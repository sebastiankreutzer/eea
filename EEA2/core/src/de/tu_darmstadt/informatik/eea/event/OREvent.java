package de.tu_darmstadt.informatik.eea.event;

import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * This event is triggered whenever at least one of the contained events
 * evaluates to true.
 * 
 * @author Johann Reinhard
 *
 */
public class OREvent extends EEAEvent {

	public static final String ID = "OREvent";

	private final EEAEvent[] events;

	/**
	 * Creates a new OREvent.
	 * @param events An array of EEAEvents.
	 */
	public OREvent(EEAEvent... events) {
		super(ID);
		this.events = events;
	}

	@Override
	public boolean eventTriggered(float delta) {
		for (EEAEvent event : events) {
			if (event.eventTriggered(delta))
				return true;
		}
		return false;
	}

	@Override
	public void onAddComponent() {
		super.onAddComponent();
		for (EEAEvent event : events) {
			event.onAddComponent();
		}
	}

	@Override
	public void onRemoveComponent() {
		super.onRemoveComponent();
		for (EEAEvent event : events) {
			event.onRemoveComponent();
		}
	}

	public void setOwnerEntity(Entity owner) {
		super.setOwnerEntity(owner);
		for (EEAEvent event : events) {
			event.setOwnerEntity(owner);
		}
	}

}
