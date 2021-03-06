package de.tu_darmstadt.informatik.eea.component.collision;

/**
 * This CollisionComponent does not trigger or detect any collision.
 * 
 * @author jr
 *
 */
public class NoCollisionComponent extends EEACollisionComponent {

	public final static String ID = "NoCollisionComponent";

	public NoCollisionComponent() {
		super(ID);
	}

	@Override
	public void sizeChanged() {
		// Nothing to do here.
	}

	@Override
	public boolean collide(EEACollisionComponent other) {
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
