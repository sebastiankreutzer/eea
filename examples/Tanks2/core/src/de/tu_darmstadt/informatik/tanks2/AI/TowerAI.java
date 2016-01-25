package de.tu_darmstadt.informatik.tanks2.AI;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu_darmstadt.informatik.tanks2.interfaces.IShootAmmo;

public class TowerAI extends AI {
	
	public static final String ID = "TowerAI";
	private IResourcesManager resourceManager;

	public TowerAI(String target, IResourcesManager resourcesManager) {
		super(ID, target);
		this.resourceManager = resourcesManager;
	}

	@Override
	protected EEAMovement calculateNextMove() {
		float deltaRotation = calculateDeltaRotation();
		
		if (Math.abs(deltaRotation - 180) <= 175) return determineRotateAction(deltaRotation);
		
		if(((IShootAmmo) owner).hasShootAmmo())	return new ShootAction(resourceManager);
		
		return determineRotateAction(deltaRotation);
	}

}
