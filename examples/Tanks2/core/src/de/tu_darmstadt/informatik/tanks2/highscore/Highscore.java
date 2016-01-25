package de.tu_darmstadt.informatik.tanks2.highscore;

import java.io.Serializable;

public class Highscore implements Serializable {

	private static final long serialVersionUID = 8175891823171504140L;
	String playerName;
	int firedShots;
	float passedTime;
	
	/**
	 * Creates a new Highscore with the passed values
	 * @param playerName name of the player that scored the highscore
	 * @param firedShots shots fired by the player
	 * @param passedTime total time until gameover
	 */
	public Highscore(String playerName, int firedShots, float passedTime) {
		
		if (playerName == null || playerName.isEmpty()) {
			this.playerName = "Unknown";
		}
		else {
			this.playerName = playerName;
		}
		
		this.firedShots = firedShots;
		this.passedTime = passedTime;
	}
	
	/**
	 * Returns the name of the player of the Highscore
	 * @return name of the player
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Returns the amount of shots fired
	 * @return amount of shots fired
	 */
	public int getFiredShots() {
		return firedShots;
	}
	
	/**
	 * Returns the time of the Highscore 
	 * @return time until gameover
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
