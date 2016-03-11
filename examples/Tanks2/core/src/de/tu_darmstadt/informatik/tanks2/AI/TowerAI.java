package de.tu_darmstadt.informatik.tanks2.AI;

import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu_darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.IShootAmmo;

public class TowerAI extends AI {
	
	public static final String ID = "TowerAI";
	private ShootFactory shotFactory;

	public TowerAI(String target, ShootFactory shotFactory, boolean debug) {
		super(ID, target, debug);
		this.shotFactory = shotFactory;
	}

	@Override
	protected EEAMovement calculateNextMove() {
		float deltaRotation = calculateDeltaRotation();
		
		if (Math.abs(deltaRotation - 180) <= 175) return determineRotateAction(deltaRotation);
		
		if(((IShootAmmo) owner).hasShootAmmo())	return new ShootAction(shotFactory, debug);
		
		return determineRotateAction(deltaRotation);
	}

}
