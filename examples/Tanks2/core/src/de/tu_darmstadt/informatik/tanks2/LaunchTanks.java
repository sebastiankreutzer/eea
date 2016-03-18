package de.tu_darmstadt.informatik.tanks2;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;
import de.tu_darmstadt.informatik.tanks2.misc.Options;

public class LaunchTanks extends EEAGame {
	
	public static MainMenuState mainMenu;
	public static GameplayState gameState;
	public static OptionsState optionsState;
	public static EEAGameState pauseState;
	public static HighscoreState highScoreState;

	@Override
	protected void initStates() {
		Options options = new Options(resourceManager);
		
		mainMenu = new MainMenuState(this, options);
		gameState = new GameplayState(this, options);
		optionsState = new OptionsState(this, options);
		pauseState = new PauseState(this);
		highScoreState = new HighscoreState(this);
	}

	@Override
	protected void startGame() {
		setScreen(mainMenu);
	}

}
