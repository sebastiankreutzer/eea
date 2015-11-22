package de.tu_darmstadt.gdi1.tanks.model.event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IShootAmmo;

import eea.engine.entity.Entity;
import eea.engine.event.Event;

public class HasShootAmmoLeftEvent extends Event {

	public HasShootAmmoLeftEvent() {
		super("HasShootAmmoLeftEvent");
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		Entity entity = getOwnerEntity();
		if(IShootAmmo.class.isInstance(entity)){
			return ((IShootAmmo)entity).hasShootAmmo();
		} else return false; 
	}

}
