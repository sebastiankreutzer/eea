package de.tu.darmstadt.informatik.tanks2.events;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.event.EEAEvent;

public class AIShootEvent extends EEAEvent {
	
	public static final String ID = "AIShootEvent";
	
	private Vector2 playerPosition;

	public AIShootEvent() {
		super(ID);
		playerPosition = new Vector2(0,0);
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	protected boolean performAction(int delta) {
//		Entity entity = StateBasedEntityManager.getInstance().getEntity(Tanks.GAMEPLAYSTATE, Tanks.player1);
//		if(entity != null){
//			playerPosition = new Vector2f(entity.getPosition());
//			Vector2f opponentPosition = getOwnerEntity().getPosition();
//			float opponentRotation = getOwnerEntity().getRotation();
//			
//			
//			Line OpponentToPlayer = new Line(opponentPosition, playerPosition);
//			double rotation = OpponentToPlayer.getDY() / OpponentToPlayer.getDX();
//			rotation = Math.toDegrees(Math.atan(rotation));
//			
//			if(playerPosition.x <= opponentPosition.x) rotation += 270;
//			if(playerPosition.x > opponentPosition.x) rotation += 90;
//			
//			return  !(Math.abs(rotation - opponentRotation) >= 5);
//		}
//		return false;
//	}

}
