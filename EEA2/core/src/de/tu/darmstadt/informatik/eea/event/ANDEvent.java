package de.tu.darmstadt.informatik.eea.event;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public class ANDEvent extends EEAEvent {
	
	public static final String ID = "ANDEvent";
	
	private final EEAEvent[] events;
	
	public ANDEvent(EEAEvent... events) {
		super(ID);
		this.events = events;
	}

	@Override
	public boolean eventTriggered(float delta) {
		for(EEAEvent event : events){
			if(!event.eventTriggered(delta)) return false;
		}
		return true;
	}
	
	public void setOwnerEntity(Entity owner) {
		super.setOwnerEntity(owner);
		for (EEAEvent event : events) {
			event.setOwnerEntity(owner);
		}
	}

}
