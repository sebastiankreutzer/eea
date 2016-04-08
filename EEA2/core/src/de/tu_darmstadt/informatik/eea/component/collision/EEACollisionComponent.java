package de.tu_darmstadt.informatik.eea.component.collision;

import de.tu_darmstadt.informatik.eea.component.EEAComponent;

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

	protected abstract boolean collideWithCircle(CircleTriggerComponent other);
	
	protected abstract boolean collideWithRectangle(RectangleTriggerComponent other);

}
