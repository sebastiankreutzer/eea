package de.tu_darmstadt.gdi1.tanks.model.event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IMinesAmmo;

import eea.engine.entity.Entity;
import eea.engine.event.Event;

public class HasMinesAmmoLeftEvent extends Event {

	public HasMinesAmmoLeftEvent() {
		super("HasMinesAmmoLeftEvent");
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		Entity entity = getOwnerEntity();
		if(IMinesAmmo.class.isInstance(entity)){
			return ((IMinesAmmo)entity).hasMinesLeft();
		} else return false; 
	}

}
