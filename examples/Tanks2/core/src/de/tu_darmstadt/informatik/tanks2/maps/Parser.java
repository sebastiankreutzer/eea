package de.tu_darmstadt.informatik.tanks2.maps;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.tanks2.entities.Pickup.PickUpType;
import de.tu_darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu_darmstadt.informatik.tanks2.factories.BackgroundFactory;
import de.tu_darmstadt.informatik.tanks2.factories.BorderFactory;
import de.tu_darmstadt.informatik.tanks2.factories.ExplosionFactory;
import de.tu_darmstadt.informatik.tanks2.factories.MineFactory;
import de.tu_darmstadt.informatik.tanks2.factories.PickupFactory;
import de.tu_darmstadt.informatik.tanks2.factories.ScatterShootFactory;
import de.tu_darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu_darmstadt.informatik.tanks2.factories.TankFactory;
import de.tu_darmstadt.informatik.tanks2.factories.TowerFactory;
import de.tu_darmstadt.informatik.tanks2.factories.WallFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMap;
import de.tu_darmstadt.informatik.tanks2.interfaces.IParser;
import de.tu_darmstadt.informatik.tanks2.misc.ErrorReporter;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu_darmstadt.informatik.tanks2.misc.Options;
import de.tu_darmstadt.informatik.tanks2.misc.Scanner;
import de.tu_darmstadt.informatik.tanks2.misc.SourcePosition;
import de.tu_darmstadt.informatik.tanks2.misc.Token;

public class Parser implements IParser {

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

	private Scanner lexicalAnalyser;
	private ErrorReporter errorReporter;
	private Token currentToken;
	private SourcePosition previousTokenPosition;
	private boolean debug;
	private IResourcesManager resourcesManager;

	private ExplosionFactory explosionFactory;
	private PickupFactory pickUpFactory;
	private MineFactory mineFactory;
	private ScatterShootFactory scatterShotFactory;
	private ShootFactory shotFactory;
	private TankFactory tankFactory;
	private TowerFactory towerFactory;
	private WallFactory wallFactory;

	public Parser(Scanner lexer, ErrorReporter reporter, IResourcesManager resourcesManager, Options options) {
		lexicalAnalyser = lexer;
		errorReporter = reporter;
		previousTokenPosition = new SourcePosition();
		debug = false;
		this.resourcesManager = resourcesManager;
		
		explosionFactory = new ExplosionFactory(resourcesManager, debug);
		pickUpFactory = new PickupFactory(debug, resourcesManager);
		mineFactory = new MineFactory(resourcesManager, explosionFactory, debug);
		shotFactory = new ShootFactory(resourcesManager, explosionFactory, debug);
		tankFactory = new TankFactory(options.getDifficulty(), resourcesManager, shotFactory, mineFactory, debug);
		towerFactory = new TowerFactory(resourcesManager, shotFactory, debug);
		wallFactory = new WallFactory(debug, resourcesManager);
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public IMap parseMap() throws SyntaxException {

		previousTokenPosition.start = 0;
		previousTokenPosition.finish = 0;
		currentToken = lexicalAnalyser.scan();

		Map map = parse(Map.getInstance());
		while (currentToken.getType() != Token.EOT) {
			switch (currentToken.getType()) {
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
				map = parseShoot(map);
				break;
			case Token.Explosion:
				this.acceptIt();
				map = parseExplosion(map);
				break;
			case Token.Border:
				this.acceptIt();
				map = parseBorder(map);
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
				map = parseScattershoot(map);
				break;
			case Token.Mine:
				this.acceptIt();
				map = parseMine(map);
				break;
			default:
				SyntaxError("\"%\" expected here", Token.spell(Token.Tank) + " or " + Token.spell(Token.Wall)
						+ " but was: " + currentToken.getType());
			}
		}
		return map;
	}

	protected Map parseMine(Map map) throws SyntaxException {

		int strength = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		float scale = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		map.addEntity(mineFactory.createMine(x, y, scale, strength));
		return map;
	}

	protected Map parseScattershoot(Map map) throws SyntaxException {
		float x = parseFloat();
		float y = parseFloat();

		int strength = parseInt();

		float rotation = parseFloat();
		float scale = parseFloat();

		float time = parseFloat();

		map.addEntity(shotFactory.createScatterShot(x, y, "NONE", strength, rotation, scale, time));

		return map;
	}

	protected Map parsePickup(Map map) throws SyntaxException {

		PickUpType type = PickUpType.AMMUNITION;
		if (this.accept(Token.IDENTIFIER).getSpelling().equalsIgnoreCase(PickUpType.HEALTH.toString()))
			type = PickUpType.HEALTH;

		int streangth = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		float scaling = parseFloat();
		float x = parseFloat();
		float y = parseFloat();

		map.addEntity(pickUpFactory.createPickup(type, streangth, x, y, scaling));

		return map;
	}

	protected Map parse(Map map) throws SyntaxException {

		this.accept(Token.Map);

		String backGround = this.accept(Token.IDENTIFIER).getSpelling();
		String mapName = this.accept(Token.IDENTIFIER).getSpelling();
		String nextMap = this.accept(Token.IDENTIFIER).getSpelling();

		long timeLimit = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		long time = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		int shots = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		GameplayLog.getInstance().setBackground(backGround);
		GameplayLog.getInstance().setMapName(mapName.substring(1, mapName.length() - 1));
		GameplayLog.getInstance().setNextMap(nextMap);
		GameplayLog.getInstance().setTimeLimit(timeLimit);
		GameplayLog.getInstance().timer.set(time);
		GameplayLog.getInstance().setNumberOfShots(shots);

		map.addEntity(new BackgroundFactory(backGround.substring(1, backGround.length() - 1), resourcesManager)
				.createEntity());

		return map;
	}

	protected Map parseTower(Map map) throws SyntaxException {

		float x = parseFloat();
		float y = parseFloat();

		int maxLife = parseInt();
		int life = parseInt();

		int maxShoots = parseInt();
		int shoots = parseInt();

		int streangth = parseInt();
		float speed = parseFloat();

		float rotation = parseFloat();
		float scale = parseFloat();

		map.addEntity(
				towerFactory.createTower(x, y, maxLife, life, maxShoots, shoots, streangth, speed, rotation, scale));

		return map;
	}

	protected Map parseBorder(Map map) throws SyntaxException {

		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		int width = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		int height = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		map.addEntity(new BorderFactory(x, y, width, height).createEntity());

		return map;
	}

	protected Map parseExplosion(Map map) throws SyntaxException {

		int width = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		// this.accept(Token.COMMA);

		int height = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		// this.accept(Token.COMMA);

		float speed = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;

		// this.accept(Token.COMMA);

		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		// this.accept(Token.COMMA);

		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		// this.accept(Token.RPAREN);

		map.addEntity(explosionFactory.createExplosion(x, y, speed, width, height, debug));
		return map;
	}

	protected Map parseTank(Map map) throws SyntaxException {
		String name = this.accept(Token.IDENTIFIER).getSpelling();

		float x = parseFloat();
		float y = parseFloat();

		int maxLife = parseInt();
		int life = parseInt();

		int shootsMax = parseInt();
		int shoots = parseInt();

		int minesMax = parseInt();
		int mines = parseInt();

		int strength = parseInt();
		float speed = parseFloat();

		float rotation = parseFloat();
		float scale = parseFloat();

		map.addEntity(tankFactory.createTank(x, y, name, maxLife, life, shootsMax, shoots, minesMax, mines, strength,
				speed, rotation, scale));

		return map;
	}

	protected Map parseShoot(Map map) throws SyntaxException {

		float x = parseFloat();
		float y = parseFloat();
		int strength = parseInt();
		float rotation = parseFloat();
		float scale = parseFloat();

		map.addEntity(shotFactory.createShot(x, y, "NONE", strength, rotation, scale));
		return map;
	}

	protected Map parseWall(Map map) throws SyntaxException {

		int maxLife = parseInt();
		int life = parseInt();
		float x = parseFloat();
		float y = parseFloat();
		float rotation = parseFloat();
		float scaling = parseFloat();

		map.addEntity(wallFactory.createEntity(x, y, maxLife, life, rotation, scaling));

		return map;
	}

	private void SyntaxError(String messageTemplate, String tokenQuoted) throws SyntaxException {
		SourcePosition pos = currentToken.getSourcePosition();
		errorReporter.reportError(messageTemplate, tokenQuoted, pos);
		throw (new SyntaxException());
	}

	private Token accept(int tokenExpected) throws SyntaxException {
		if (currentToken.getType() == tokenExpected) {
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
