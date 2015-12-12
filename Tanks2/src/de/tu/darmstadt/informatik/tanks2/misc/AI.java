package de.tu.darmstadt.informatik.tanks2.misc;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.EEAComponent;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.interfaces.IArtificialIntelligence;

public abstract class AI extends EEAComponent implements IArtificialIntelligence {
	
	protected String nameTarget;
	protected Entity target;

	public AI(String componentID, String target) {
		super(componentID);
		this.nameTarget = target;
	}

	@Override
	public void update(float delta) {
		Action a = getNextAction();
		a.setActor(owner);
		a.act(delta);
	}

}
