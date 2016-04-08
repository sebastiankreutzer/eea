package de.tu_darmstadt.informatik.eea.event;

import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * 
 * This event is triggered when the target entity is destroyed.
 * 
 * @author Johann Reinhard
 *
 */
public class EntityDestroyedEvent extends EEAEvent {

	public static final String ID = "EntityDestroyedEvent";

	private Entity entity;

	/**
	 * Creates a new EntityDestroyedEvent.
	 * @param target The target entity.
	 */
	public EntityDestroyedEvent(Entity target) {
		super(ID);
		entity = target;
	}

	@Override
	public boolean eventTriggered(float delta) {
		if (!entity.isManaged()) {
			return true;
		} else {
			return false;
		}
	}

}
