package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IMinesAmmo;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.IShootAmmo;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.CollisionEvent;

public class ChangeAmmoAction implements Action {
	
	private int strength;
	
	public ChangeAmmoAction(int strength){
		this.strength = strength;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		if(CollisionEvent.class.isInstance(event)){
			CollisionEvent colider = (CollisionEvent) event;
			Entity entity = colider.getColidedEntity();
			if(IShootAmmo.class.isInstance(entity) && IMinesAmmo.class.isInstance(entity)){
				IShootAmmo shoot = (IShootAmmo) entity;
				shoot.changeShootAmmo(strength);
				
				IMinesAmmo mines = (IMinesAmmo) entity;
				mines.changeMinesAmmo((int)(strength/2));
				
				StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
			}
		}

	}

}
