package de.tu_darmstadt.informatik.eea.entity.component.collision;

public class NoCollisionComponent extends EEACollisionTriggerComponent {
	
	public final static String ID = "NoCollisionComponent";

	public NoCollisionComponent() {
		super(ID);
	}
	
	@Override
	public void sizeChanged() {
		// Nothing to do here.
	}
	
	@Override
	public boolean collide(EEACollisionTriggerComponent other) {
		return false;
	}

	@Override
	protected boolean collideWithBorder(BorderTriggerComponent other) {
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
