package de.tu_darmstadt.informatik.eea.entity.component.collision;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

/**
 * This component enables triggering on collisions for rectangular entities. The
 * dimensions will be determined by the entities size.
 * 
 * @author jr
 *
 */
public class RectangleTriggerComponent extends EEACollisionTriggerComponent {

	public static final String ID = "RectangleTriggerComponent";

	protected float width, height;
	protected float maxRange, minRange;

	/**
	 * Creates a new RectangleTriggerComponent.
	 */
	public RectangleTriggerComponent() {
		super(ID);
	}

	/**
	 * Creates a new RectangleTriggerComponent with a specified ID.
	 * 
	 * @param id
	 *            The ID for this component.
	 */
	protected RectangleTriggerComponent(String id) {
		super(id);
	}

	public void sizeChanged() {
		width = owner.getScaledWidth();
		height = owner.getScaledHeight();
		maxRange = width * width + height * height;
		maxRange = 0.5f * (float) Math.sqrt(maxRange);
		minRange = 0.5f * Math.min(width, height);
	}

	/**
	 * @return The x position of its center.
	 */
	public float getCenterX() {
		return owner.getX(Align.center);
	}

	/**
	 * @return The y position of its center.
	 */
	public float getCenterY() {
		return owner.getY(Align.center);
	}
	
	/**
	 * @return The maximal range for this rectangle from its center.
	 */
	public float getMaxRange() {
		return maxRange;
	}

	/**
	 * @return The rotation of this rectangle.
	 */
	public float getRotation() {
		return owner.getRotation();
	}

	@Override
	public boolean collide(EEACollisionTriggerComponent other) {
		return false;
	}

	@Override
	protected boolean collideWithBorder(BorderTriggerComponent other) {
		return other.collideWithRectangle(this);
	}

	@Override
	protected boolean collideWithCircle(CircleTriggerComponent other) {
		// Calculate the squared distance between the centers.
		Vector2 delta = new Vector2();
		delta.x = other.getCenterX() - getCenterX();
		delta.y = other.getCenterY() - getCenterY();
		float distance = delta.x * delta.x + delta.y * delta.y;

		// Check if the distance is larger than the combined maximal range.
		float r = maxRange + other.radius;
		if (distance > r * r)
			return false;

		// Check if the distance is smaller than the combined minimal range.
		r = minRange + other.radius;
		if (r * r > distance) {
			return true;
		}

		// Else project circle into the rectangles local coordinate system.
		double rot = -1 * Math.toRadians(getRotation());
		float sin = (float) Math.sin(rot);
		float cos = (float) Math.cos(rot);
		Vector2 v = new Vector2(cos * delta.x - sin * delta.y, sin * delta.x + cos * delta.y);

		// Check whether overlap in x and y axis.
		boolean insideX = (v.x + other.radius > width * -0.5f && v.x - other.radius < width * 0.5f);
		boolean insideY = (v.y + other.radius > height * -0.5f && v.y - other.radius < height * 0.5f);
		// Return result.
		return (insideX && insideY);
	}

	@Override
	protected boolean collideWithRectangle(RectangleTriggerComponent other) {
		// Calculate the squared distance between the centers.
		float deltaX = getCenterX() - other.getCenterX();
		float deltaY = getCenterY() - other.getCenterY();
		float distance = deltaX * deltaX + deltaY * deltaY;

		// Check if the distance is larger than the combined maximal range.
		float r = maxRange + other.maxRange;
		if (distance > r * r) {
			return false;
		}

		// Check if the distance is smaller than the combined minimal range.
		r = minRange + other.minRange;
		if (distance < r * r) {
			return true;
		}

		// Else project into this local coordinate system.

		// Get the rotation of this and calculate sin and cos
		double rotation = Math.toRadians(getOwnerEntity().getRotation());
		float sin1 = (float) Math.sin(rotation);
		float cos1 = (float) Math.cos(rotation);

		// Get the untransformed coordinates
		Vector2[] v1 = baseRect(width, height);
		// Rotate and move
		rotate(v1, sin1, cos1);
		move(v1, deltaX, deltaY);

		// Get the rotation of other and calculate sin and cos
		rotation = Math.toRadians(other.getOwnerEntity().getRotation());
		float sin2 = (float) Math.sin(rotation);
		float cos2 = (float) Math.cos(rotation);

		// Rotate this with the negative values of other
		rotate(v1, -sin2, cos2);

		// Find min and max values for x and y
		float minY = Float.MAX_VALUE;
		float maxY = -Float.MAX_VALUE;
		float minX = Float.MAX_VALUE;
		float maxX = -Float.MAX_VALUE;

		for (Vector2 v : v1) {
			minY = Math.min(minY, v.y);
			maxY = Math.max(maxY, v.y);
			minX = Math.min(minX, v.x);
			maxX = Math.max(maxX, v.x);
		}

		// Test for overlap on x-Axis
		if (minX > other.width * 0.5f || maxX < other.width * -0.5f) {
			return false; // No overlap
		}

		// Test for overlap on y-Axis
		if (minY > other.height * 0.5f || maxY < other.height * -0.5f) {
			return false; // No overlap
		}

		// Now do the same in the other local system

		// Get the untransformed coordinates
		v1 = baseRect(other.width, other.height);

		// Rotate
		rotate(v1, sin2, cos2);

		// Move, this time in the opposite direction
		move(v1, -deltaX, -deltaY);

		// Rotate other with the negative values of this
		rotate(v1, -sin1, cos1);

		// Find min and max values for x and y
		minY = Float.MAX_VALUE;
		maxY = -Float.MAX_VALUE;
		minX = Float.MAX_VALUE;
		maxX = -Float.MAX_VALUE;

		for (Vector2 v : v1) {
			minY = Math.min(minY, v.y);
			maxY = Math.max(maxY, v.y);
			minX = Math.min(minX, v.x);
			maxX = Math.max(maxX, v.x);
		}

		// Test for overlap on x-Axis
		if (minX > width * 0.5f || maxX < width * -0.5f) {
			return false; // No overlap
		}

		// Test for overlap on y-Axis
		if (minY > height * 0.5f || maxY < height * -0.5f) {
			return false; // No overlap
		}

		return true; // Overlap on all projections
	}

	/**
	 * Returns the coordinates of the corners of a rectangle centered on the
	 * origin. The width determines the x-Axis and the height the y-axis.
	 * 
	 * @param width
	 *            The rectangles width.
	 * @param height
	 *            The rectangels height.
	 * @return A Vector2 array with length four, that contains the corners
	 *         positions.
	 */
	private Vector2[] baseRect(float width, float height) {
		Vector2[] v = new Vector2[4];
		width *= 0.5f;
		height *= 0.5f;

		v[0] = new Vector2(width, height);
		v[1] = new Vector2(-width, height);
		v[2] = new Vector2(width, -height);
		v[3] = new Vector2(-width, -height);

		return v;
	}

	/**
	 * Rotates an array of vectors around the given Vectors around the center (0,0).
	 * 
	 * @param v
	 *            The array of vectors.
	 * @param sin
	 *            The sine of the rotation.
	 * @param cos
	 *            The cosine of the rotation.
	 */
	private void rotate(Vector2[] v, float sin, float cos) {
		for (Vector2 vector : v) {
			vector.set(cos * vector.x - sin * vector.y, sin * vector.x + cos * vector.y);
		}
	}

	/**
	 * Moves an array of vectors.
	 * 
	 * @param v
	 *            The array of vectors.
	 * @param x
	 *            The x value.
	 * @param y
	 *            The y value.
	 */
	private void move(Vector2[] v, float x, float y) {
		for (Vector2 vector : v) {
			vector.add(x, y);
		}
	}

}