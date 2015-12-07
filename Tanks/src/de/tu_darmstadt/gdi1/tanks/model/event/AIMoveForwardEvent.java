package de.tu_darmstadt.gdi1.tanks.model.event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.MovementDoesNotCollideEvent;
import eea.engine.interfaces.IMovement;

public class AIMoveForwardEvent extends MovementDoesNotCollideEvent {
	private Vector2f playerPosition;

	public AIMoveForwardEvent(float speed, IMovement move) {
		super(speed, move);
		this.id = "AIMoveForwardEvent";
		playerPosition = new Vector2f(0,0);
	}
	
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		if(super.performAction(gc, sb, delta)){
			Entity entity = StateBasedEntityManager.getInstance().getEntity(Tanks.GAMEPLAYSTATE, Tanks.player1);
			if(entity != null){
				playerPosition = new Vector2f(entity.getPosition());
				Vector2f opponentPosition = getOwnerEntity().getPosition();
				if (playerPosition.distance(opponentPosition) >= 300 || playerPosition.distance(opponentPosition) <= 250){
					return  (playerPosition.distance(this.getOwnerEntity().getPosition()) >= 300);
				} else return false;
			} else return false;
		} else return false;
	}

}
