package de.tu.darmstadt.informatik.tanks2.misc;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.action.MoveAction;
import de.tu.darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu.darmstadt.informatik.eea.action.RotateAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tank;
import temp.removeASAP.Tanks;

public class EasyAI extends AI {
	
	public static final String ID = "EasyAI";
	
	float speed = 0, strength = 0;

	public EasyAI(String target) {
		super(ID, target);
	}
	
	@Override
	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
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
		
		float rotationToTarget = (float) Math.toDegrees(
				Math.atan2(owner.getY() - target.getY(), owner.getX() - target.getX())
				) + 90;
		float deltaRotation = ((owner.getRotation() - rotationToTarget) % 360 + 360) % 360;
		
		if (Math.abs(deltaRotation - 180) <= 175) {
			if(deltaRotation < 180) return new RotateAction(-speed);
			else return new RotateAction(speed);
		}
		
		float distance = Vector2.dst2(owner.getX(), owner.getY(), target.getX(), target.getY());
		if (distance <= Math.pow(250, 2)) return new MoveRelativeAction(-speed, 0);
		if (distance >= Math.pow(300, 2)) return new MoveRelativeAction(speed, 0);
		
		return new ShootAction();
	}
		
	private boolean findTarget(){
		target = owner.getManager().getEntity(Tanks.player1);
		if(target != null) return true;
		return false;
	}

}
