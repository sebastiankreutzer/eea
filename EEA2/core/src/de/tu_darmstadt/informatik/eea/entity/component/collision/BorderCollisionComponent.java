package de.tu_darmstadt.informatik.eea.entity.component.collision;

import com.badlogic.gdx.utils.Align;

import de.tu_darmstadt.informatik.eea.entity.Entity;

import static com.badlogic.gdx.utils.Align.*;

import com.badlogic.gdx.math.Vector2;

public class BorderCollisionComponent extends EEACollisionComponent {

	public final static String ID = "BorderCollisionComponent";

	private final int border;

	/**
	 * @param componentID
	 * @param border
	 *            Integer from libGDX Align.
	 * @param position
	 */
	public BorderCollisionComponent(int border) {
		super(ID);
		this.border = border;
	}

	@Override
	public boolean collide(EEACollisionComponent other) {
		return other.collideWithBorder(this);
	}
	
	@Override
	protected boolean collideWithBorder(BorderCollisionComponent other) {
		return false;
	}

	@Override
	protected boolean collideWithCircle(CircleCollisionComponent other) {
		switch (border) {
		case top:
			return (other.getCenterY() + other.getRadius() > owner.getY());

		case bottom:
			return (other.getCenterY() - other.getRadius() < owner.getY());

		case left:
			return (other.getCenterX() - other.getRadius() < owner.getX());

		case right:
			return (other.getCenterX() + other.getRadius() > owner.getX());
		}
		return false;
	}

	@Override
	protected boolean collideWithRectangle(RectangleTriggerComponent other) {
		switch (border) {

		case bottom:
			if (other.getCenterY() - other.maxRange > owner.getY())
				return false;
			else {
				Vector2 rot = calcRot(other.getOwnerEntity().getRotation());
				Vector2 y = calcY(rot.x, rot.y, other.width, other.height);
				return (other.getCenterY() - Math.max(y.x, y.y) < owner.getY());
			}

		case top:
			if (other.getCenterY() + other.maxRange < owner.getY())
				return false;
			else {
				Vector2 rot = calcRot(other.getOwnerEntity().getRotation());
				Vector2 y = calcY(rot.x, rot.y, other.width, other.height);
				return (other.getCenterY() + Math.max(y.x, y.y) > owner.getY());
			}

		case left:
			if (other.getCenterX() - other.maxRange > owner.getX())
				return false;
			else {
				Vector2 rot = calcRot(other.getOwnerEntity().getRotation());
				Vector2 x = calcX(rot.x, rot.y, other.width, other.height);
				return (other.getCenterX() - Math.max(x.x, x.y) < owner.getX());
			}

		case right:
			if (other.getCenterX() - other.maxRange < owner.getX())
				return false;
			else {
				Vector2 rot = calcRot(other.getOwnerEntity().getRotation());
				Vector2 x = calcX(rot.x, rot.y, other.width, other.height);
				return (other.getCenterX() + Math.max(x.x, x.y) > owner.getX());
			}
		}
		return false;
	}

	private Vector2 calcRot(double rotation) {
		rotation = rotation % 180;
		rotation = Math.toRadians(rotation);
		return new Vector2((float) Math.sin(rotation), (float) Math.cos(rotation));
	}

	private Vector2 calcY(float sin, float cos, float width, float height) {
		Vector2 y = new Vector2();
		y.x = 0.5f * (float) (sin * width + cos * height);
		y.y = 0.5f * (float) (sin * width - cos * height);
		return y;
	}

	private Vector2 calcX(float sin, float cos, float width, float height) {
		Vector2 x = new Vector2();
		x.x = 0.5f * (float) (cos * width - sin * height);
		x.y = 0.5f * (float) (cos * width + sin * height);
		return x;
	}

}
