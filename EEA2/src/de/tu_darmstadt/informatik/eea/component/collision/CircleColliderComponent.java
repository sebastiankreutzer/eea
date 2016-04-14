package de.tu_darmstadt.informatik.eea.component.collision;

/**
 * This component enables collision detection for circular entities. The
 * dimension will be determined by the entities size.
 * 
 * @author Johann Reinhard
 *
 */
public class CircleColliderComponent extends CircleTriggerComponent {

	public final static String ID = "CircleColliderComponent";

	/**
	 * Creates a new CircleColliderComponent.
	 */
	public CircleColliderComponent() {
		super(ID);
	}

	@Override
	public boolean collide(EEACollisionComponent other) {
		return other.collideWithCircle(this);
	}
}