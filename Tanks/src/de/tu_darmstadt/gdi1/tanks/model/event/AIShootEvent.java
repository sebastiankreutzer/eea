package de.tu_darmstadt.gdi1.tanks.model.event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

public class AIShootEvent extends Event{
	
	private Vector2f playerPosition;

	public AIShootEvent() {
		super("AIShootEvent");
		playerPosition = new Vector2f(0,0);
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		Entity entity = StateBasedEntityManager.getInstance().getEntity(Tanks.GAMEPLAYSTATE, Tanks.player1);
		if(entity != null){
			playerPosition = new Vector2f(entity.getPosition());
			Vector2f opponentPosition = getOwnerEntity().getPosition();
			float opponentRotation = getOwnerEntity().getRotation();
			
			
			Line OpponentToPlayer = new Line(opponentPosition, playerPosition);
			double rotation = OpponentToPlayer.getDY() / OpponentToPlayer.getDX();
			rotation = Math.toDegrees(Math.atan(rotation));
			
			if(playerPosition.x <= opponentPosition.x) rotation += 270;
			if(playerPosition.x > opponentPosition.x) rotation += 90;
			
			return  !(Math.abs(rotation - opponentRotation) >= 5);
		}

		
		return false;
	}

}
