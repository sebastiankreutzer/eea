package de.tu.darmstadt.informatik.eea.event;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public class CollisionEvent extends EEAEvent {
	
	public static final String ID = "CollisionEvent";
	
	private Entity collidedEntity;

	public CollisionEvent() {
		super(ID);
	}

	@Override
	public boolean eventTriggered(float delta) {
	    // determine the first entity that has collided with the owner of the event
		// TODO Fix this by giving a handle to the entity manager
		//Entity entity = em.collides(owner);
		Entity entity = null;
		
		if(entity != null){
			for(Action a : actions){
				a.setTarget(entity);
			}
		}

	    // if there is such an entity, store a reference and indicate the
	    // willingness
	    // to perform the action(s)
//	    if (entity != null) {
//	      collidedEntity = entity;
//	      return true;
//	    }

	    // else, nothing is to be done
	    return false;
	}
	
	public Entity getCollidedEntity(){
		return collidedEntity;
	}

}
