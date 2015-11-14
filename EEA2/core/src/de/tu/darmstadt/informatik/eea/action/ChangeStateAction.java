package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.states.BasicGameState;

public class ChangeStateAction extends Action {
	
	private final EEAGame game;
	private final BasicGameState state;
	private boolean reset;
	
	public ChangeStateAction(EEAGame game, BasicGameState state){
		this(game, state, false);
	}
	
	public ChangeStateAction(EEAGame game, BasicGameState state, boolean resetState){
		this.game = game;
		this.state = state;
		this.reset = resetState;
	}

	@Override
	public boolean act(float delta) {
		game.setScreen(state);
		
		if(reset){
			// TODO Reset state
		}
		
		return true;
	}
	
}
