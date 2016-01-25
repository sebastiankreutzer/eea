package de.tu_darmstadt.informatik.eea.event;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.eea.entity.Entity;

public class MovementDoesNotCollideEvent extends EEAEvent {
	
	private static final String ID = "MovementDoesNotCollideEvent";
	
	EEAMovement move;
	
	public MovementDoesNotCollideEvent(EEAMovement move) {
		super(ID);
	    this.move = move;
	}
	
	public void setMovement(EEAMovement movement) {
		this.move = movement;
		setAction(0, movement);
	}

	@Override
	public boolean eventTriggered(float delta) {
	    Vector2 oldPosition = new Vector2(owner.getX(), owner.getY());
	    float oldRotation = owner.getRotation();
	    
	    move.act(delta);

	    List<Entity> colliders = owner.getManager().getAllCollisions(owner);

	    boolean collisionOccured = false;
	    for (Entity c : colliders) {
	    	if (!c.isPassable()) {
	    		collisionOccured = true;
	    		break;
	    	}
	    }
	    
	    owner.setPosition(oldPosition.x, oldPosition.y);
	    owner.setRotation(oldRotation);
	    return !collisionOccured;
	}

}
