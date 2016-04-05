package de.tu_darmstadt.informatik.tanks2.maps;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.utils.GdxRuntimeException;

import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.IResourceManager.PathName;
import de.tu_darmstadt.informatik.eea.ROMFile;
import de.tu_darmstadt.informatik.eea.RWFile;
import de.tu_darmstadt.informatik.eea.ResourceManager;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.LaunchTanks;
import de.tu_darmstadt.informatik.tanks2.exceptions.SemanticException;
import de.tu_darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMap;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu_darmstadt.informatik.tanks2.misc.Options;
import de.tu_darmstadt.informatik.tanks2.misc.Lexer;
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
	private PathName pathName;

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
	 * {@link Map#parse(IResourceManager, boolean, Options)} wird dann die
	 * angegebene Map geparst.
	 * 
	 * @param map
	 *            Der Name der naechsten Map
	 */
	public void loadMap(String map) {
		name = map;
		path = mapFolder + name;
		pathName = PathName.RELATIVE;
	}

	/**
	 * Bereitet das Laden einer gespeicherten Map vor. Beim naechsten Aufruf von
	 * {@link Map#parse(IResourceManager, boolean, Options)} wird dann die
	 * angegebene Map geparst.
	 * 
	 * @param absolutePath
	 *            Der absolute Dateipfad des gespeicherten Spielstands.
	 */
	public void loadSavegame(String absolutePath) {
		int idx = absolutePath.lastIndexOf(File.separatorChar) + 1;
		name = absolutePath.substring(idx);
		path = absolutePath;
		pathName = PathName.ABSOLUTE;
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
	public boolean save(String savegameName, List<Entity> entities, IResourceManager resourcesManager) {
		try {
			// Oeffne einen RWFile und schreibe die in einen String formatierten
			// Entities
			RWFile file = resourcesManager.openRWFile(saveFolder + savegameName + saveExtension);
			file.writeString(convertToMapFormat(entities), false);
			return true;
		} catch (IOException e) {
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
	 * @param resourceManager
	 *            Der ResourceManager
	 * @param debug
	 *            Der Debugstatus
	 * @throws SemanticException
	 *             Wenn die Map semantische Fehler aufweist
	 * @throws SyntaxException
	 *             Wenn die Map syntaktische Fehler aufweist
	 * @throws IOException
	 *             Wenn der galadene Dateipfad ungueltig ist.
	 */
	public void parse(ResourceManager resourceManager, boolean debug)
			throws SemanticException, SyntaxException, IOException {
		// Leere die Liste der zuvor geladenen Entities
		clear();

		ROMFile file;
		try {
			file = resourceManager.openROMFile(path, pathName);
		} catch (IOException e) {
			System.out.println("Error loading file " + path + " : " + e.toString() + " does not exist.");
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}

		// Oeffne ein neuen SourceFile und erstelle einen zugehoerigen Lexer und
		// Parser
		SourceFile sc;
		try {
			sc = new SourceFile(file);
		} catch (IOException e) {
			resetToDefault();
			throw e;
		}
		Lexer lexer = new Lexer(sc);
		Parser parser = new Parser(lexer, debug);
		parser.setDebug(debug);

		// Parse die Map oder werfe eine Fehlermeldung
		try {
			parser.parseMap(this);
			check();
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
	 * Ueberprueft die Map auf Logikfehler, wie fehlende Gegner oder
	 * kollidierende Entities.
	 * 
	 * @throws SemanticException
	 *             Wenn ein solcher Fehler gefunden wurde.
	 */
	private void check() throws SemanticException {
		int playerCount = 0;
		int OpponentCount = 0;

		for (Entity entity : entities) {
			if (entity.getID().startsWith(LaunchTanks.player1)) {
				playerCount++;
				if (checkForCollision(entity, entities)) {
					throw new SemanticException("PlayerOne collides with an other Entity in the map");
				}
			}
			if (entity.getID().startsWith(LaunchTanks.opponentTank) || entity.getID().startsWith(LaunchTanks.player2)) {
				OpponentCount++;
				if (checkForCollision(entity, entities)) {
					throw new SemanticException(entity.getID() + " collides with an other Entity in the map");
				}
			}
		}

		if (playerCount > 1) {
			throw new SemanticException("More than one Player1 has been declared");
		} else if (playerCount == 0) {
			throw new SemanticException("No Player has been declared");
		} else if (OpponentCount == 0) {
			throw new SemanticException("No Opponent has been declared");
		}
	}

	/**
	 * Ueberprueft ob eine Entity mit mindestens einer Entity aus einer Liste
	 * von Entities kollidiert. Testet nicht auf Kollision der Entities der
	 * Liste untereinander.
	 * 
	 * @param entity
	 * @param entities
	 * @return False falls keine Kollision vorliegt, ansonsten true
	 */
	private boolean checkForCollision(Entity entity, List<Entity> entities) {
		for (Entity OtherEntity : entities) {
			if (entity.collidesWith(OtherEntity))
				return true;
		}
		return false;

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
	 * Gibt den Pfad der Quelle der Map zurueck, falls eine Savegame geladen
	 * wurde ist der Pfad absolut, ansonsten relativ.
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
			String s = entity.toString();
			System.out.println(entity.getID() + ": " + s);
			if (s.startsWith("Tank") || s.startsWith("Wall") || s.startsWith("Shot") || s.startsWith("Explosion")
					|| s.startsWith("Border") || s.startsWith("Mine") || s.startsWith("Tower") || s.startsWith("Pickup")
					|| s.startsWith("Scattershot")) {
				stringBuffer.append(s);
				stringBuffer.append("\n");
			}
		}
		return stringBuffer.toString();
	}
}
