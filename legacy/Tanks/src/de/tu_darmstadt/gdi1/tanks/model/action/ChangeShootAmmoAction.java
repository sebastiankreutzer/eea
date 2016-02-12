package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IShootAmmo;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;

public class ChangeShootAmmoAction implements Action {
	
	private int value;
	
	public ChangeShootAmmoAction(int value){
		this.value = value;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		Entity entity = event.getOwnerEntity();
		if(IShootAmmo.class.isInstance(entity)){
			IShootAmmo  ammo = (IShootAmmo) entity;
			ammo.changeShootAmmo(value);
		}

	}

}
