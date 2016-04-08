package de.tu_darmstadt.informatik.eea.component.collision;

public class RectangleCollisionComponent extends RectangleTriggerComponent {
	
	public static final String ID = "RectangleCollisionComponent";

	public RectangleCollisionComponent() {
		super(ID);
	}

	@Override
	public boolean collide(EEACollisionComponent other) {
		return other.collideWithRectangle(this);
	}

}
