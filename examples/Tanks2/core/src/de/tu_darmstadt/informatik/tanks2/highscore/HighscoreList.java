package de.tu_darmstadt.informatik.tanks2.highscore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreList {
	
	private List<Highscore> highscores;
	private static final HighscoreList highscoreList = new HighscoreList();
	private String currentMap;
	private boolean loaded;
	
	/**
	 * Private constructor
	 */
	private HighscoreList() {
		highscores = new ArrayList<Highscore>();
		currentMap = "";
		loaded = false;
	}
	
	/**
	 * Adds a new Highscore to the list of highscores and sorts this list.
	 * @param highscore Highscore that should be added
	 */
	public void addHighscore(Highscore highscore) {
		highscores.add(highscore);
		this.sort();
		// Maximum of 10 entries
		if (highscores.size() > 10) {
			highscores.remove(10);
		}
		this.save();
	}
	 
	/**
	 * Sorts the list of Highscores
	 */
	private void sort() {
		Collections.sort(highscores, new HighscoreComparator());
	}
	
	
	/**
	 * Removes all Highscore entries and saves it to the file
	 */
	public void resetHighscore() {
		highscores = new ArrayList<Highscore>();
		this.save();
	}
	
	/**
	 * Returns a string containing all highscores with name, shots and time separated by a new line
	 * @return string containing all highscores with name, shots and time separated by a new line
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
	 * Returns the single instance of the HighscoreList
	 * @return HighscoreList instance
	 */
	public static HighscoreList getInstance() {
		return highscoreList;
	}
	
	/**
	 * Returns the list of Highscores
	 * @return list of Highscores
	 */
	public List<Highscore> getHighscores() {
		return highscores;
	}
	

	/**
	 * Saves the highscores in a file to highscores/mapName.hsc.
	 */
	public void save() {
		
		if (currentMap != null && !currentMap.isEmpty()) {
			String fileName = "maps/" + currentMap + ".hsc";
			try {
				FileOutputStream file = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(file);
				oos.writeObject(highscores);
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Loads the highscores to the passed map.
	 * @param filename name of the map to whom the highscores should be loaded
	 */
	@SuppressWarnings("unchecked")
	public void load(String mapName) {
		
		if (mapName == null || mapName.equals("null")) {
			currentMap = "";
			loaded = false;
			this.highscores = new ArrayList<Highscore>();
			return;
		}
		
		currentMap = mapName;
		File f = new File("maps/" + mapName + ".hsc");
		
		// Check if file exists to avoid exceptions
		if (f.exists()) {
			try {
				FileInputStream file = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(file);
				this.highscores = (List<Highscore>) ois.readObject();
				ois.close();
				loaded = true;
				System.out.println("Highscore for map " + currentMap + " successfully loaded.");
			} catch (FileNotFoundException e) {
				System.out.println("No highscore file found for this map.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("No highscore file found for this map.");
			loaded = false;
			this.highscores = new ArrayList<Highscore>();
		}
	}
	
	/**
	 * Returns if the passed highscore is good enough to be added to the list of highscores
	 * @param hsc the highscore to be added
	 * @return true if the highscore ist good enough to be added otherwise false
	 */
	public boolean isNewHighscore(Highscore hsc) {
		
		// List is not full
		if (highscores.size() < 10) {
			return true;
		}
		
		// Last highscore entry
		Highscore lastHighscore = highscores.get(9);
		
		// Comparator
		HighscoreComparator comp = new HighscoreComparator();
		
		// If the result is -1, the new highscore is better
		int result = comp.compare(hsc, lastHighscore);
		
		// Better
		if (result == -1) {
			return true;
		}
		// Worse
		else {
			return false;
		}
	}
	
	/**
	 * Returns the name of the map to which the highscore belongs.
	 * @return name of the map to which the highscore belongs.
	 */
	public String getMapName() {
		return currentMap;
	}
	
	/**
	 * Returns if a highscore was successfully loaded.
	 * @return true if a highscore was successfully loaded otherwise false
	 */
	public boolean hasHighscoreLoaded() {
		return loaded;
	}
}
