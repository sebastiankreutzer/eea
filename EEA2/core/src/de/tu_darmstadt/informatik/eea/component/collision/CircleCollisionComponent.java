package de.tu_darmstadt.informatik.eea.component.collision;

public class CircleCollisionComponent extends CircleTriggerComponent {
	
	public final static String ID = "CircleCollisionComponent";
	
	public CircleCollisionComponent() {
		super(ID);
	}
	
	@Override
	public boolean collide(EEACollisionComponent other) {
		return other.collideWithCircle(this);
	}
}