package de.tu.darmstadt.informatik.tanks2.misc;

import de.tu.darmstadt.informatik.eea.entity.Component;
import de.tu.darmstadt.informatik.tanks2.interfaces.IArtificialIntelligence;

public abstract class AI extends Component implements IArtificialIntelligence {

	public AI(String componentID) {
		super(componentID);
	}

	@Override
	public void update(float delta) {
		getNextAction().act(delta);
	}

}
