package de.tu_darmstadt.informatik.eea.component.collision;

public class BorderColliderComponent extends BorderTriggerComponent {

	public static final String ID = "BorderColliderComponent";

	/**
	 * Creates a new BorderCollisionComponent.
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
