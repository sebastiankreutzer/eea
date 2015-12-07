package de.tu.darmstadt.informatik.tanks2.maps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.tu.darmstadt.informatik.eea.EEAGraphics;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.exceptions.SemanticException;
import de.tu.darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu.darmstadt.informatik.tanks2.highscore.HighscoreList;
import de.tu.darmstadt.informatik.tanks2.interfaces.IMap;
import de.tu.darmstadt.informatik.tanks2.misc.ErrorReporter;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu.darmstadt.informatik.tanks2.misc.Scanner;
import de.tu.darmstadt.informatik.tanks2.misc.SourceFile;

public class Map implements IMap {
	
	private String source;
	private static Map map = new Map();
	private List<Entity> entities;
	private EEAGraphics eeaGraphics;
	
	private Map() {
		entities = new CopyOnWriteArrayList<Entity>();
		// Default map
		source = "maps/map00";
	}
	
	public void addEntity(Entity Entity) {
		entities.add(Entity);
	}
	
	public void removeEntity(Entity Entity){
		entities.remove(Entity);
	}
	
	@Override
	public List<Entity> getEntities() {
		return entities;
	}
	
	public static Map getInstance(EEAGraphics eeaGraphics) {
		map.eeaGraphics = eeaGraphics;
		return map;
	}
	
	public void load(String map) {
		source = map;
	}
	
	public boolean save(String destination) {
		try {
			File file = new File(destination);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(toString());
			fileWriter.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public void parse(String map, boolean debug) throws SemanticException, SyntaxException {
		clear();
		source = map;
		SourceFile sc = new SourceFile(source);
		Scanner lexer = new Scanner(sc);
		Parser parser = new Parser(lexer, new ErrorReporter(), eeaGraphics);
		parser.setDebug(debug);
		try {
			new Checker(parser.parseMap()).check();
			// Load highscore file of map if exist
			String mapName = GameplayLog.getInstance().getMapName();
	    	HighscoreList.getInstance().load(mapName);
		} catch (SemanticException e) {
			// TODO Enable exception after clarified why scaling does not work
			//resetToDefault();
			//throw e;
		} catch (SyntaxException e) {
			resetToDefault();
			throw e;
		}
	}
	
	public String toString() {
		
		return "not implemented";
		
//		List<Entity> entities;
//		
//		StateBasedEntityManager EntityManager = StateBasedEntityManager.getInstance();
//		if(EntityManager.getEntitiesByState(Tanks.GAMEPLAYSTATE) == null) {
//			entities = this.entities;
//		}
//		else  entities = EntityManager.getEntitiesByState(Tanks.GAMEPLAYSTATE);
//		
//		if (entities.isEmpty()) return null;
//		StringBuffer stringBuffer = new StringBuffer();
//		stringBuffer.append(GameplayLog.getInstance().toString());
//		stringBuffer.append("\n");
//		
//		for(Entity Entity : entities){
//			if(Entity.toString().startsWith("Tank") || Entity.toString().startsWith("Wall") || Entity.toString().startsWith("Shot")  || Entity.toString().startsWith("Explosion")
//					|| Entity.toString().startsWith("Border") || Entity.toString().startsWith("Mine") || Entity.toString().startsWith("Tower") || Entity.toString().startsWith("Pickup")
//					|| Entity.toString().startsWith("Scattershot")){
//			stringBuffer.append(Entity.toString());
//			stringBuffer.append("\n");
//			}
//		}
//		return stringBuffer.toString();
	}
	
	/**
	 * Clears all map entities.
	 */
	public void clear() {
		entities = new CopyOnWriteArrayList<Entity>();
		// TODO Fix GameplayLog
		//GameplayLog.getInstance().setMultiplayer(false);
	}
	
	public void resetToDefault() {
		clear();
		source = "maps/map00";
	}
	
	public String getSource() {
		return this.source;
	}
}

