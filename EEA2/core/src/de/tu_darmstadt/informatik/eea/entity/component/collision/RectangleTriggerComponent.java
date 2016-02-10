package de.tu_darmstadt.informatik.eea.entity.component.collision;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

public class RectangleTriggerComponent extends EEACollisionComponent {

	public static final String ID = "RectangleTriggerComponent";
	protected float maxRange;
	float minRange;
	protected float width;
	protected float height;

	public RectangleTriggerComponent() {
		super(ID);
	}

	public RectangleTriggerComponent(String id) {
		super(ID);
	}

	public void sizeChanged() {
		width = owner.getScaledWidth();
		height = owner.getScaledHeight();
		maxRange = width * width + height * height;
		maxRange = 0.5f * (float) Math.sqrt(maxRange);
		minRange = 0.5f * Math.min(width, height);
	}

	public float getMaxRange() {
		return maxRange;
	}

	public float getCenterX() {
		return owner.getX(Align.center);
	}

	public float getCenterY() {
		return owner.getY(Align.center);
	}
	
	@Override
	public boolean collide(EEACollisionComponent other) {
		return false;
	}

	@Override
	protected boolean collideWithBorder(BorderCollisionComponent other) {
		return other.collideWithRectangle(this);
	}

	@Override
	protected boolean collideWithCircle(CircleCollisionComponent other) {
		Vector2 delta = new Vector2();
		delta.x = other.getCenterX() - getCenterX();
		delta.y = other.getCenterY() - getCenterY();
	
		float distance = delta.x * delta.x + delta.y * delta.y;
		float r = maxRange + other.radius;
		if (distance > r * r)
			return false;
	
		r = minRange + other.radius;
		if (r * r > distance) {
			return true;
		} else {
			Vector2 v = new Vector2();
			double rot = -1 * Math.toRadians(getOwnerEntity().getRotation());
			float sin = (float) Math.sin(rot);
			float cos = (float) Math.cos(rot);
			v.x = cos * delta.x - sin * delta.y;
			v.y = sin * delta.x + cos * delta.y;
	
			boolean insideX = (v.x + other.radius > width * -0.5f && v.x - other.radius < width * 0.5f);
			boolean insideY = (v.y + other.radius > height * -0.5f && v.y - other.radius < height * 0.5f);
	
			return (insideX && insideY);
		}
	}

	@Override
	protected boolean collideWithRectangle(RectangleTriggerComponent other) {
		float deltaX = getCenterX() - other.getCenterX();
		float deltaY = getCenterY() - other.getCenterY();
	
		float distance = deltaX * deltaX + deltaY * deltaY;
		float r = maxRange + other.maxRange;
		if (distance > r * r)
			return false;
	
		r = minRange + other.minRange;
		if (distance < r * r)
			return true;
	
		// First project into this local coordinate system.
	
		// Get the untransformed coordinates
		Vector2[] v1 = baseRect(width, height);
	
		// Get the rotation of this and calculate sin and cos
		double rotation = Math.toRadians(getOwnerEntity().getRotation());
		float sin1 = (float) Math.sin(rotation);
		float cos1 = (float) Math.cos(rotation);
	
		// Rotate
		rotate(v1, sin1, cos1);
	
		// Move
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
	 * Rotates the vectors around the given Vectors around the center.
	 * @param v The array of vectors.
	 * @param sin The sinus of the rotation.
	 * @param cos The cosinus of the rotation.
	 */
	private void rotate(Vector2[] v, float sin, float cos) {
		for (Vector2 vector : v) {
			vector.set(cos * vector.x - sin * vector.y, sin * vector.x + cos * vector.y);
		}
	}

	/**
	 * Moves the vectors.
	 * @param v The array of vectors.
	 * @param x The x value.
	 * @param y The y value.
	 */
	private void move(Vector2[] v, float x, float y) {
		for (Vector2 vector : v) {
			vector.add(x, y);
		}
	}

}