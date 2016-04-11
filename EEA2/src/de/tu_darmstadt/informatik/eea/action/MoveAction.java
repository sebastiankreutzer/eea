package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

/**
 * This action moves an entity without respect for the entities rotation.
 * 
 * @author jr
 *
 */
public class MoveAction extends EEAMovement {

	protected float dX, dY;

	/**
	 * Creates a new MoveAction.
	 * 
	 * @param xDir
	 *            The magnitude of the movement in the horizontal direction.
	 * @param yDir
	 *            The magnitude of the movement in the vertical direction.
	 */
	public MoveAction(float xDir, float yDir) {
		super(Align.bottomLeft);
		dX = xDir;
		dY = yDir;
	}

	/**
	 * Creates a new MoveAction.
	 * 
	 * @param xDir
	 *            The magnitude of the movement in the horizontal direction.
	 * @param yDir
	 *            The magnitude of the movement in the vertical direction.
	 * @param alignment
	 *            The alignment for this movement.
	 */
	public MoveAction(float xDir, float yDir, int alignment) {
		super(alignment);
		dX = xDir;
		dY = yDir;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		Vector2 p = new Vector2(getActor().getX(alignment), getActor().getY(alignment));
		p.add(dX * delta, dY * delta);
		return p;
	}

}
