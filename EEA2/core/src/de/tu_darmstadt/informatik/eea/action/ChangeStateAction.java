package de.tu_darmstadt.informatik.eea.action;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;

public class ChangeStateAction extends EEAAction {
	
	private final EEAGame game;
	private final EEAGameState newState;
	private boolean resetOldState;
	
	public ChangeStateAction(EEAGame game, EEAGameState state){
		this(game, state, false);
	}
	
	public ChangeStateAction(EEAGame game, EEAGameState newState, boolean resetOldState){
		this.game = game;
		this.newState = newState;
		this.resetOldState = resetOldState;
	}

	@Override
	public boolean act(float delta) {
		if(resetOldState){
			EEAGameState oldState = (EEAGameState) game.getScreen();
			oldState.reset();
		}
		
		game.setScreen(newState);
		
		return true;
	}
	
}
