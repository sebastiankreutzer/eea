package de.tu_darmstadt.informatik.tanks2.highscore;

import java.io.Serializable;

public class Highscore implements Serializable {

	private static final long serialVersionUID = 8175891823171504140L;
	String playerName;
	int firedShots;
	float passedTime;

	/**
	 * Erzeugt einen neuen Highscore
	 * 
	 * @param playerName
	 *            Der Spielername
	 * @param firedShots
	 *            Anzahl der abgefeuerten Schuesse dieses Spielers
	 * @param passedTime
	 *            Die vergangene Spieldauer
	 */
	public Highscore(String playerName, int firedShots, float passedTime) {

		if (playerName == null || playerName.isEmpty()) {
			this.playerName = "Unknown";
		} else {
			this.playerName = playerName;
		}

		this.firedShots = firedShots;
		this.passedTime = passedTime;
	}

	/**
	 * Gibt den Namen des Spielers zu diesem Highscore zurueck
	 * 
	 * @return Der Name des Spielers
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Returns the amount of shots fired
	 * 
	 * @return amount of shots fired
	 */
	public int getFiredShots() {
		return firedShots;
	}

	/**
	 * Gibt die Spieldauer dieses Highscores zurueck
	 * 
	 * @return Die Spieldauer
	 */
	public float getPassedTime() {
		return passedTime;
	}

	/**
	 * playerName firedShots passedTime
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(playerName);
		sb.append(" ");
		sb.append(firedShots);
		sb.append(" ");
		sb.append(passedTime);
		return sb.toString();
	}
}
