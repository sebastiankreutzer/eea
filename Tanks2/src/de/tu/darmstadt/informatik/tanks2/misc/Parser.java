package de.tu.darmstadt.informatik.tanks2.misc;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.tanks2.entities.Pickup.PickUpType;
import de.tu.darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu.darmstadt.informatik.tanks2.factories.BackgroundFactory;
import de.tu.darmstadt.informatik.tanks2.factories.BorderFactory;
import de.tu.darmstadt.informatik.tanks2.factories.ExplosionFactory;
import de.tu.darmstadt.informatik.tanks2.factories.MineFactory;
import de.tu.darmstadt.informatik.tanks2.factories.PickupFactory;
import de.tu.darmstadt.informatik.tanks2.factories.ScatterShootFactory;
import de.tu.darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu.darmstadt.informatik.tanks2.factories.TankFactory;
import de.tu.darmstadt.informatik.tanks2.factories.TowerFactory;
import de.tu.darmstadt.informatik.tanks2.factories.WallFactory;
import de.tu.darmstadt.informatik.tanks2.interfaces.IMap;
import de.tu.darmstadt.informatik.tanks2.interfaces.IParser;

public class Parser implements IParser {

	/*
	 * 0,90,180,270: Startposition mit 270 links, 0 oben, 90 rechts, 180 unten
	 * Map(width, height)
	 * 	Tank(Name, Fraction, Life, Rotation, x, y)
	 * 	Wall(Life, x, y)
	 * 	
	 * 
	 * Map(200 , 600)
	 * 	Tank(MyTank, Player, 30, 90, 300, 200)
	 * 	Wall(50, 10 ,10)
	 * 	Wall(100, 400,300)
	 * 
	 */
	
	  private Scanner lexicalAnalyser;
	  private ErrorReporter errorReporter;
	  private Token currentToken;
	  private SourcePosition previousTokenPosition;
	  private boolean debug;

	  public Parser(Scanner lexer, ErrorReporter reporter) {
	    lexicalAnalyser = lexer;
	    errorReporter = reporter;
	    previousTokenPosition = new SourcePosition();
	    debug = false;
	  }
	  
	  public void setDebug(boolean debug){
		  this.debug = debug;
	  }
	
	public IMap parseMap() throws SyntaxException {
		
	    previousTokenPosition.start = 0;
	    previousTokenPosition.finish = 0;
	    currentToken = lexicalAnalyser.scan();
		
		Map map = parse(Map.getInstance());
		while(currentToken.getType() != Token.EOT){
			switch(currentToken.getType()){
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
				default: SyntaxError("\"%\" expected here", Token.spell(Token.Tank) +" or " +Token.spell(Token.Wall) +" but was: " +currentToken.getType());
			}
		}
		return map;
	}
	
	protected Map parseMine(Map map) throws SyntaxException {
		
		int strength = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		float scale = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		map.addEntity(new MineFactory(new Vector2(x, y), scale, strength, debug).createEntity());
		return map;
	}
	protected Map parseScattershoot(Map map) throws SyntaxException {
		
		int time = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		int streangth = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
				
		float rotation = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		float scale = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
				
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());

		map.addEntity(new ScatterShootFactory(time, streangth, "Shoot", rotation, scale, x,y , debug).createEntity());	
		
		return map;
	}

	protected Map parsePickup(Map map)throws SyntaxException {
		
		PickUpType type = PickUpType.AMMUNITION;
		if (this.accept(Token.IDENTIFIER).getSpelling().equalsIgnoreCase(PickUpType.HEALTH.toString())) type = PickUpType.HEALTH;
		
		int streangth = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		int rotation = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		float scaling = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
		
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		map.addEntity(new PickupFactory(type, streangth, rotation, scaling, x, y, debug).createEntity());
		
		
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
		
		// TODO Fix GameplayLog
//		GameplayLog.getInstance().setBackground(backGround);
//		GameplayLog.getInstance().setMapName(mapName.substring(1, mapName.length()-1));
//		GameplayLog.getInstance().setNextMap(nextMap);
//		GameplayLog.getInstance().setTimeLimit(timeLimit);
//		GameplayLog.getInstance().getTimer().setElapsedTime(time);
//		GameplayLog.getInstance().setNumberOfShots(shots);
		
		map.addEntity(new BackgroundFactory(backGround.substring(1, backGround.length()-1)).createEntity());
		
		return map;
	}
	
	protected Map parseTower(Map map) throws SyntaxException {
		
		
		
		int maxLife = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		int life = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		int maxShoots = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		int shoots = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		int streangth = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		float speed = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
		
		
		int rotation = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		float scaling = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
		
		
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		
		map.addEntity(new TowerFactory(maxLife, life, maxShoots, shoots, streangth, speed, rotation, scaling, x, y, debug).createEntity());
		
		return map;
	}
	
	protected Map parseBorder(Map map) throws SyntaxException{
		
		
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		int width = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		int height = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		
		map.addEntity(new BorderFactory(x, y, width, height).createEntity());
		
		return map;
	}
	
	protected Map parseExplosion(Map map) throws SyntaxException {
		
		
		
		
		int width =  Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		
		int height =  Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		
		float speed =  Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
		
		//this.accept(Token.COMMA);
		
		int x =  Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		
		int y =  Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.RPAREN);
		
		map.addEntity(new ExplosionFactory(x, y, speed, width, height, debug).createEntity());
		return map;
	}
	
	protected Map parseTank(Map map) throws SyntaxException {
		
		//boolean player = false;
		
		
		String name = this.accept(Token.IDENTIFIER).getSpelling();
		
		//this.accept(Token.COMMA);
		
		//switch(currentToken.getType()){
		//	case Token.Player : 
		//		this.acceptIt();
		//		player = true;
		//		break;
		//	case Token.NPC: 
		//		this.acceptIt();
		//		player = false;
		//		break;
		//	default: SyntaxError("\"%\" expected here", Token.spell(Token.Player) +" or " +Token.spell(Token.NPC));
		//}
		//this.accept(Token.COMMA);
		int maxLife = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int life = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int shootsMax = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int shoots = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int minesMax = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int mines = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int streangth = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		float speed = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int rotation = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		float scaling = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
		
		//this.accept(Token.COMMA);
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		map.addEntity(new TankFactory(name, maxLife, life, shootsMax, shoots, minesMax, mines, streangth, speed,rotation, scaling, x, y, Options.getInstance().getDifficulty(),debug).createEntity());
		
		//this.accept(Token.RPAREN);
		
		return map;
	}
	
	protected Map parseShoot(Map map) throws SyntaxException {
		
		
		
		
		int streangth = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		
		float rotation = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		

		float scale = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling()) / 100.0f;
		
		//this.accept(Token.COMMA);
		
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		
		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.RPAREN);
		
		map.addEntity(new ShootFactory(streangth, "Shoot", rotation, scale, x,y , debug).createEntity());		
		return map;
	}
	
	protected Map parseWall(Map map) throws SyntaxException {


		
		

		int maxLife = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int life = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int rotation = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int scaling = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int x = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		//this.accept(Token.COMMA);
		int y = Integer.valueOf(this.accept(Token.INTLITERAL).getSpelling());
		
		map.addEntity(new WallFactory(maxLife, life, rotation, scaling, x, y, debug).createEntity());
		
		//this.accept(Token.RPAREN);
		return map;
	}
	
	private void SyntaxError(String messageTemplate, String tokenQuoted) throws SyntaxException {
		     SourcePosition pos = currentToken.getSourcePosition();
		    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
		    throw(new SyntaxException());
	}
	
	private  Token accept (int tokenExpected) throws SyntaxException {
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

		  void acceptIt() {
		    previousTokenPosition = currentToken.getSourcePosition();
		    currentToken = lexicalAnalyser.scan();
		  }
	
	
}
