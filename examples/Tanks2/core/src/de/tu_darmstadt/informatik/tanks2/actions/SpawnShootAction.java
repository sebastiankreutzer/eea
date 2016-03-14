package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.factories.ShootFactory;

public class SpawnShootAction extends EEAAction {

	private int strength;
	private float rotation;
	private ShootFactory shotFactory;

	public SpawnShootAction(float rotation, int strength, ShootFactory shotFactory) {
		this.strength = strength;
		this.rotation = rotation;
		this.shotFactory = shotFactory;
	}

	@Override
	public boolean act(float delta) {
		Entity simpleShoot = shotFactory.createShot(getActor().getX(), getActor().getY(), getEntity().getID(),
				strength, rotation, getActor().getScaleX() / 2);
		getEntity().getManager().addEntity(simpleShoot);
		return true;
	}
}
