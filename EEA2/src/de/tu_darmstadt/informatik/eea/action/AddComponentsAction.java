package de.tu_darmstadt.informatik.eea.action;

import de.tu_darmstadt.informatik.eea.component.EEAComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * This action adds one or more {@link EEAComponent} to an Entity. Note that
 * this action does not keep track whether it already acted.
 * 
 * @author Johann Reinhard
 *
 */
public class AddComponentsAction extends EEAAction {

	private EEAComponent[] components;
	private Entity entity;

	/**
	 * @param entity
	 *            The {@link Entity} the components will be added to.
	 * @param components
	 *            The {@link EEAComponent} that will be added to the entity.
	 */
	public AddComponentsAction(Entity entity, EEAComponent... components) {
		this.components = components;
		this.entity = entity;
	}

	@Override
	public boolean act(float delta) {
		for (EEAComponent component : components) {
			entity.addComponent(component);
		}
		return true;
	}

}
