package de.tu_darmstadt.informatik.tanks2.highscore;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.RWFile;
import de.tu_darmstadt.informatik.tanks2.LaunchTanks;

/**
 * Eine HighscoreList enthaelt eine Liste der zehn besten Highscores einer
 * bestimmten Map.
 * 
 * @author jr
 *
 */
public class HighscoreList {

	private final static String highscoreFolder = "highscores/";
	private final static String extension = ".hsc";

	private IResourceManager resourcesManager;
	private List<Highscore> highscores;
	private String map;
	private boolean loaded;

	/**
	 * Erzeugt eine neue HighscoreList ohne Highscore Eintraege
	 *
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @param map
	 *            Der Name der Map
	 */
	private HighscoreList(IResourceManager resourcesManager, String map) {
		this.resourcesManager = resourcesManager;
		highscores = new ArrayList<Highscore>();
		this.map = map;
		loaded = false;
	}

	/**
	 * Erzeugt eine neue HighscoreList ohne Highscore Eintraege
	 *
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @param map
	 *            Der Name der Map
	 * @param highscores
	 *            Die Liste der bisherigen Highscore
	 */
	private HighscoreList(IResourceManager resourcesManager, String map, List<Highscore> highscores) {
		this.resourcesManager = resourcesManager;
		this.highscores = highscores;
		this.map = map;
		loaded = true;
	}

	/**
	 * Fuegt einen neuen Highscore hinzu, sortiert die Liste neu und entfernt
	 * gegebenenfalls den letzeten Highscore.
	 * 
	 * @param highscore
	 *            Der hinzuzufuegende Highscore
	 */
	public void addHighscore(Highscore highscore) {
		// Fuege den neuen Highscore hinzu und sortiere die Liste
		highscores.add(highscore);
		sort();
		// Beschraenke die Nummer der Highscores auf zehn
		if (highscores.size() > 10) {
			highscores.remove(10);
		}
		save();
	}

	/**
	 * Sortiert die Liste der Highscores
	 */
	private void sort() {
		Collections.sort(highscores, new HighscoreComparator());
	}

	/**
	 * Leert die Liste der Highscores
	 */
	public void resetHighscore() {
		highscores = new ArrayList<Highscore>();
		this.save();
	}

	/**
	 * Gibt einen formatierten String zurueck, der zum Laden dieser
	 * HighscoreList dient.
	 * 
	 * @return Einen String mit den formatierten Highscores.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Highscore h : highscores) {
			sb.append(h.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Gibt die Liste aller gespeicherten Highscores zurueck.
	 * 
	 * @return Eine Liste der Highscores.
	 */
	public List<Highscore> getHighscores() {
		return highscores;
	}

	/**
	 * Speichert die HighscoreList.
	 */
	private void save() {
		if (map != null && !map.isEmpty()) {
			String path = highscoreFolder + map + extension;
			try {
				// Oeffne einen RWFile und schreibe die HighscoreList
				RWFile file = resourcesManager.openRWFile(path);
				OutputStream outputStream = file.write(false);
				ObjectOutputStream oos = new ObjectOutputStream(outputStream);
				oos.writeObject(highscores);
				oos.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Could not save map to " + path);
			}
		}
	}

	/**
	 * Laed den Highscore fuer eine Map. Falls beim Laden Fehler auftreten wird
	 * eine neue, leere HighscoreList zurueckgegeben.
	 * 
	 * @param map
	 *            Der Name der Map.
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @return Eine HighscoreList
	 */
	@SuppressWarnings("unchecked")
	public static HighscoreList load(String map, IResourceManager resourcesManager) {
		// Erzeuge eine neue, leere HighscoreList wenn der String null ist oder
		// oder dem Ende entspricht
		if (map == null || map.equals(LaunchTanks.finish)) {
			System.out.println("No highscore file for " + map);
			return new HighscoreList(resourcesManager, LaunchTanks.finish);
		}
		try {
			// Oeffne einen RWFile
			RWFile file = resourcesManager.openRWFile(highscoreFolder + map + extension);
			// Oeffne einen InputStream und lade die Highscores
			InputStream inputStream = file.read();
			List<Highscore> highscoreList = loadHighscores(inputStream);
			inputStream.close();
			// Gebe die HighscoreList zurueck
			System.out.println("Highscore for map " + map + " successfully loaded.");
			return new HighscoreList(resourcesManager, map, highscoreList);
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		// Bei einem Fehler wird eine leere HighscoreList zurueckgegeben
		System.out.println("No highscore file found for this map.");
		return new HighscoreList(resourcesManager, map);
	}

	/**
	 * Laedt eine Liste von Highscores aus dem uebergebenen InputStream.
	 * 
	 * @param inputStream
	 *            Der InputStream
	 * @return Eine Liste von Highscores, kann leer sein.
	 */
	private static List<Highscore> loadHighscores(InputStream inputStream) {
		List<Highscore> highscoreList = new ArrayList<Highscore>();
		try {
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			highscoreList = (List<Highscore>) ois.readObject();
			ois.close();
		} catch (IOException e) {
			System.out.println("Could not properly read or close the highscore file.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("The highscore file contains errors.");
			e.printStackTrace();
		}
		return highscoreList;
	}

	/**
	 * Ueberprueft ob ein Highscore besser als der kleinste Highscore dieser
	 * HighscoreList ist.
	 * 
	 * @param hsc
	 *            Der Highscore
	 * @return true wenn der Highscore groesser ist, andernfalls false
	 */
	public boolean isNewHighscore(Highscore hsc) {
		// Liste nicht voll
		if (highscores.size() < 10) {
			return true;
		}
		// Nehme den letzten Highscore und vergleiche die beiden
		Highscore lastHighscore = highscores.get(9);
		HighscoreComparator comp = new HighscoreComparator();
		int result = comp.compare(hsc, lastHighscore);
		return (result == -1) ? true : false;
	}

	/**
	 * Gib den Namen der zugehoerigen Map zurueck.
	 * 
	 * @return Der Name der Map.
	 */
	public String getMapName() {
		return map;
	}

	/**
	 * Gibt zurueck ob die HighscoreList erfolgreich geladen werden konnte.
	 * 
	 * @return true falls die HighscoreList geladen wurde, andernfalls false.
	 */
	public boolean hasHighscoreLoaded() {
		return loaded;
	}
}
