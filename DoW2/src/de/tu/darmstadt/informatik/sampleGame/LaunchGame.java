package de.tu.darmstadt.informatik.sampleGame;
import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.states.BasicGameState;

public class LaunchGame extends EEAGame {
	public static BasicGameState MainMenuState;
	public static BasicGameState GameplayState;
	
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
