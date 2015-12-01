package de.tu.darmstadt.informatik.eea.event;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.EEAMovement;
import de.tu.darmstadt.informatik.eea.entity.Entity;

public class MovementDoesNotCollideEvent extends EEAEvent {
	
	private static final String ID = "MovementDoesNotCollideEvent";
	
	private float speed;
	EEAMovement move;
	
	public MovementDoesNotCollideEvent(float currentSpeed, EEAMovement move) {
		super(ID);
	    speed = currentSpeed;
	    addAction(move);
	    this.move = move;
	}

	@Override
	public boolean eventTriggered(float delta) {
		// TODO Fix collision handling
	    Vector2 position = move.getNextPosition(delta);

	    // now, determine if there is a collision between this
	    // "clone of the owning entity" and another object
	    //Entity collider = em.collides(sb.getCurrentStateID(), entity);
	    
	    Entity collider = null;

	    // if this is not the case, or the colliding entity can be passed
	    // without an effect, indicate that the movement can be performed.
	    if (collider == null) {
	      return true; // no collision => go ahead
	    } else {
	      return (collider.isPassable()); // collision "relevant" or not?
	    }
	}

}
