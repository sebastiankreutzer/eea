package de.tu.darmstadt.informatik.tanks2.events;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.EEAMovement;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.event.MovementDoesNotCollideEvent;

public class AIMoveBackwardEvent extends MovementDoesNotCollideEvent {

	private Vector2 playerPosition;

	public AIMoveBackwardEvent(float speed, EEAMovement move) {
		super(speed, move);
		this.id = "AIMoveForwardEvent";
		playerPosition = new Vector2(0,0);
	}
	
	public boolean eventTriggered(float delta) {
		
		// TODO Fix + so much to improve + create final floats
		
		if(super.eventTriggered(delta)){
			// Stop polling this every frame and just store a reference on creation
			//Entity entity = StateBasedEntityManager.getInstance().getEntity(Tanks.GAMEPLAYSTATE, Tanks.player1);
			Entity entity = null;
			if(entity != null){
				playerPosition = new Vector2(entity.getX(), entity.getY());
//				Vector2 opponentPosition = getOwnerEntity().getPosition();
//				if (playerPosition.dst(opponentPosition) >= 300 || playerPosition.dst(opponentPosition) <= 250){
//					return  (playerPosition.distance(this.getOwnerEntity().getPosition()) < 300);
//				} else return false;
			} else return false;
		}
		return false;
	}

}
