package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

/**
 * This action moves an entity with respect to the rotation of the entity.
 * 
 * @author Johann Reinhard
 *
 */
public class MoveRelativeAction extends EEAMovement {

	float dOrthogonal, deltaLinear;

	/**
	 * Creates a new MoveRelativeAction.
	 * 
	 * @param linear
	 *            The magnitude of the movement in the direction the entity is
	 *            facing.
	 * @param orthogonal
	 *            The magnitude of the movement orthogonal to the direction the
	 *            entity is facing.
	 */
	public MoveRelativeAction(float linear, float orthogonal) {
		super(Align.bottomLeft);
		dOrthogonal = orthogonal;
		deltaLinear = linear;
	}

	/**
	 * Creates a new MoveRelativeAction.
	 * 
	 * @param linear
	 *            The magnitude of the movement in the direction the entity is
	 *            facing.
	 * @param orthogonal
	 *            The magnitude of the movement orthogonal to the direction the
	 *            entity is facing.
	 * @param alignment
	 *            The alignment for this movement.
	 */
	public MoveRelativeAction(float linear, float orthogonal, int alignment) {
		super(alignment);
		dOrthogonal = orthogonal;
		deltaLinear = linear;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		// Get rotation and calculate sine and cosine
		double rotation = Math.toRadians(getActor().getRotation());
		float cos = (float) Math.cos(rotation);
		float sin = (float) Math.sin(rotation);

		return new Vector2((dOrthogonal * cos - deltaLinear * sin) * delta + getActor().getX(),
				(dOrthogonal * sin + deltaLinear * cos) * delta + getActor().getY());
	}

}
