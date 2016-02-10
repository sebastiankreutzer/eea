package de.tu_darmstadt.informatik.eea.entity.component.collision;

import de.tu_darmstadt.informatik.eea.entity.EEAComponent;

public abstract class EEACollisionComponent extends EEAComponent {

	public EEACollisionComponent(String componentID) {
		super(componentID);
	}

	@Override
	public boolean update(float delta) {
		return true;
	}
	
	@Override
	public void onAddComponent() {
		super.onAddComponent();
		sizeChanged();
	}
	
	public void sizeChanged() {
		
	}

	public abstract boolean collide(EEACollisionComponent other);

	protected abstract boolean collideWithBorder(BorderCollisionComponent other);

	protected abstract boolean collideWithCircle(CircleCollisionComponent other);
	
	protected abstract boolean collideWithRectangle(RectangleTriggerComponent other);

}
