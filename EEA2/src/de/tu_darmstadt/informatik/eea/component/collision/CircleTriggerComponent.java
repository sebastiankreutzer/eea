package de.tu_darmstadt.informatik.eea.component.collision;

import com.badlogic.gdx.utils.Align;

/**
 * This component enables triggering on collisions for circular entities. The
 * dimension will be determined by the entities size.
 * 
 * @author jr
 *
 */
public class CircleTriggerComponent extends EEACollisionComponent {

	public final static String ID = "CircleCollisionComponent";

	float radius;

	/**
	 * Creates a new CircleTriggerComponent.
	 */
	public CircleTriggerComponent() {
		super(ID);
	}

	/**
	 * Creates a new CircleTriggerComponent.
	 * 
	 * @param id
	 *            The ID for this component.
	 */
	protected CircleTriggerComponent(String id) {
		super(id);
	}

	@Override
	public void sizeChanged() {
		// The values could be different, to be safe choose the larger value.
		radius = Math.max(owner.getScaledWidth(), owner.getScaledHeight()) * 0.5f;
	}

	/**
	 * @return The x coordinate of the center.
	 */
	float getCenterX() {
		return owner.getX(Align.center);
	}

	/**
	 * @return The y coordinate of the center.
	 */
	float getCenterY() {
		return owner.getY(Align.center);
	}

	/**
	 * @return The radius of this circle.
	 */
	float getRadius() {
		return radius;
	}

	@Override
	public boolean collide(EEACollisionComponent other) {
		return false;
	}

	@Override
	protected boolean collideWithBorder(BorderTriggerComponent borderCollisionComponent) {
		return borderCollisionComponent.collideWithCircle(this);
	}

	@Override
	protected boolean collideWithCircle(CircleTriggerComponent other) {
		// Determine the squared distance between the centers.
		float deltaX = getCenterX() - other.getCenterX();
		float deltaY = getCenterY() - other.getCenterY();
		float distance = deltaX * deltaX + deltaY * deltaY;
		
		// Compare this to the squared sum of the radix and return the result.
		float radius = getRadius() + other.getRadius();
		float minDistance = radius * radius;
		return minDistance > distance;
	}

	@Override
	protected boolean collideWithRectangle(RectangleTriggerComponent other) {
		return other.collideWithCircle(this);
	}

}