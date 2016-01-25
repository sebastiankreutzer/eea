package de.tu_darmstadt.informatik.tanks2;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;

public class LaunchTanks extends EEAGame {
	
	public static MainMenuState mainMenu;
	public static GameplayState gameState;
	public static OptionsState options;
	public static EEAGameState pauseState;

	@Override
	protected void initStates() {
		mainMenu = new MainMenuState(this);
		gameState = new GameplayState(this);
		options = new OptionsState(this);
		pauseState = new PauseState(this);
	}

	@Override
	protected void startGame() {
		setScreen(mainMenu);
	}

}
