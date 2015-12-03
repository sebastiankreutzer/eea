package de.tu.darmstadt.informatik.eea.action;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.states.EEAGameState;

public class ChangeStateAction extends EEAAction {
	
	private final EEAGame game;
	private final EEAGameState state;
	private boolean reset;
	
	public ChangeStateAction(EEAGame game, EEAGameState state){
		this(game, state, false);
	}
	
	public ChangeStateAction(EEAGame game, EEAGameState state, boolean resetOldState){
		this.game = game;
		this.state = state;
		this.reset = resetOldState;
	}

	@Override
	public boolean act(float delta) {
		game.setScreen(state);
		
		if(reset){
			
		}
		
		return true;
	}
	
}
