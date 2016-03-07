package de.tu_darmstadt.informatik.eea.entity.component.collision;

import com.badlogic.gdx.utils.Align;

public class CircleCollisionComponent extends EEACollisionComponent {
	
	public final static String ID = "CircleCollisionComponent";
	
	float radius;
	
	public CircleCollisionComponent() {
		super(ID);
	}
	
	@Override
	public void sizeChanged() {
		radius = Math.max(owner.getScaledWidth(), owner.getScaledHeight()) * 0.5f; // To be safe choose the larger value.
	}
	
	float getCenterX() {
		return owner.getX(Align.center);
	}
	
	float getCenterY() {
		return owner.getY(Align.center);
	}
	
	float getRadius() {
		return radius;
	}

	@Override
	public boolean collide(EEACollisionComponent other) {
		return other.collideWithCircle(this);
	}

	@Override
	protected boolean collideWithBorder(BorderCollisionComponent borderCollisionComponent) {
		return borderCollisionComponent.collideWithCircle(this);
	}
	
	@Override
	protected boolean collideWithCircle(CircleCollisionComponent other) {
		float deltaX = getCenterX() - other.getCenterX();
		float deltaY = getCenterY() - other.getCenterY();
		
		float distance = deltaX * deltaX + deltaY * deltaY;
		
		float radius = getRadius() + other.getRadius();
		float minDistance = radius * radius;
		return minDistance > distance;
	}

	@Override
	protected boolean collideWithRectangle(RectangleTriggerComponent other) {
		return other.collideWithCircle(this);
	}

}