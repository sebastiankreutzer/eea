package de.tu_darmstadt.gdi1.tanks.model.map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.tu_darmstadt.gdi1.tanks.model.GameplayLog;
import de.tu_darmstadt.gdi1.tanks.model.exceptions.SemanticException;
import de.tu_darmstadt.gdi1.tanks.model.exceptions.SyntaxException;
import de.tu_darmstadt.gdi1.tanks.model.highscore.HighscoreList;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.IMap;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.Checker;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.ErrorReporter;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.Parser;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.Scanner;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.SourceFile;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class Map implements IMap {
	
	private String source;
	private static Map map = new Map();
	private List<Entity> entities;
	
	private Map() {
		entities = new CopyOnWriteArrayList<Entity>();
		// Default map
		source = "maps/map00";
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}
	
	@Override
	public List<Entity> getEntities() {
		return entities;
	}
	
	public static Map getInstance() {
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
		Parser parser = new Parser(lexer, new ErrorReporter());
		parser.setDebug(debug);
		try {
			new Checker(parser.parseMap()).check();
			// Load highscore file of map if exist
			String mapName = GameplayLog.getInstance().getMapName();
	    	HighscoreList.getInstance().load(mapName);
		} catch (SemanticException e) {
			resetToDefault();
			throw e;
		} catch (SyntaxException e) {
			resetToDefault();
			throw e;
		}
	}
	
	public String toString() {
		
		List<Entity> entities;
		
		StateBasedEntityManager entityManager = StateBasedEntityManager.getInstance();
		if(entityManager.getEntitiesByState(Tanks.GAMEPLAYSTATE) == null) {
			entities = this.entities;
		}
		else {
			entities = entityManager.getEntitiesByState(Tanks.GAMEPLAYSTATE);
		}
		
		if (entities.isEmpty()) return null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GameplayLog.getInstance().toString());
		stringBuffer.append("\n");
		
		for(Entity entity : entities){
			if(entity.toString().startsWith("Tank") || entity.toString().startsWith("Wall") || entity.toString().startsWith("Shot")  || entity.toString().startsWith("Explosion")
					|| entity.toString().startsWith("Border") || entity.toString().startsWith("Mine") || entity.toString().startsWith("Tower") || entity.toString().startsWith("Pickup")
					|| entity.toString().startsWith("Scattershot")){
			stringBuffer.append(entity.toString());
			stringBuffer.append("\n");
			}
		}
		return stringBuffer.toString();
	}
	
	/**
	 * Clears all map entities.
	 */
	public void clear() {
		entities = new CopyOnWriteArrayList<Entity>();
		GameplayLog.getInstance().setMultiplayer(false);
	}
	
	public void resetToDefault() {
		clear();
		source = "maps/map00";
	}
	
	public String getSource() {
		return this.source;
	}
}
