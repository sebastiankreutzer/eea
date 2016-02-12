package de.tu_darmstadt.gdi1.tanks.model.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IArtificialIntelligence;
import eea.engine.action.Action;
import eea.engine.component.Component;
public abstract class AI extends Component implements IArtificialIntelligence {
	


	public AI(String id) {
		super(id);
	}



	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Action action = getNextAction();
		action.update(gc, sb, delta, this);
		
	}

}
