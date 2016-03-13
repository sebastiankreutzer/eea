package de.tu_darmstadt.informatik.tanks2.maps;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.utils.GdxRuntimeException;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.RWFile;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.exceptions.SemanticException;
import de.tu_darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMap;
import de.tu_darmstadt.informatik.tanks2.misc.ErrorReporter;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu_darmstadt.informatik.tanks2.misc.Options;
import de.tu_darmstadt.informatik.tanks2.misc.Scanner;
import de.tu_darmstadt.informatik.tanks2.misc.SourceFile;

/**
 * Eine Map stellt eine Sammlung von Entities dar die aus einer mittels String
 * spezifiezierten Datei geparst werden.
 * 
 * @author jr
 *
 */
public class Map implements IMap {

	public static final String mapFolder = "maps/";
	public static final String saveFolder = "saves/";
	public static final String saveExtension = ".tanks";
	public static final String defaultMap = "map00";

	private String name, path;
	private static Map map = new Map();
	private List<Entity> entities;

	/**
	 * Erzeugt eine neue, leere Map mit Standardwerten
	 */
	private Map() {
		resetToDefault();
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

	/**
	 * Gibt eine globale Instanz der Map zurueck.
	 * 
	 * @return Eine Map
	 */
	public static Map getInstance() {
		return map;
	}

	/**
	 * Bereitet das Laden einer neuen Map vor. Beim naechsten Aufruf von
	 * {@link Map#parse(IResourcesManager, boolean, Options)} wird dann die
	 * angegebene Map geparst.
	 * 
	 * @param map
	 *            Der Name der naechsten Map
	 */
	public void loadMap(String map) {
		name = map;
		path = mapFolder + name;
	}

	/**
	 * Bereitet das Laden einer gespeicherten Map vor. Beim naechsten Aufruf von
	 * {@link Map#parse(IResourcesManager, boolean, Options)} wird dann die
	 * angegebene Map geparst.
	 * 
	 * @param savegame
	 *            Der Name des gespeicherten Spielstands.
	 */
	public void loadSavegame(String savegame) {
		name = savegame;
		path = saveFolder + name;
	}

	/**
	 * Speichert die Entities in einer Datei die spaeter wieder wie eine Map
	 * geladen werden kann.
	 * 
	 * @param savegameName
	 *            Der Name fuer die Datei, ohne Dateiendung
	 * @param entities
	 *            Alle entities des EntityManagers
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @return true falls beim Speichern keine Probleme auftraten, andernfalls
	 *         false
	 */
	public boolean save(String savegameName, List<Entity> entities, IResourcesManager resourcesManager) {
		try {
			// Oeffne einen RWFile und schreibe die in einen String formatierten
			// Entities
			RWFile file = resourcesManager.openRWFile(saveFolder + savegameName + saveExtension);
			file.writeString(convertToMapFormat(entities), false);
			return true;
		} catch (GdxRuntimeException e) {
			System.err.println(e);
			return false;
		}
	}

	/**
	 * Parst eine Map aus dem mittels {@link Map#loadMap(String)} oder
	 * {@link Map#loadSavegame(String)} geladenen Pfad in Abhaengigkeit der
	 * uebergebenen Optionen und ueberprueft diese auf semantische und
	 * syntaktische Korrektheit.
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @param options
	 *            Die Optionen
	 * @param debug
	 *            Der Debugstatus
	 * @throws SemanticException
	 *             Wenn die Map semantische Fehler aufweist
	 * @throws SyntaxException
	 *             Wenn die Map syntaktische Fehler aufweist
	 */
	public void parse(IResourcesManager resourcesManager, Options options, boolean debug)
			throws SemanticException, SyntaxException {
		// Leere die Liste der zuvor geladenen Entities
		clear();
		// Oeffne ein neuen SourceFile und erstelle einen zugehoerigen Lexer und
		// Parser
		SourceFile sc = new SourceFile(path, resourcesManager);
		Scanner lexer = new Scanner(sc);
		Parser parser = new Parser(lexer, new ErrorReporter(), resourcesManager, options);
		parser.setDebug(debug);
		// Parse die Map oder werfe eine Fehlermeldung
		try {
			new Checker(parser.parseMap()).check();
		} catch (SemanticException e) {
			resetToDefault();
			throw e;
		} catch (SyntaxException e) {
			resetToDefault();
			throw e;
		}
	}

	/**
	 * Leert die Liste der zuvor geladenen Entities.
	 */
	public void clear() {
		entities = new CopyOnWriteArrayList<Entity>();
		GameplayLog.getInstance().setMultiplayer(false);
	}

	/**
	 * Setzt die Map auf als Standard spezifierte Map.
	 */
	public void resetToDefault() {
		clear();
		loadMap(defaultMap);
	}

	/**
	 * Gibt den Namen der Map zurueck.
	 * 
	 * @return Der Name ohne Dateipfad
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gibt den relativen Pfad der Quelle der Map zurueck.
	 * 
	 * @return Der Pfad der Datei
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Konvertiert eine Liste von Entities sowie Informationen des globalen
	 * GameplayLogs in einen formatierten String. Aus diesem String kann eine
	 * Map geparst werden.
	 * 
	 * @param entities
	 *            Die Liste der Entities
	 * @return Eine String repraesentation einer Map
	 */
	public String convertToMapFormat(List<Entity> entities) {
		// Der String soll die Informationen des GameplayLogs enthalten
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(GameplayLog.getInstance().toString());
		stringBuffer.append("\n");
		// Der String soll einen String fuer alle Entities enthalten die beim
		// Parsen der Map wieder benoetigt werden
		for (Entity entity : entities) {
			String s = entity.getID();
			System.out.println(s);
			if (s.startsWith("Tank") || s.startsWith("Wall") || s.startsWith("Shot") || s.startsWith("Explosion")
					|| s.startsWith("Border") || s.startsWith("Mine") || s.startsWith("Tower") || s.startsWith("Pickup")
					|| s.startsWith("Scattershot")) {
				stringBuffer.append(entity.toString());
				stringBuffer.append("\n");
			}
		}
		return stringBuffer.toString();
	}
}
