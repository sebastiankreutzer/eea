package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * EEAActions are used to alter or act a {@link de.tu_darmstadt.informatik.eea.entity.Entity}.
 * Each action has one actor and may have a target entity. Actions can be added to a {@link de.tu_darmstadt.informatik.eea.event.EEAEvent}.
 * @author Sebastian Kreutzer
 * @version 2.0
 */
public abstract class EEAAction extends Action {

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Action#setActor(com.badlogic.gdx.scenes.scene2d.Actor)
	 */
	@Override
	public void setActor(Actor actor) {
		if (actor != null && !(actor instanceof Entity)) {
			throw new IllegalArgumentException("EEAAction only supports EEAEntities");
		}
		super.setActor(actor);
	}
	
	/**
	 * Returns the {@link de.tu_darmstadt.informatik.eea.entity.Entity} that is registered as this actions actor.
	 * @return The entity registered as this actions actor.
	 */
	public Entity getEntity() {
		return (Entity) getActor();
	} 	

}
