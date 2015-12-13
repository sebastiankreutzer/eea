package de.tu.darmstadt.informatik.tanks2.AI;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu.darmstadt.informatik.tanks2.interfaces.IShootAmmo;

public class TowerAI extends AI {
	
	public static final String ID = "TowerAI";

	public TowerAI(String target) {
		super(ID, target);
	}

	@Override
	protected EEAAction calculateNextMove() {
		float deltaRotation = calculateDeltaRotation();
		
		if (Math.abs(deltaRotation - 180) <= 175) return determineRotateAction(deltaRotation);
		
		if(((IShootAmmo) owner).hasShootAmmo())	return new ShootAction();
		
		return determineRotateAction(deltaRotation);
	}

}
