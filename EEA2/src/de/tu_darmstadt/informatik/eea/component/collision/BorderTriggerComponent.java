package de.tu_darmstadt.informatik.eea.component.collision;

import com.badlogic.gdx.math.Vector2;

/**
 * This component enables triggering on collision for entities representing a
 * border. The entities position defines the position of the border.
 * 
 * @author jr
 *
 */
public abstract class BorderTriggerComponent extends EEACollisionComponent {

	public static final String ID = "BorderCollisionComponent";

	public static enum Border {
		TOP, BOTTOM, LEFT, RIGHT
	}

	protected final Border border;

	/**
	 * Creates a new BorderTriggerComponent.
	 * 
	 * @param border
	 *            The border this component represents.
	 */
	public BorderTriggerComponent(Border border) {
		this(ID, border);
	}

	/**
	 * Creates a new BorderTriggerComponent.
	 * 
	 * @param componentID
	 *            The ID for this component.
	 * @param border
	 *            The border this component represents.
	 */
	protected BorderTriggerComponent(String componentID, Border border) {
		super(componentID);
		this.border = border;
	}

	@Override
	public void sizeChanged() {
		// There is nothing to do here.
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
		// Check whether the others position with its radius are larger or
		// smaller than this owner entities position.
		switch (border) {
		case TOP:
			return (other.getCenterY() + other.getRadius() > owner.getY());

		case BOTTOM:
			return (other.getCenterY() - other.getRadius() < owner.getY());

		case LEFT:
			return (other.getCenterX() - other.getRadius() < owner.getX());

		case RIGHT:
			return (other.getCenterX() + other.getRadius() > owner.getX());

		default:
			return false;
		}
	}

	@Override
	protected boolean collideWithRectangle(RectangleTriggerComponent other) {
		// First check whether the others position and maximal range are too
		// small. Otherwise compute the maximal range with respect to the axis
		// and compare with the positions.
		switch (border) {
		case BOTTOM:
			if (other.getCenterY() - other.getMaxRange() > owner.getY())
				return false;
			else {
				Vector2 rot = calcRot(other.getRotation());
				Vector2 y = calcY(rot.x, rot.y, other.width, other.height);
				return (other.getCenterY() - Math.max(y.x, y.y) < owner.getY());
			}

		case TOP:
			if (other.getCenterY() + other.getMaxRange() < owner.getY())
				return false;
			else {
				Vector2 rot = calcRot(other.getRotation());
				Vector2 y = calcY(rot.x, rot.y, other.width, other.height);
				return (other.getCenterY() + Math.max(y.x, y.y) > owner.getY());
			}

		case LEFT:
			if (other.getCenterX() - other.getMaxRange() > owner.getX())
				return false;
			else {
				Vector2 rot = calcRot(other.getRotation());
				Vector2 x = calcX(rot.x, rot.y, other.width, other.height);
				return (other.getCenterX() - Math.max(x.x, x.y) < owner.getX());
			}

		case RIGHT:
			if (other.getCenterX() + other.getMaxRange() < owner.getX())
				return false;
			else {
				Vector2 rot = calcRot(other.getRotation());
				Vector2 x = calcX(rot.x, rot.y, other.width, other.height);
				return (other.getCenterX() + Math.max(x.x, x.y) > owner.getX());
			}
		default:
			return false;
		}
	}

	/**
	 * Calculates sine and cosine for a given rotation.
	 * 
	 * @param rotation
	 *            The rotation in degree.
	 * @return A Vector containing sine and cosine.
	 */
	private Vector2 calcRot(double rotation) {
		rotation = rotation % 360;
		rotation = Math.toRadians(rotation);
		return new Vector2((float) Math.sin(rotation), (float) Math.cos(rotation));
	}

	/**
	 * Calculates the maximal and minimal y range for a given rectangle and
	 * rotation.
	 * 
	 * @param sin
	 *            The sine for the rotation.
	 * @param cos
	 *            The cosine for the rotation.
	 * @param width
	 *            The width of the rectangle.
	 * @param height
	 *            The height of the rectangle.
	 * @return A vector containing the min and max y range.
	 */
	private Vector2 calcY(float sin, float cos, float width, float height) {
		Vector2 y = new Vector2();
		y.x = 0.5f * (float) Math.abs(sin * width + cos * height);
		y.y = 0.5f * (float) Math.abs(sin * width - cos * height);
		return y;
	}

	/**
	 * Calculates the maximal and minimal x range for a given rectangle and
	 * rotation.
	 * 
	 * @param sin
	 *            The sine for the rotation.
	 * @param cos
	 *            The cosine for the rotation.
	 * @param width
	 *            The width of the rectangle.
	 * @param height
	 *            The height of the rectangle.
	 * @return A vector containing the min and max x range.
	 */
	private Vector2 calcX(float sin, float cos, float width, float height) {
		Vector2 x = new Vector2();
		x.x = 0.5f * (float) Math.abs(cos * width - sin * height);
		x.y = 0.5f * (float) Math.abs(cos * width + sin * height);
		return x;
	}

}