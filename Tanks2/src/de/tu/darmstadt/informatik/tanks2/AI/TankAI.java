package de.tu.darmstadt.informatik.tanks2.AI;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.action.MoveAction;
import de.tu.darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu.darmstadt.informatik.eea.action.RotateAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tank;
import de.tu.darmstadt.informatik.tanks2.interfaces.IShootAmmo;
import temp.removeASAP.Tanks;

public class TankAI extends AI {
	
	public static final String ID = "TankAI";

	public TankAI(String target) {
		super(ID, target);
	}
	
	protected EEAAction calculateNextMove(){
		float deltaRotation = calculateDeltaRotation();
		
		if (Math.abs(deltaRotation - 180) <= 175) return determineRotateAction(deltaRotation);
		
		float distance = Vector2.dst2(owner.getX(), owner.getY(), target.getX(), target.getY());
		if (distance <= Math.pow(250, 2)) return new MoveRelativeAction(-speed, 0);
		if (distance >= Math.pow(300, 2)) return new MoveRelativeAction(speed, 0);
		
		if(((IShootAmmo) owner).hasShootAmmo())	return new ShootAction();
		
		return determineRotateAction(deltaRotation);
	}

}
