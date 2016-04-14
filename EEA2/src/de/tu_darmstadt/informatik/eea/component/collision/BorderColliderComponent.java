package de.tu_darmstadt.informatik.eea.component.collision;

/**
 * This component enables collision detection for entities representing a
 * border. The entities position defines the position of the border.
 * 
 * @author johan
 *
 */
public class BorderColliderComponent extends BorderTriggerComponent {

	public static final String ID = "BorderColliderComponent";

	/**
	 * Creates a new BorderColliderComponent.
	 * 
	 * @param border
	 *            The {@link Border} determining the direction of this border.
	 */
	public BorderColliderComponent(Border border) {
		super(ID, border);
	}

	@Override
	public boolean collide(EEACollisionComponent other) {
		return other.collideWithBorder(this);
	}

}
