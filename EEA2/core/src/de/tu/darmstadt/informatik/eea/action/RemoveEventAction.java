package de.tu.darmstadt.informatik.eea.action;

import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;

public class RemoveEventAction extends EEAAction {
	
	private EEAEvent event;
	
	public RemoveEventAction(EEAEvent event) {
		this.event = event;
	}

	@Override
	public boolean act(float delta) {
		// TODO Does this work as intended and should we check if instance?
		((Entity) getTarget()).removeComponent(event);
		return false;
	}

}
