package de.tu_darmstadt.informatik.tanks2.AI;

import com.badlogic.gdx.math.Vector2;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu_darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu_darmstadt.informatik.tanks2.interfaces.IShootAmmo;

public class TankAI extends AI {
	
	public static final String ID = "TankAI";
	private IResourcesManager resourceManager;

	public TankAI(String target, IResourcesManager resourcesManager) {
		super(ID, target);
		this.resourceManager = resourcesManager;
	}
	
	protected EEAMovement calculateNextMove(){
		float deltaRotation = calculateDeltaRotation();
		
		if (Math.abs(deltaRotation - 180) <= 175) return determineRotateAction(deltaRotation);
		
		float distance = Vector2.dst2(owner.getX(), owner.getY(), target.getX(), target.getY());
		if (distance <= Math.pow(250, 2)) return new MoveRelativeAction(-speed, 0);
		if (distance >= Math.pow(300, 2)) return new MoveRelativeAction(speed, 0);
		
		if(((IShootAmmo) owner).hasShootAmmo())	return new ShootAction(resourceManager);
		
		return determineRotateAction(deltaRotation);
	}

}
