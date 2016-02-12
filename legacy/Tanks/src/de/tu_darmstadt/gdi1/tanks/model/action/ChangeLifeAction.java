package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.ILife;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.CollisionEvent;

public class ChangeLifeAction implements Action {
	
	private int strength;
	
	public ChangeLifeAction(int strength){
		this.strength = strength;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		if(CollisionEvent.class.isInstance(event)){
			CollisionEvent colider = (CollisionEvent) event;
			Entity entity = colider.getColidedEntity();
			if(ILife.class.isInstance(entity)){
				ILife life = (ILife) entity;
				life.changeLife(strength);
				StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
			}
		}
	}

}
