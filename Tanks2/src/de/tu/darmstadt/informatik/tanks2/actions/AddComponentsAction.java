package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.entity.Component;
import de.tu.darmstadt.informatik.eea.entity.Entity;

public class AddComponentsAction extends Action {
	
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
