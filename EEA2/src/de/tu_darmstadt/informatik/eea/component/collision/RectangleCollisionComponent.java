package de.tu_darmstadt.informatik.eea.component.collision;

/**
 * This component enables collision detection for rectangular entities. The
 * dimensions will be determined by the entities size.
 * 
 * @author jr
 *
 */
public class RectangleCollisionComponent extends RectangleTriggerComponent {
	
	public static final String ID = "RectangleCollisionComponent";

	/**
	 * Creates a new RectangleCollisionComponent.
	 */
	public RectangleCollisionComponent() {
		super(ID);
	}

	@Override
	public boolean collide(EEACollisionTriggerComponent other) {
		return other.collideWithRectangle(this);
	}

}
