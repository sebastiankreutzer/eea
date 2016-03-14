package de.tu_darmstadt.informatik.tanks2.AI;

import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu_darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;

/**
 * Eine Tower AI, die einen stationaeren Tower rotieren und auf ein Ziel
 * schiessen laesst.
 * 
 * @author jr
 *
 */
public class TowerAI extends AI {

	public static final String ID = "TowerAI";
	private ShootFactory shotFactory;

	/**
	 * Erzeugt eine neue TowerAI
	 * 
	 * @param target
	 *            Die ID des Ziels
	 * @param shotFactory
	 *            Die ShotFactory zum erzeugen der Schuesse
	 * @param debug
	 *            Der Debugmodus
	 */
	public TowerAI(String target, ShootFactory shotFactory, boolean debug) {
		super(ID, target, debug);
		this.shotFactory = shotFactory;
	}

	@Override
	protected EEAMovement calculateNextMove() {
		// Bestimme die Abweichung der Rotation
		float deltaRotation = calculateDeltaRotation();
		// Wenn die Abweichung zu gross ist, gebe eine RotationAction zurueck
		// die sie korrigiert
		if (Math.abs(deltaRotation - 180) <= 175)
			return determineRotateAction(deltaRotation);

		// Wenn Munition vohanden ist schiesse, ansonsten bleibt nur Drehen
		// uebrig
		if (((IAmmunition) owner).hasAmmunition()) {
			return new ShootAction(shotFactory, debug);
		} else {
			return determineRotateAction(deltaRotation);
		}
	}

}
