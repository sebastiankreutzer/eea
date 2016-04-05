package de.tu_darmstadt.informatik.tanks2.maps;

import de.tu_darmstadt.informatik.eea.EEA;
import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.tanks2.entities.Pickup.PickupType;
import de.tu_darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu_darmstadt.informatik.tanks2.factories.ExplosionFactory;
import de.tu_darmstadt.informatik.tanks2.factories.MineFactory;
import de.tu_darmstadt.informatik.tanks2.factories.PickupFactory;
import de.tu_darmstadt.informatik.tanks2.factories.ShotFactory;
import de.tu_darmstadt.informatik.tanks2.factories.TankFactory;
import de.tu_darmstadt.informatik.tanks2.factories.TowerFactory;
import de.tu_darmstadt.informatik.tanks2.factories.WallFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMap;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu_darmstadt.informatik.tanks2.misc.Options;
import de.tu_darmstadt.informatik.tanks2.misc.Lexer;
import de.tu_darmstadt.informatik.tanks2.misc.SourcePosition;
import de.tu_darmstadt.informatik.tanks2.misc.Token;

public class Parser {

	/*
	 * 0,90,180,270: Startposition mit 270 links, 0 oben, 90 rechts, 180 unten
	 * Map(width, height) Tank(Name, Fraction, Life, Rotation, x, y) Wall(Life,
	 * x, y)
	 * 
	 * 
	 * Map(200 , 600) Tank(MyTank, Player, 30, 90, 300, 200) Wall(50, 10 ,10)
	 * Wall(100, 400,300)
	 * 
	 */

	private Lexer lexicalAnalyser;
	private Token currentToken;
	private SourcePosition previousTokenPosition;
	private boolean debug;

	private ExplosionFactory explosionFactory;
	private PickupFactory pickUpFactory;
	private MineFactory mineFactory;
	private ShotFactory shotFactory;
	private TankFactory tankFactory;
	private TowerFactory towerFactory;
	private WallFactory wallFactory;

	public Parser(Lexer lexer, boolean debug) {
		lexicalAnalyser = lexer;
		IResourceManager resourcesManager = EEA.getResourceManager();
		Options options = Options.getInstance();

		previousTokenPosition = new SourcePosition();

		explosionFactory = new ExplosionFactory(resourcesManager, debug);
		pickUpFactory = new PickupFactory(resourcesManager, debug);
		mineFactory = new MineFactory(resourcesManager, explosionFactory, debug);
		shotFactory = new ShotFactory(resourcesManager, explosionFactory, debug);
		tankFactory = new TankFactory(options.getDifficulty(), resourcesManager, shotFactory, mineFactory, debug);
		towerFactory = new TowerFactory(shotFactory, debug);
		wallFactory = new WallFactory(debug);
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public IMap parseMap(Map map) throws SyntaxException {

		previousTokenPosition.start = 0;
		previousTokenPosition.finish = 0;
		currentToken = lexicalAnalyser.scan();

		parseMapInformation(map);
		while (currentToken.getKind() != Token.EOT) {
			switch (currentToken.getKind()) {
			case Token.Tank:
				this.acceptIt();
				map = parseTank(map);
				break;
			case Token.Wall:
				this.acceptIt();
				map = parseWall(map);
				break;
			case Token.Shot:
				this.acceptIt();
				map = parseShot(map);
				break;
			case Token.Explosion:
				this.acceptIt();
				map = parseExplosion(map);
				break;
			case Token.Tower:
				this.acceptIt();
				map = parseTower(map);
				break;
			case Token.Pickup:
				this.acceptIt();
				map = parsePickup(map);
				break;
			case Token.Scattershot:
				this.acceptIt();
				map = parseScattershot(map);
				break;
			case Token.Mine:
				this.acceptIt();
				map = parseMine(map);
				break;
			default:
				SyntaxError(
						"\"\"% expected here but was: " + Token.spell(currentToken.getKind()) + " with value "
								+ currentToken.getSpelling(),
						Token.spell(Token.IDENTIFIER));
			}
		}
		return map;
	}

	protected Map parsePickup(Map map) throws SyntaxException {

		PickupType type = PickupType.AMMUNITION;
		if (this.accept(Token.IDENTIFIER).getSpelling().equalsIgnoreCase(PickupType.HEALTH.toString()))
			type = PickupType.HEALTH;

		int strength = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		float scaling = parseFloat();

		float x = parseFloat();
		float y = parseFloat();

		map.addEntity(pickUpFactory.createPickup(type, strength, x, y, scaling));

		return map;
	}

	protected void parseMapInformation(Map map) throws SyntaxException {
		accept(Token.Map);

		String backgroundName = accept(Token.IDENTIFIER).getSpelling();
		String mapName = accept(Token.IDENTIFIER).getSpelling();
		String nextMapName = accept(Token.IDENTIFIER).getSpelling();

		long timeLimit = parseInt();
		long time = parseInt();
		int shots = parseInt();

		GameplayLog log = GameplayLog.getInstance();
		log.setBackground(backgroundName);
		log.setMapName(mapName.substring(1, mapName.length() - 1));
		log.setNextMap(nextMapName);
		log.setTimeLimit(timeLimit);
		log.timer.setElapsedTime(time);
		log.setNumberOfShots(shots);

		// Add background
		Entity background = new Entity("background");
		String file = backgroundName.substring(1, backgroundName.length() - 1);
		background.addComponent(new ImageRenderComponent(file));
		map.addEntity(background);
	}

	protected Map parseTower(Map map) throws SyntaxException {

		float x = parseFloat();
		float y = parseFloat();

		int maxLife = parseInt();
		int life = parseInt();

		int maxShoots = parseInt();
		int shoots = parseInt();

		int strength = parseInt();
		float speed = parseFloat();

		float rotation = parseFloat();
		float scale = parseFloat();

		map.addEntity(
				towerFactory.createTower(x, y, maxLife, life, maxShoots, shoots, strength, speed, rotation, scale));

		return map;
	}

	protected Map parseExplosion(Map map) throws SyntaxException {
		int width = parseInt();
		int height = parseInt();

		float speed = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;

		float x = parseFloat();
		float y = parseFloat();
		
		map.addEntity(explosionFactory.createExplosion(x, y, speed, width, height, debug));
		return map;
	}

	protected Map parseTank(Map map) throws SyntaxException {
		String name = this.accept(Token.IDENTIFIER).getSpelling();

		float[] prs = parsePositionRotationScale();

		int strength = parseInt();
		float speed = parseFloat();

		int[] life = parseLifeOrAmmunitonOrMines();
		int[] ammo = parseLifeOrAmmunitonOrMines();
		int[] mines = parseLifeOrAmmunitonOrMines();

		map.addEntity(tankFactory.createTank(prs[0], prs[1], name, life[1], life[0], ammo[1], ammo[0], mines[1], mines[0], strength,
				speed, prs[2], prs[3]));

		return map;
	}

	protected Map parseShot(Map map) throws SyntaxException {
		float[] v = parsePositionRotationScale();
		int strength = parseInt();

		map.addEntity(shotFactory.createShot(v[0], v[1], "NONE", strength, v[2], v[3]));
		return map;
	}
	

	protected Map parseScattershot(Map map) throws SyntaxException {
		float[] v = parsePositionRotationScale();
		int strength = parseInt();
		float time = parseFloat();

		map.addEntity(shotFactory.createScatterShot(v[0], v[1], "NONE", strength, v[2], v[3], time));

		return map;
	}
	
	protected Map parseMine(Map map) throws SyntaxException {
		float[] v = parsePositionRotationScale();
		int strength = parseInt();

		map.addEntity(mineFactory.createMine(v[0], v[1], v[3], strength));
		return map;
	}

	
	private float[] parsePositionRotationScale() {
		float[] v = new float[4];
		v[0] = parseFloat();
		v[1] = parseFloat();
		v[2] = parseFloat();
		v[3] = parseFloat();
		return v;
	}
	
	private int[] parseLifeOrAmmunitonOrMines() {
		int[] v = new int[2];
		v[0] = parseInt();
		v[1] = parseInt();
		return v;
	}

	protected Map parseWall(Map map) throws SyntaxException {

		float x = parseFloat();
		float y = parseFloat();
		int maxLife = parseInt();
		int life = parseInt();
		float rotation = parseFloat();
		float scaling = parseFloat();

		map.addEntity(wallFactory.createWall(x, y, maxLife, life, rotation, scaling));

		return map;
	}

	/**
	 * Generates an exception and writes debug information in the console.
	 * 
	 * @param message
	 *            Should contain an '%' character to insert the quoted token at
	 *            this position.
	 * @param tokenQuoted
	 *            The name of the token that caused this syntactic error.
	 * @throws SyntaxException
	 */
	private void SyntaxError(String message, String tokenQuoted) throws SyntaxException {
		SourcePosition pos = currentToken.getSourcePosition();
		int idx = message.indexOf('%');
		if (idx > 0) {
			message = message.substring(0, idx - 1) + tokenQuoted + message.substring(idx + 1);
		}
		System.out.print("ERROR: " + message + " " + pos.start + ".." + pos.finish + " ");
		throw (new SyntaxException());
	}

	private Token accept(int tokenExpected) throws SyntaxException {
		if (currentToken.getKind() == tokenExpected) {
			previousTokenPosition = currentToken.getSourcePosition();
			Token temp = currentToken;
			currentToken = lexicalAnalyser.scan();
			return temp;
		} else {
			SyntaxError("\"%\" expected here", Token.spell(tokenExpected));
			return null;
		}
	}

	private float parseFloat() {
		float value = Float.parseFloat(currentToken.getSpelling());
		next();
		return value;
	}

	private int parseInt() {
		int value = Integer.parseInt(currentToken.getSpelling());
		next();
		return value;
	}

	private void next() {
		previousTokenPosition = currentToken.getSourcePosition();
		currentToken = lexicalAnalyser.scan();
	}

	void acceptIt() {
		previousTokenPosition = currentToken.getSourcePosition();
		currentToken = lexicalAnalyser.scan();
	}

}
