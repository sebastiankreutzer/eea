package de.tu.darmstadt.informatik.tanks2;

import de.tu.darmstadt.informatik.eea.EEAGame;

public class LaunchTanks extends EEAGame {
	
	public static MainMenuState mainMenu;
	public static GameplayState gameState;
	public static OptionsState options;

	@Override
	protected void initStates() {
		mainMenu = new MainMenuState(this);
		gameState = new GameplayState(this);
		options = new OptionsState(this);
	}

	@Override
	protected void startGame() {
		setScreen(mainMenu);
	}

}
