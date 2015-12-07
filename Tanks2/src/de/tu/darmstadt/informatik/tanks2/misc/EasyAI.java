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
		
		float opponentRotation = (owner.getRotation() + 360) % 360;
		float rotation = (float) Math.toDegrees(
				Math.atan2(owner.getY() - target.getY(), owner.getX() - target.getX())
				) - 90;
		
		if (Math.abs(rotation - opponentRotation) >= 5){
			float r  = (float) ((rotation + opponentRotation) % 360);
			if(r < 180) return new RotateAction(speed);
			else return new RotateAction(-speed);
		}
		
		float distance = Vector2.dst(owner.getX(), owner.getY(), target.getX(), target.getY());
		if (distance >= 300) return new MoveAction(speed, 0);
		if (distance <= 250) return new MoveAction(-speed, 0);
		
		return new ShootAction();
	}
		
	private boolean findTarget(){
		target = owner.getManager().getEntity(Tanks.player1);
		if(target != null) return true;
		return false;
	}

}
