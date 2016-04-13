package de.tu_darmstadt.informatik.eea.component.collision;

import de.tu_darmstadt.informatik.eea.component.EEAComponent;

/**
 * A component class template for collision detection. The derived classes
 * usually come in two flavors, trigger and collision. The former detects
 * collisions with other CollisionComponents, but other components wont detect a
 * collision with that component. The latter detects collisions and can also be
 * detected.
 * 
 * @author jr
 *
 */
public abstract class EEACollisionComponent extends EEAComponent {

	/**
	 * Creates a new EEACollisionComponent.
	 * 
	 * @param componentID
	 *            The component ID.
	 */
	public EEACollisionComponent(String componentID) {
		super(componentID);
	}

	@Override
	public boolean update(float delta) {
		return true;
	}

	@Override
	public void onAddComponent() {
		super.onAddComponent();
		sizeChanged();
	}

	/**
	 * This method is usually automatically called when the Entity is resized
	 * and updates this EEACollisionTriggerComponent fields.
	 */
	public abstract void sizeChanged();

	/**
	 * Determines whether this CollisionComponent collides with another
	 * {@link EEACollisionComponent}.
	 * 
	 * @param other
	 *            The other CollisionComponent.
	 * @return true if the components collide, otherwise false.
	 */
	public abstract boolean collide(EEACollisionComponent other);

	/**
	 * Determines whether this CollisionComponent collides with another
	 * {@link BorderColliderComponent}.
	 * 
	 * @param other
	 *            The other BorderCollisionComponent.
	 * @return true if the components collide, otherwise false.
	 */
	protected abstract boolean collideWithBorder(BorderTriggerComponent other);

	/**
	 * Determines whether this CollisionComponent collides with another
	 * {@link CircleTriggerComponent}.
	 * 
	 * @param other
	 *            The other CircleTriggerComponent.
	 * @return true if the components collide, otherwise false.
	 */
	protected abstract boolean collideWithCircle(CircleTriggerComponent other);

	/**
	 * Determines whether this CollisionComponent collides with another
	 * {@link RectangleTriggerComponent}.
	 * 
	 * @param other
	 *            The other RectangleTriggerComponent.
	 * @return true if the components collide, otherwise false.
	 */
	protected abstract boolean collideWithRectangle(RectangleTriggerComponent other);

}
