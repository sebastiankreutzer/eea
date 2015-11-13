package de.tu.darmstadt.informatik.sampleGame;
import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.states.BasicGameState;

public class LaunchGame extends EEAGame {
	public BasicGameState MainMenuState;
	
	public LaunchGame() {		
	}

	@Override
	public void create() {
		// man muss die States in der create methode initialisieren , da hier sichergestellt ist , dass libgdx initialisiert ist
		MainMenuState = new MainMenuState(this);
	}	
}
