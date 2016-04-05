package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.factories.ShotFactory;

/**
 * Eine Action welche einen Streuschuss erzeugt und initialisiert.
 * 
 * @author jr
 *
 */
public class ScatterShootAction extends ShootAction {

	private float fuseTime;

	/**
	 * Erzeugt eine neue ScatterShootAction.
	 * 
	 * @param shotFactory
	 *            Die ShotFactory fuer den Schuss.
	 */
	public ScatterShootAction(float fuseTime, ShotFactory shotFactory) {
		this(fuseTime, shotFactory, false);
	}

	/**
	 * Erzeugt eine neue ScatterShootAction.
	 * 
	 * @param shotFactory
	 *            Die ShotFactory fuer den Schuss.
	 * @param debug
	 *            Der Debugmodus
	 */
	public ScatterShootAction(float fuseTime, ShotFactory shotFactory, boolean debug) {
		super(shotFactory, debug);
		this.fuseTime = fuseTime;
	}

	protected Entity createShot(String name, int damage, float rotation, float scale) {
		return shotFactory.createScatterShot(0, 0, name, damage, rotation, scale, fuseTime);
	}

}
