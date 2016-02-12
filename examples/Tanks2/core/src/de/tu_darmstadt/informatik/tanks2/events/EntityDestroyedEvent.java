package de.tu_darmstadt.informatik.tanks2.events;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;

public class EntityDestroyedEvent extends EEAEvent {
	
	public static final String ID = "EntityDestroyedEvent";
	
	private Entity entity;

	public EntityDestroyedEvent() {
		super(ID);
		entity = getOwnerEntity();
	}
	
	public EntityDestroyedEvent(Entity target) {
		super(ID);
		entity = target;
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		// TODO Is there a better way?
		if(!entity.isManaged()) return true;
		return false;
	}

}
