package de.tu.darmstadt.informatik.eea.event;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public class OREvent extends EEAEvent {

	public static final String ID = "OREvent";
	
	private final EEAEvent[] events;
	
	public OREvent(EEAEvent... events) {
		super(ID);
		this.events = events;
	}

	@Override
	public boolean eventTriggered(float delta) {
		for(EEAEvent event : events){
			if(event.eventTriggered(delta)) return true;
		}
		return false;
	}
	
	public void setOwnerEntity(Entity owner) {
		super.setOwnerEntity(owner);
		for (EEAEvent event : events) {
			event.setOwnerEntity(owner);
		}
	}

}
