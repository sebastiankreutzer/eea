package de.tu.darmstadt.informatik.eea.states;

public class GameState {
	private final String stateName;
	
	public GameState(String stateName) {
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}
}