package de.tu.darmstadt.informatik.sampleGame;
import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.states.BasicGameState;

public class LaunchGame extends EEAGame {
	public BasicGameState MainMenuState;
	public BasicGameState MainMenuState2;
	
	public LaunchGame() {		
	}

	@Override
	public void create() {
		// man muss die States in der create methode initialisieren , da hier sichergestellt ist , dass libgdx initialisiert ist
		initStates();
		startGame();
	}

	private void initStates() {
		MainMenuState = new MainMenuState(this);
		MainMenuState2 = new MainMenuState(this);
		MainMenuState2.setBackgroundColor(255, 255, 255);
	}

	private void startGame() {
		setScreen(MainMenuState2);
	}	
}
