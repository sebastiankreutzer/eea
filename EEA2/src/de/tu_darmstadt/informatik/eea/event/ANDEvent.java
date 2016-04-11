package de.tu_darmstadt.informatik.eea.event;

import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * This class can be used to link events together so that the actions only get
 * executed when all single events are triggered.
 * 
 * @author Johann Reinhard
 *
 */
public class ANDEvent extends EEAEvent {

	public static final String ID = "ANDEvent";

	private final EEAEvent[] events;

	/**
	 * Creates a new ANDEvent.
	 * 
	 * @param events The array of events that make up this new event.
	 */
	public ANDEvent(EEAEvent... events) {
		super(ID);
		this.events = events;
	}

	/**
	 * Returns true when every single event is triggered.
	 * @param delta The elapsed time in seconds.
	 */
	@Override
	public boolean eventTriggered(float delta) {
		for (EEAEvent event : events) {
			if (!event.eventTriggered(delta))
				return false;
		}
		return true;
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
