package de.tu.darmstadt.informatik.sampleGame;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.states.EEAGameState;

public class LaunchGame extends EEAGame {
	public static EEAGameState MainMenuState;
	public static EEAGameState GameplayState;
	
	public LaunchGame() {
	}

	protected void initStates() {
		MainMenuState = new MainMenuState(this);
		MainMenuState.setBackgroundColor(0, 255, 255);
		GameplayState = new GameplayState(this);
		GameplayState.setBackgroundColor(255, 0, 255);
	}

	protected void startGame() {
		setScreen(MainMenuState);
	}	
}
