package de.tu.darmstadt.informatik.eea.event;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.EEAMovement;
import de.tu.darmstadt.informatik.eea.entity.Entity;

public class MovementDoesNotCollideEvent extends EEAEvent {
	
	private static final String ID = "MovementDoesNotCollideEvent";
	
	EEAMovement move;
	
	public MovementDoesNotCollideEvent(float currentSpeed, EEAMovement move) {
		super(ID);
	    //speed = currentSpeed;
	    //addAction(move);
	    this.move = move;
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
