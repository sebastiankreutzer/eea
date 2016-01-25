package de.tu_darmstadt.informatik.dow2;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.ResourcesManager;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;

public class LaunchGame extends EEAGame {
	public static EEAGameState MainMenuState;
	public static EEAGameState GameplayState;
	private IResourcesManager resourcesManager;
	
	public LaunchGame() {
		resourcesManager = new ResourcesManager();
	}

	protected void initStates() {
		MainMenuState = new MainMenuState(this, resourcesManager);
		MainMenuState.setBackgroundColor(0, 255, 255);
		GameplayState = new GameplayState(this, resourcesManager);
		GameplayState.setBackgroundColor(255, 0, 255);
	}

	protected void startGame() {
		setScreen(MainMenuState);
	}	
}
