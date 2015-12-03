package de.tu.darmstadt.informatik.tanks2.misc;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.action.MoveAction;
import de.tu.darmstadt.informatik.eea.action.RotateAction;
import de.tu.darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tank;
import temp.removeASAP.Tanks;

public class EasyAI extends AI {
	
	public static final String ID = "EasyAI";
	
	private static enum State {rotating, moving, shooting};
	private State state = State.rotating;
	
	float speed = 0, strength = 0;

	public EasyAI(String target) {
		super(ID, target);
		if(owner instanceof Tank){
			Tank tank = (Tank) owner;
			speed = tank.getSpeed();
			strength = tank.getStrength();
		}
	}

	@Override
	public Action getNextAction() {
		if(target == null && !findTarget()) return null;
		
		return calculateNextMove();
	}
	
	private EEAAction calculateNextMove(){
		
		float opponentRotation = (target.getRotation() + 360) % 360;
		double rotation = (owner.getY() - target.getY()) / (owner.getX() - target.getX());
		rotation = Math.toDegrees(Math.atan(rotation));
		
		if(owner.getX() <= target.getX()) rotation += 270;
		else rotation += 90;
		
		if (Math.abs(rotation - opponentRotation) >= 5){
			float r  = (float) ((rotation + opponentRotation) % 360);
			if(r < 180) new RotateAction(speed);
			else new RotateAction(-speed);
		}
		
		float distance = Vector2.dst(owner.getX(), owner.getY(), target.getX(), target.getY());
		if (distance >= 300) return new MoveAction(speed, 0);
		if (distance <= 250) return new MoveAction(-speed, 0);
		
		return new ShootAction();
	}
	
	private void calculateNextState(){
		switch(state){
		
		case rotating: 	if(needsToRotate()) break;
					   	if(needsToMove()) {state = State.moving; break;}
					   	state = State.shooting; break;
		
		case moving:   	if(needsToRotate()) {state = State.rotating; break;}
						if(needsToMove()) break;
		   				state = State.shooting; break;
		
		case shooting:	if(needsToRotate()) {state = State.rotating; break;}
						if(needsToMove()) {state = State.moving; break;}
						break;			
		}
	}
		
	private boolean findTarget(){
		target = owner.getManager().getEntity(Tanks.player1);
		if(target != null) return true;
		return false;
	}
	
	private boolean needsToRotate(){
		if(target != null){
			float opponentRotation = (target.getRotation() + 360) % 360;
			double rotation = (owner.getY() - target.getY()) / (owner.getX() - target.getX());
			
			//Line OpponentToPlayer = new Line(opponentPosition, playerPosition);
			//double rotation = OpponentToPlayer.getDY() / OpponentToPlayer.getDX();
			rotation = Math.toDegrees(Math.atan(rotation));
			
			if(owner.getX() <= target.getX()) rotation += 270;
			if(owner.getX() > target.getX()) rotation += 90;
			
			return (Math.abs(rotation - opponentRotation) >= 5);
		}
		return false;
	}
	
	private boolean needsToMove(){
		float distance = Vector2.dst2(owner.getX(), owner.getY(), target.getX(), target.getY());
		return (distance >= 300 || distance <= 250);
	}

}
