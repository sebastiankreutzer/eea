package de.tu_darmstadt.informatik.eea.component.collision;

public class NoCollisionComponent extends EEACollisionComponent {
	
	public final static String ID = "NoCollisionComponent";

	public NoCollisionComponent() {
		super(ID);
	}
	
	@Override
	public boolean collide(EEACollisionComponent other) {
		return false;
	}

	@Override
	protected boolean collideWithBorder(BorderCollisionComponent other) {
		return false;
	}

	@Override
	protected boolean collideWithCircle(CircleTriggerComponent other) {
		return false;
	}

	@Override
	protected boolean collideWithRectangle(RectangleTriggerComponent other) {
		return false;
	}

}
