package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * This event can be used to test for collisions with other
 * {@link de.tu_darmstadt.informatik.eea.entity.Entity}s. The actions are
 * triggered when a collision occurs. Note that the owning entity has to have a
 * valid
 * {@link de.tu_darmstadt.informatik.eea.component.collision.EEACollisionComponent}
 * .
 * 
 * @author Johann Reinhard
 *
 */
public class CollisionEvent extends EEAEvent {

	public static final String ID = "CollisionEvent";

	private Entity collidedEntity;

	/**
	 * IMPORTANT! This event will not be triggered if the owning entities
	 * collision component is a NoCollisionComponent.
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
			// if there is such an entity, store a reference and indicate the
			// willingness
			// to perform the action(s)
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
	 * @return The colliding entity.
	 */
	public Entity getCollidedEntity() {
		return collidedEntity;
	}

}
