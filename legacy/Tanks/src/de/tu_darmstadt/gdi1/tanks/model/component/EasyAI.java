package de.tu_darmstadt.gdi1.tanks.model.component;



import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;
import de.tu_darmstadt.gdi1.tanks.model.action.ShootAction;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.action.Action;
import eea.engine.action.basicactions.MoveBackwardAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.action.basicactions.RotateLeftAction;
import eea.engine.action.basicactions.RotateRightAction; 
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class EasyAI extends AI {
	
	private enum state {rotating, moving, shooting};
	private state actualState;
	private float speed;
	private ShootAction shotAction;
	private Vector2f playerPosition;
	
	public EasyAI(float speed, float frequenzy, int streangth) {
		super("EasyAI");
		this.speed = speed;
		actualState = state.rotating;
		//shotAction = new ShootAction(frequenzy, 1);
		playerPosition = new Vector2f(0,0);
	}
	
	

	@Override
	public Action getNextAction() {
		calculateNextState();
		switch(actualState){
		
		case rotating: 	if(this.isRotatingLeft()) return new RotateLeftAction(speed);
					 	else return new RotateRightAction(speed); 
		
		case moving:	if(this.isMovingForward()) return new MoveForwardAction(speed);
						else return new MoveBackwardAction(speed);
		
		default:  return shotAction;
		}
		
	}
	
	private void calculateNextState(){
		switch(actualState){
		
		case rotating: 	if(needsToRotate()) break;
					   	if(needsToMove()) {actualState = state.moving; break;}
					   	actualState = state.shooting; break;
		
		case moving:   	if(needsToRotate()) {actualState = state.rotating; break;}
						if(needsToMove()) break;
		   				actualState = state.shooting; break;
		
		case shooting:	if(needsToRotate()) {actualState = state.rotating; break;}
						if(needsToMove()) {actualState = state.moving; break;}
						break;
						
						
		}
	}
	
	
	private boolean needsToRotate(){
		Entity entity = StateBasedEntityManager.getInstance().getEntity(Tanks.GAMEPLAYSTATE, Tanks.player1);
		if(entity != null){
			
			playerPosition = new Vector2f(entity.getPosition());
			Vector2f opponentPosition = getOwnerEntity().getPosition();
			float opponentRotation = (getOwnerEntity().getRotation() + 360) % 360;
			
			
			Line OpponentToPlayer = new Line(opponentPosition, playerPosition);
			double rotation = OpponentToPlayer.getDY() / OpponentToPlayer.getDX();
			rotation = Math.toDegrees(Math.atan(rotation));
			
			if(playerPosition.x <= opponentPosition.x) rotation += 270;
			if(playerPosition.x > opponentPosition.x) rotation += 90;
			
			return (Math.abs(rotation - opponentRotation) >= 5);
		}
		return false;
	}
	
	private boolean needsToMove(){
		Vector2f opponentPosition = getOwnerEntity().getPosition();
		return (playerPosition.distance(opponentPosition) >= 300 || playerPosition.distance(opponentPosition) <= 250);
	}

	
	private boolean isMovingForward(){
		return  (playerPosition.distance(this.getOwnerEntity().getPosition()) >= 300);
	}
	
	private boolean isRotatingLeft(){
		
		Vector2f opponentPosition = new Vector2f(this.getOwnerEntity().getPosition());
		float opponentRotation = (this.getOwnerEntity().getRotation() + 360) % 360;
		
		Vector2f localKoordsX = new Vector2f ((float) Math.sin(Math.toRadians(opponentRotation)) ,-(float) Math.cos(Math.toRadians(opponentRotation)));
		
		
		if(opponentRotation == 0){
			return (playerPosition.x <= opponentPosition.x);
		} else if(opponentRotation == 180){
			return (playerPosition.x > opponentPosition.x);
		}else{
			Vector2f nextPosition = new Vector2f(opponentPosition).add(localKoordsX);
			Line OpponentToBase = new Line(opponentPosition, nextPosition);
			
			float m = OpponentToBase.getDY() / OpponentToBase.getDX();
			float b = opponentPosition.y - (m * opponentPosition.x);
			float y = m * playerPosition.x +b;
			
			if(opponentRotation >= 180){
				return (y <= playerPosition.y);
			}else{
				return (y > playerPosition.y);
			}
			
			
		}
	}

}
