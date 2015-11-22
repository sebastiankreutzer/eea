package de.tu.darmstadt.informatik.tanks2.events;

import de.tu.darmstadt.informatik.eea.action.EEAMovement;
import de.tu.darmstadt.informatik.eea.event.MovementDoesNotCollideEvent;

public class AIMoveForwardEvent extends MovementDoesNotCollideEvent {

	public AIMoveForwardEvent(float speed, EEAMovement move) {
		super(speed, move);
		this.id = "AIMoveForwardEvent";
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		// TODO Fix analog to AIMoveBackwardEvent
		return super.eventTriggered(delta);
	}
//	public boolean (int delta) {
//		if(super.performAction(gc, sb, delta)){
//			Entity entity = StateBasedEntityManager.getInstance().getEntity(Tanks.GAMEPLAYSTATE, Tanks.player1);
//			if(entity != null){
//				playerPosition = new Vector2f(entity.getPosition());
//				Vector2f opponentPosition = getOwnerEntity().getPosition();
//				if (playerPosition.distance(opponentPosition) >= 300 || playerPosition.distance(opponentPosition) <= 250){
//					return  (playerPosition.distance(this.getOwnerEntity().getPosition()) >= 300);
//				} else return false;
//			} else return false;
//		} else return false;
//	}

}
