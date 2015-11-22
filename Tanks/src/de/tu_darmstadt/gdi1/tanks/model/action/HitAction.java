package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;


import de.tu_darmstadt.gdi1.tanks.model.factory.ExplosionFactory;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.ILife;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.CollisionEvent;

public class HitAction implements Action {
	
	private int strength;
	
	public HitAction(int streangth){
		this.strength = streangth;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		if(CollisionEvent.class.isInstance(event)){
			CollisionEvent colider = (CollisionEvent) event;
			Entity entity = colider.getColidedEntity();
			if(ILife.class.isInstance(entity)){
				ILife life = (ILife) entity;
				life.changeLife(-strength);
				StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
				
				Entity explosion = new Entity("Explosion");
				explosion =new ExplosionFactory(event.getOwnerEntity().getPosition().x-entity.getSize().y/2, event.getOwnerEntity().getPosition().y-entity.getSize().y/2, 
						0.01f, entity.getSize().y, entity.getSize().y, Tanks.debug).createEntity();
				
				StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), explosion);
				if(!life.hasLifeLeft()) {
					
					StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), entity);
				}
			}
		}

	}

}
