package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.math.Vector2;

import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * This event is triggered when the given
 * {@link de.tu_darmstadt.informatik.eea.action.EEAMovement} can be applied to
 * the owning entity without a collision occuring.
 * 
 * @author Johann Reinhard
 *
 */
public class MovementDoesNotCollideEvent extends EEAEvent {

	private static final String ID = "MovementDoesNotCollideEvent";

	private EEAMovement move;

	/**
	 * Creates a new MovementDoesNotCollideEvent.
	 * @param move The EEAMovement to test with.
	 */
	public MovementDoesNotCollideEvent(EEAMovement move) {
		super(ID);
		this.move = move;
	}

	/**
	 * Sets the EEAMovement that is used for collision testing.
	 * @param movement The new EEAMovement.
	 */
	public void setMovement(EEAMovement movement) {
		this.move = movement;
		setAction(0, movement);
	}

	@Override
	public boolean eventTriggered(float delta) {
		Vector2 oldPosition = new Vector2(owner.getX(), owner.getY());
		float oldRotation = owner.getRotation();

		move.act(delta);

		Entity other = owner.collides();

		owner.setPosition(oldPosition.x, oldPosition.y);
		owner.setRotation(oldRotation);
		return (other == null);
	}

}
