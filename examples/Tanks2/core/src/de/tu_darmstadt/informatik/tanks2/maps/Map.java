package de.tu_darmstadt.informatik.tanks2.maps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.tu_darmstadt.informatik.eea.EEAGraphics;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.states.EntityManager;
import de.tu_darmstadt.informatik.tanks2.exceptions.SemanticException;
import de.tu_darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu_darmstadt.informatik.tanks2.highscore.HighscoreList;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMap;
import de.tu_darmstadt.informatik.tanks2.misc.ErrorReporter;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu_darmstadt.informatik.tanks2.misc.Options;
import de.tu_darmstadt.informatik.tanks2.misc.Scanner;
import de.tu_darmstadt.informatik.tanks2.misc.SourceFile;

public class Map implements IMap {

	private String source;
	private static Map map = new Map();
	private List<Entity> entities;

	private Map() {
		entities = new CopyOnWriteArrayList<Entity>();
		// Default map
		source = "maps/map00";
	}

	public void addEntity(Entity Entity) {
		entities.add(Entity);
	}

	public void removeEntity(Entity Entity) {
		entities.remove(Entity);
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

	public void parse(String map, IResourcesManager resourcesManager, boolean debug, Options options)
			throws SemanticException, SyntaxException {
		clear();
		source = map;
		SourceFile sc = new SourceFile(source, resourcesManager);
		Scanner lexer = new Scanner(sc);
		Parser parser = new Parser(lexer, new ErrorReporter(), resourcesManager, options);
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

	public String toString(EntityManager entityManager) {

		List<Entity> entities = entityManager.getAllEntities();

		if (entities.isEmpty())
			entities = this.entities;
		if (entities.isEmpty())
			return null;

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GameplayLog.getInstance().toString());
		stringBuffer.append("\n");

		for (Entity Entity : entities) {
			if (Entity.toString().startsWith("Tank") || Entity.toString().startsWith("Wall")
					|| Entity.toString().startsWith("Shot") || Entity.toString().startsWith("Explosion")
					|| Entity.toString().startsWith("Border") || Entity.toString().startsWith("Mine")
					|| Entity.toString().startsWith("Tower") || Entity.toString().startsWith("Pickup")
					|| Entity.toString().startsWith("Scattershot")) {
				stringBuffer.append(Entity.toString());
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
