package de.tu_darmstadt.gdi1.tanks.tests.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;

import de.tu_darmstadt.gdi1.tanks.model.GameplayLog;
import de.tu_darmstadt.gdi1.tanks.model.entities.Mine;
import de.tu_darmstadt.gdi1.tanks.model.entities.Tank;
import de.tu_darmstadt.gdi1.tanks.model.highscore.Highscore;
import de.tu_darmstadt.gdi1.tanks.model.highscore.HighscoreList;
import de.tu_darmstadt.gdi1.tanks.model.map.Map;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class TanksTestAdapterExtended1 extends TanksTestAdapterMinimal {

	HighscoreList highscores = HighscoreList.getInstance();
	
	/**
	 * Use this constructor to set up everything you need.
	 */
	public TanksTestAdapterExtended1() {
		super();
	}
	
	/**
	 * Adds a highscore entry to highscores with the passed values for name, shots fired and passed time.
	 * @param name name of the player that scored the highscore
	 * @param shotsFired amount of shots fired by the player
	 * @param timePassed time passed in seconds
	 */
	public void addHighscore(String name, int shotsFired, int timePassed) {
		highscores.addHighscore(new Highscore(name, shotsFired, timePassed));
	}
	
	/**
	 * Resets the highscores. Alle entries are deleted and @see {@link #getHighscoreCount()} should return 0.
	 */
	public void resetHighscore() {
		highscores.resetHighscore();
	}
	
	/**
	 * Returns the player name of the highscore entry at the passed position.
	 * The best highscore should be at position 0.
	 * See the specification in the task assignment for the definition of best highscore.
	 * Positions that are invalid should return null.
	 * @param position position of the highscore entry
	 * @return playername of the highscore entry at the passed position or null if position is invalid
	 */
	public String getNameAtHighscorePosition(int position) {
		List<Highscore> highscoreList = highscores.getHighscores();
		if(position < 0 || position > getHighscoreCount() -1) return null;
		String playerName = highscoreList.get(position).getPlayerName();
		return playerName;
	}
	
	/**
	 * Returns the amount of shots fired of the highscore entry a the passed position
	 * The best highscore should be at position 0.
	 * See the specification in the task assignment for the definition of best highscore.
	 * Positions that are invalid should return null.
	 * @param position position of the highscore entry
	 * @return amount of shots fired of the highscore entry at the passed position or null if position is invalid
	 */
	public int getShotsFiredAtHighscorePosition(int position) {
		List<Highscore> highscoreList = highscores.getHighscores();
		if(position < 0 || position > getHighscoreCount() -1) return -1;
		int shotsFired = highscoreList.get(position).getFiredShots();
		return shotsFired;
	}
	
	/**
	 * Returns the amount of time passed of the highscore entry a the passed position
	 * The best highscore should be at position 0.
	 * See the specification in the task assignment for the definition of best highscore.
	 * Positions that are invalid should return null.
	 * @param position position of the highscore entry
	 * @return amount of time passed of the highscore entry at the passed position or null if position is invalid
	 */
	public int getTimePassedAtHighscorePosition(int position) {
		List<Highscore> highscoreList = highscores.getHighscores();
		if(position < 0 || position > getHighscoreCount() -1) return -1;
		int timePassed = (int)highscoreList.get(position).getPassedTime();
		return timePassed;
	}
	
	/**
	 * Dies liefert die Anzahl an Highscore-Eintraegen, die aktuell gespeichert
	 * sind, zurueck.
	 * @return Anzahl an Highscore-Eintraegen
	 */
	
	/**
	 * Returns the amount of highscore entries currently stored.
	 * @return amount of highscore entries
	 */
	public int getHighscoreCount() {
		List<Highscore> highscoreList = highscores.getHighscores();
		int highscoreCount = highscoreList.size();
		return highscoreCount;
	}
	
	/* *************************************************** 
	 * ******************* Tupel Mine ********************
	 * *************************************************** */
	
	/**
	 * Mine ist die Bezeichnung eine zerstoerbare Mine mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Minen muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Die erste Mine ist an der Position 0. 
	 * 
	 * @param position : Minen-Nummer
	 * @return Staerke der Mine mit der uebergebenen Minen-Nummer, wenn die Nummer
	 * ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	
	/**
	 * Returns the strength value of the mine at the passed position.
	 * It is important that the mines are stored in the same order as they are parsed from the map file.
	 * The first mine parsed should be at position 0.
	 * If the position is invalid return -1.
	 * @param position position of the mine
	 * @return strength value of the mine at the passed position
	 */
	public int getMineStrength(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Mine> mines = new ArrayList<Mine>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Mine")) {
				mines.add((Mine) entity);
			}
		}
		
		if (position < 0 || position > mines.size() - 1)
			return -1;
		
		return mines.get(position).getStreangth();
	}
	
	/**
	 * Mine ist die Bezeichnung eine zerstoerbare Mine mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Minen muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Die erste Mine ist an der Position 0. 
	 * 
	 * @param position : Minen-Nummer
	 * @return Skalierung der Mine mit der uebergebenen Minen-Nummer, wenn die Nummer
	 * ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getMineScale(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Mine> mines = new ArrayList<Mine>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Mine")) {
				mines.add((Mine) entity);
			}
		}
		
		if (position < 0 || position > mines.size() - 1)
			return -1;
		
		return (int) (mines.get(position).getScale() * 100);
	}
	
	/**
	 * Mine ist die Bezeichnung eine zerstoerbare Mine mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Minen muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Die erste Mine ist an der Position 0. 
	 * 
	 * @param position : Minen-Nummer
	 * @return Aktuelle x-Koordinate der Mine mit der uebergebenen Minen-Nummer, wenn die Nummer
	 * ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getMineXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Mine> mines = new ArrayList<Mine>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Mine")) {
				mines.add((Mine) entity);
			}
		}
		
		if (position < 0 || position > mines.size() - 1)
			return -1;
		
		return (int) mines.get(position).getPosition().x;
	}
	
	/**
	 * Mine ist die Bezeichnung eine zerstoerbare Mine mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Minen muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Die erste Mine ist an der Position 0. 
	 * 
	 * @param position : Minen-Nummer
	 * @return Aktuelle y-Koordinate der Mine mit der uebergebenen Minen-Nummer, wenn die Nummer
	 * ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getMineYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Mine> mines = new ArrayList<Mine>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Mine")) {
				mines.add((Mine) entity);
			}
		}
		
		if (position < 0 || position > mines.size() - 1)
			return -1;
		
		return (int) mines.get(position).getPosition().y;
	}
	
	/**
	 * @return Aktuelle Anzahl an Minen im laufenden Spiel
	 */
	public int getMineCount() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		if (tanks != null) entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		
		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Mine")) {
				count++;
			}
		}
		return count;
	}
	
	/* *************************************************** 
	 * **************** Begrenzte Minen ******************
	 * *************************************************** */
	
	/**
	 * Panzer haben nur begrenzt viele Minen, die auf der Karte plaziert werden koennen. 
	 * Diese Methode liefert die maximal moegliche Anzahl an Minen zurueck.
	 * @return Maximale Minenanzahl des Panzers
	 */
	public int getMineMaxAmmo() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tank> tanks = new ArrayList<Tank>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				tanks.add((Tank) entity);
			}
		}
		
		return tanks.get(0).getMaxMinesAmmo();
	}
	
	/**
	 * Panzer haben nur begrenzt viele Minen, die auf der Karte plaziert werden koennen. 
	 * Diese Methode liefert die aktuell verfuegbare Anzahl an Minen zurueck.
	 * @return Aktuelle Minenanzahl des Panzers
	 */
	public int getMineActualAmmo() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tank> tanks = new ArrayList<Tank>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				tanks.add((Tank) entity);
			}
		}
		
		return tanks.get(0).getActualMinesAmmo();
	}
	
	/* *************************************************** 
	 * ********************** Input **********************
	 * *************************************************** */	
	
	/**
	 * This Method should emulate the pressing of the m key.
	 * This should plant a mine.
	 * 
	 * Diese Methode emuliert das Druecken der m-Taste und sollte
	 * die Plazierung einer Mine ausloesen.
	 */
	public void handleKeyPressM() {
		handleKeyPressed(0, Input.KEY_M);	
	}
	
	/**
	 * This Method should emulate the pressing of the escape key.
	 * This is needed for transitions between the menu und the gameplay window
	 * 
	 * Diese Methode emuliert das Druecken der Escape-Taste und wird fuer
	 * die Transitionen zwischen Menue und Spielfenster verwendet
	 */
	public void handleKeyPressEscape() {
		handleKeyPressed(0, Input.KEY_ESCAPE);	
	}
	
	/**
	 * This Method should emulate the pressing of the key s.
	 * This should save the game.
	 * 
	 * Diese Methode emuliert das Druecken der s-Taste und sollte
	 * das Speichern der aktuellen Karte hervorrufen.
	 */
	public void handleKeyPressS() {
		handleKeyPressed(0, Input.KEY_S);		
	}
	
	/* *************************************************** 
	 * ************ Load & save map file *****************
	 * *************************************************** */	
	
	/**
	 * Laed eine gespeicherte Karte von einem File.
	 * 
	 * Loads a map with gameplay infos into the game.
	 */
	public void loadSaveGameFromFile(File file) {
		loadMapFromFile(file);
	}
	
	/**
	 * Dies speichert den aktuellen Spielstand. Die Methode liefert einen String zurueck,
	 * der in eine Datei mit der Endung .tanks gespeichert werden kann. Genaue Details zur
	 * toString()-Methode entnehmen Sie bitte der Aufgabenstellung
	 * @return aktueller Spielstand als String
	 */
	
	/**
	 * Saves the current state of the game. 
	 * This method should return the string that would be saved into a .tanks savegame file.
	 * For further informations regarding the savegame format see the specification in the task assignment.
	 * @return string representation of the current gamestate
	 */
	public String saveCurrentGameState() {
		return null;
	}
	
	/* *************************************************** 
	 * ******************* Various ***********************
	 * *************************************************** */
	
	/**
	 * Diese Methode gibt die vergangene Zeit des aktuell geladenen
	 * Spielstands zurueck
	 * @return elapsedtime : vergangene Zeit des aktuellen Spielstands
	 */
	
	/**
	 * Return the time passed of the currently loaded savegame.
	 * @return time passed of the currently loaded savegame
	 */
	public int getTimePassedOfLoadedMap() {
		GameplayLog log;
		
		log = GameplayLog.getInstance();
		
		return (int) log.getTimer().getElapsedTime();
	}
	
	/**
	 * Diese Methode gibt die Anazahl abgefeuerter Schuesse im aktuell geladenen
	 * Spielstand zurueck
	 * @return amount of shots fired by the player of the currently loaded savegame
	 */
	
	/**
	 * Returns the amount of shots fired by the player of the currently loaded savegame.
	 * @return amount of shots fired by the player of the currently loaded savegame
	 */
	public int getShotsFiredOfLoadedMap() {
		GameplayLog log;

		log = GameplayLog.getInstance();
		
		return (int) log.getNumberOfShots();
	}


}
