package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.entity.Entity;

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

	public Entity getCollidedEntity() {
		return collidedEntity;
	}

}
