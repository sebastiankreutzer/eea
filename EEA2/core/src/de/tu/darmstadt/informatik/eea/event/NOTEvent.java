package de.tu.darmstadt.informatik.eea.event;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public class NOTEvent extends EEAEvent {
	
	public static final String ID = "ANDEvent";
	
	private EEAEvent event;

	public NOTEvent(EEAEvent event) {
		super(ID);
		this.event = event;
	}

	@Override
	public boolean eventTriggered(float delta) {
		return !event.eventTriggered(delta);
	}
	
	public void setOwnerEntity(Entity owningEntity){
		super.setOwnerEntity(owningEntity);
		event.setOwnerEntity(owningEntity);
	}

}
