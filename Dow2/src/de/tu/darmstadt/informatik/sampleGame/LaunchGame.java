package de.tu.darmstadt.informatik.sampleGame;
import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.states.BasicGameState;

public class LaunchGame extends EEAGame {
	public final BasicGameState MainMenuState = new MainMenuState(this);
	
	public LaunchGame() {
	}
}
