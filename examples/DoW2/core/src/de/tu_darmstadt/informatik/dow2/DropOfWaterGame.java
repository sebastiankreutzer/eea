package de.tu_darmstadt.informatik.dow2;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;

public class DropOfWaterGame extends EEAGame {
	public static EEAGameState MainMenuState;
	public static EEAGameState GameplayState;
	
	public DropOfWaterGame() {		
	}

	/**
	 * Diese Methode wird zu Beginn des Programmes aufgerufen
	 */
	protected void initStates() {
		MainMenuState = new MainMenuState(this);
		MainMenuState.setBackgroundColor(0, 255, 255);
		GameplayState = new GameplayState(this);
		GameplayState.setBackgroundColor(255, 0, 255);
	}

	/**
	 *  Diese Methode wird zu Beginn des Programmes aufgerufen, nachdem initStates aufgerufen worden ist
	 */
	protected void startGame() {
		setScreen(MainMenuState);
	}	
}
