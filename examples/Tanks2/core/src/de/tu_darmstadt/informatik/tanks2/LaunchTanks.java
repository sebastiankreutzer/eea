package de.tu_darmstadt.informatik.tanks2;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.tanks2.misc.Options;

public class LaunchTanks extends EEAGame {

	public static boolean debug = false;

	// Die verschiedenen EEAGameStates dieses Spiels
	public static MainMenuState mainMenu;
	public static GameplayState gameState;
	public static OptionsState optionsState;
	public static PauseState pauseState;
	public static HighscoreState highScoreState;

	// Konstanten zum Parsen
	public static final String player1 = "\"PlayerOne\"", player2 = "\"PlayerTwo\"", opponentTank = "\"OpponentTank";
	public static final String healthpack = "\"Healthpack\"", ammopack = "\"Ammopack\"";
	public static final String finish = "null";

	@Override
	protected void initStates() {
		// Die Optionen werden zum initialisieren benoetigt
		Options options = Options.getInstance();
		// Initialisiere die EEAGameStates
		mainMenu = new MainMenuState(this);
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
