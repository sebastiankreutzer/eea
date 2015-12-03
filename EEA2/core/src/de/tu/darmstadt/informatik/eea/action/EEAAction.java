package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public abstract class EEAAction extends Action {

	@Override
	public void setActor(Actor actor) {
		if (actor != null && !(actor instanceof Entity)) {
			throw new IllegalArgumentException("EEAAction only supports Entities");
		}
		super.setActor(actor);
	}
	
	public Entity getEntity() {
		return (Entity) getActor();
	}

	
	

}
