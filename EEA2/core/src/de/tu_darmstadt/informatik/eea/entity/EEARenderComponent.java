package de.tu_darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * A template for components that determine the graphical representation of an
 * entity.
 * 
 * @author jr
 *
 */
public abstract class EEARenderComponent extends EEAComponent {

	/**
	 * Creates a new EEARenderComponent.
	 * 
	 * @param componentID
	 *            The ID for this component.
	 */
	public EEARenderComponent(String componentID) {
		super(componentID);
	}

	@Override
	public boolean update(float delta) {
		return true;
	}

	/**
	 * The component should be drawn in this call.
	 * 
	 * @param batch
	 *            The batch to draw on.
	 */
	public abstract void render(Batch batch);

}
