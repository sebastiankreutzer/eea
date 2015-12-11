package de.tu.darmstadt.informatik.eea.action;

import de.tu.darmstadt.informatik.eea.entity.EEAComponent;
import de.tu.darmstadt.informatik.eea.entity.Entity;

public class AddComponentsAction extends EEAAction {
	
	private EEAComponent[] components;
	private Entity entity;
	
	public AddComponentsAction(Entity entity, EEAComponent... components){
		this.components = components;
		this.entity = entity;
	}

	@Override
	public boolean act (float delta) {
		for(EEAComponent component : components){
			entity.addComponent(component);
		}
		return true;
	}

}
