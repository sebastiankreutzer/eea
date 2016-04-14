package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * This event can be used to test for collisions with other
 * {@link de.tu_darmstadt.informatik.eea.entity.Entity}s. The event is triggered
 * and the target for each of this events actions are set to the colliding
 * entity when a collision occurs. Note that the owning entity must not have a
 * {@link de.tu_darmstadt.informatik.eea.component.collision.NoCollisionComponent}
 * , otherwise this event cannot trigger.
 * 
 * @author Johann Reinhard
 *
 */
public class CollisionEvent extends EEAEvent {

	public static final String ID = "CollisionEvent";

	protected Entity collidedEntity;

	/**
	 * Creates a new CollisionEvent. IMPORTANT! This event will not be triggered
	 * if the owning entities collision component is a NoCollisionComponent.
	 */
	public CollisionEvent() {
		super(ID);
	}

	@Override
	public boolean eventTriggered(float delta) {
		// determine the first entity that has collided with the owner of the
		// event
		Entity entity = getOwnerEntity().collides();

		if (entity != null) {
			for (Action a : actions) {
				a.setTarget(entity);
			}
			// if there is such an entity, store a reference
			collidedEntity = entity;
			return true;
		}

		// else, nothing is to be done
		return false;
	}

	/**
	 * Returns the {@link de.tu_darmstadt.informatik.eea.entity.Entity} that
	 * collided with the owning entity.
	 * 
	 * @return The entity that most recently collided with this entity or null
	 *         if there has not yet been a collision.
	 */
	public Entity getCollidedEntity() {
		return collidedEntity;
	}

}
