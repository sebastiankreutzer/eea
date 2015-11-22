package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IMinesAmmo;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;

public class ChangeMineAmmoAction implements Action {
	
	private int value;

	public ChangeMineAmmoAction(int value){
		this.value = value;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		Entity entity = event.getOwnerEntity();
		if(IMinesAmmo.class.isInstance(entity)){
			IMinesAmmo  ammo = (IMinesAmmo) entity;
			ammo.changeMinesAmmo(value);
		}

	}

}
