package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

/**
 * This action template provides a consistent way of predicting movements, which
 * is useful to check for collisions or AI support.
 * 
 * @author Johann Reinhard
 *
 */
public abstract class EEAMovement extends EEAAction {

	protected Vector2 pos = new Vector2();
	protected int alignment;

	/**
	 * Creates a new EEAMovement with the default bottom left alignment.
	 */
	public EEAMovement() {
		this(Align.bottomLeft);
	}

	/**
	 * Creates a new EEAMovement with the specified alignment.
	 * 
	 * @param alignment
	 *            The {@link Align} for this entity.
	 */
	public EEAMovement(int alignment) {
		this.alignment = alignment;
	}

	/**
	 * Returns the next Position for this movement.
	 * 
	 * @param delta
	 *            The elapsed time in seconds since the last evaluation.
	 * @return The position the entity will be.
	 */
	public abstract Vector2 getNextPosition(float delta);

	@Override
	public boolean act(float delta) {
		pos = getNextPosition(delta);
		getActor().setPosition(pos.x, pos.y, alignment);
		return true;
	}

}
