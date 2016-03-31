package de.tu_darmstadt.informatik.tanks2.AI;

import com.badlogic.gdx.math.Vector2;
import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu_darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu_darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;

/**
 * Eine Tank AI, die eine Panzer fahren, rotieren und auf ein Ziel schiessen
 * laesst.
 * 
 * @author jr
 *
 */
public class TankAI extends AI {

	public static final String ID = "TankAI";
	private ShootFactory shotFactory;

	/**
	 * Erzeugt eine neue TankAI
	 * 
	 * @param target
	 *            Die ID des Ziels
	 * @param shotFactory
	 *            Die ShotFactory zum erzeugen der Schuesse
	 * @param debug
	 *            Der Debugmodus
	 */
	public TankAI(String target, ShootFactory shotFactory, boolean debug) {
		super(ID, target, debug);
		this.shotFactory = shotFactory;
	}

	protected EEAMovement calculateNextMove() {
		// Bestimme die Abweichung der Rotation
		float deltaRotation = calculateDeltaRotation();
		// Wenn die Abweichung zu gross ist, gebe eine RotationAction zurueck
		// die sie korrigiert
		if (Math.abs(deltaRotation - 180) <= 175)
			return determineRotateAction(deltaRotation);

		// Bestimme die Distanz zum Ziel, wenn diese zu gross oder zu klein ist
		// gebe eine entsprechende MoveRelativeAction zurueck um sie zu
		// korrigieren
		float distance = Vector2.dst2(owner.getX(), owner.getY(), target.getX(), target.getY());
		if (distance <= Math.pow(250, 2))
			return new MoveRelativeAction(-speed, 0);
		if (distance >= Math.pow(300, 2))
			return new MoveRelativeAction(speed, 0);

		// Wenn Munition vohanden ist schiesse, ansonsten bleibt nur Drehen
		// uebrig
		if (((IAmmunition) owner).hasAmmunition()) {
			return new ShootAction(shotFactory, debug);
		} else {
			return determineRotateAction(deltaRotation);
		}
	}

}
