package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

public class AddComponentsAction implements Action {
	
	private Component[] components;
	
	public AddComponentsAction(Component... components){
		this.components = components;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		for(Component component : components){
			event.getOwnerEntity().addComponent(component);
		}
	}

}
