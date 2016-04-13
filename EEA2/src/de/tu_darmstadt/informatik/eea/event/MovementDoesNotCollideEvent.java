package de.tu_darmstadt.informatik.eea.event;

import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * This event is triggered when the given
 * {@link de.tu_darmstadt.informatik.eea.action.EEAMovement} can be applied to
 * the owning entity without a collision occurring.
 * 
 * @author Johann Reinhard
 *
 */
public class MovementDoesNotCollideEvent extends EEAEvent {

	private static final String ID = "MovementDoesNotCollideEvent";

	private EEAMovement move;

	/**
	 * Creates a new MovementDoesNotCollideEvent.
	 * 
	 * @param move
	 *            The EEAMovement to test with.
	 */
	public MovementDoesNotCollideEvent(EEAMovement move) {
		super(ID);
		this.move = move;
	}

	/**
	 * Sets the EEAMovement that is used for collision testing.
	 * 
	 * @param movement
	 *            The new EEAMovement.
	 */
	public void setMovement(EEAMovement movement) {
		this.move = movement;
		setAction(0, movement);
	}

	@Override
	public boolean eventTriggered(float delta) {
		// Temporary save the current position and rotation
		float x = owner.getX();
		float y = owner.getY();
		float rot = owner.getRotation();

		// Perform the move and get the colliding entity
		move.act(delta);
		Entity other = owner.collides();

		// Restore the position and rotation
		owner.setPosition(x, y);
		owner.setRotation(rot);
		// Return whether the colliding object is null and therefore no
		// collision occurred
		return (other == null);
	}

}
