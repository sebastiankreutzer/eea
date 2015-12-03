package de.tu.darmstadt.informatik.tanks2.actions;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.Component;
import de.tu.darmstadt.informatik.eea.entity.Entity;

public class AddComponentsAction extends EEAAction {
	
	private Component[] components;
	private Entity entity;
	
	public AddComponentsAction(Entity entity, Component... components){
		this.components = components;
		this.entity = entity;
	}

	@Override
	public boolean act (float delta) {
		for(Component component : components){
			entity.addComponent(component);
		}
		return true;
	}

}
