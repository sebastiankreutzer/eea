package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.factories.ShotFactory;

/**
 * Diese Action erzeugt eine neue Schuss Entity an der Stelle der ausfuehrenden
 * Entity.
 * 
 * @author jr
 *
 */
public class SpawnShootAction extends EEAAction {

	private int strength;
	private float rotation;
	private ShotFactory shotFactory;

	/**
	 * Erzeugt eine neue SpawnShootAction
	 * 
	 * @param rotation
	 *            Die Rotation der von der von dieser Entity erzeugten Schuss
	 *            Entity
	 * @param strength
	 *            Die Staerke der von der von dieser Entity erzeugten Schuss
	 *            Entity
	 * @param shotFactory
	 */
	public SpawnShootAction(float rotation, int strength, ShotFactory shotFactory) {
		this.strength = strength;
		this.rotation = rotation;
		this.shotFactory = shotFactory;
	}

	@Override
	public boolean act(float delta) {
		Entity owner = getEntity();
		Entity simpleShoot = shotFactory.createShot(owner.getX(), owner.getY(), owner.getID(), strength, rotation,
				owner.getScaleX() / 2);
		owner.getManager().addEntity(simpleShoot);
		return true;
	}
}
