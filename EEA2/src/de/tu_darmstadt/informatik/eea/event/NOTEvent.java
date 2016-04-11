package de.tu_darmstadt.informatik.eea.event;

import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * Acts as the negation of a event, so that the actions are triggered whenever
 * the underlying event evaluates to false.
 * 
 * @author Johann Reinhard
 *
 */
public class NOTEvent extends EEAEvent {

	public static final String ID = "ANDEvent";

	private EEAEvent event;

	/**
	 * Creates a new NOTEvent.
	 * @param event The EEAEvent to be negated.
	 */
	public NOTEvent(EEAEvent event) {
		super(ID);
		this.event = event;
	}

	@Override
	public boolean eventTriggered(float delta) {
		return !event.eventTriggered(delta);
	}

	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
		event.setOwnerEntity(owningEntity);
	}

}
