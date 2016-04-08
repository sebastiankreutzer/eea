package de.tu_darmstadt.informatik.eea.component.collision;

/**
 * This component enables collision detection for circular entities. The
 * dimension will be determined by the entities size.
 * 
 * @author jr
 *
 */
public class CircleCollisionComponent extends CircleTriggerComponent {

	public final static String ID = "CircleCollisionComponent";

	/**
	 * Creates a new CircleCollisionComponent.
	 */
	public CircleCollisionComponent() {
		super(ID);
	}

	@Override
	public boolean collide(EEACollisionTriggerComponent other) {
		return other.collideWithCircle(this);
	}
}