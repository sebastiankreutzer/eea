package de.tu.darmstadt.informatik.tanks2.events;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.event.EEAEvent;

public class AIRotateRightEvent extends EEAEvent {
	
	private Vector2 playerPosition;

	public AIRotateRightEvent() {
		super("AIRotateRightEvent");
		playerPosition = new Vector2(0,0);
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	protected boolean performAction(GameContainer gc, StateBasedGame sb,
//			int delta) {
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
//			if (Math.abs(rotation - opponentRotation) >= 5){
//				Vector2f localKoordsX = new Vector2f ((float) Math.sin(Math.toRadians(opponentRotation)) ,-(float) Math.cos(Math.toRadians(opponentRotation)));
//				
//				
//				if(opponentRotation == 0){
//					return (playerPosition.x > opponentPosition.x);
//				} else if(opponentRotation == 180){
//					return (playerPosition.x <= opponentPosition.x);
//				}else{
//					Vector2f nextPosition = new Vector2f(opponentPosition).add(localKoordsX);
//					Line OpponentToBase = new Line(opponentPosition, nextPosition);
//					
//					float m = OpponentToBase.getDY() / OpponentToBase.getDX();
//					float b = opponentPosition.y - (m * opponentPosition.x);
//					float y = m * playerPosition.x +b;
//					
//					if(opponentRotation >= 180){
//						return (y > playerPosition.y);
//					}else{
//						return (y <= playerPosition.y);
//					}
//					
//					
//				}
//			}
//		}
//		return false;
//	}	

}
