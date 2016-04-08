package de.tu_darmstadt.informatik.eea.component.collision;

import javax.swing.border.Border;

public class BorderCollisionComponent extends BorderTriggerComponent {

	public static final String ID = "BorderCollisionComponent";

	/**
	 * Creates a new BorderCollisionComponent.
	 * 
	 * @param border
	 *            The {@link Border} determining the direction of this border.
	 */
	public BorderCollisionComponent(Border border) {
		super(ID, border);
	}

	@Override
	public boolean collide(EEACollisionTriggerComponent other) {
		return other.collideWithBorder(this);
	}

}
