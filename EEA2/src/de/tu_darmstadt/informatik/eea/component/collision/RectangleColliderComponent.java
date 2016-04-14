package de.tu_darmstadt.informatik.eea.component.collision;

/**
 * This component enables collision detection for rectangular entities. The
 * dimensions will be determined by the entities size.
 * 
 * @author jr
 *
 */
public class RectangleColliderComponent extends RectangleTriggerComponent {
	
	public static final String ID = "RectangleColliderComponent";

	/**
	 * Creates a new RectangleCollisionComponent.
	 */
	public RectangleColliderComponent() {
		super(ID);
	}

	@Override
	public boolean collide(EEACollisionComponent other) {
		return other.collideWithRectangle(this);
	}

}
