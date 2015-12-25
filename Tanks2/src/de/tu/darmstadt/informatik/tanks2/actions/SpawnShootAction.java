package de.tu.darmstadt.informatik.tanks2.actions;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.ShootFactory;

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
		Entity simpleShoot = shotFactory.createShot(getActor().getX(), getActor().getY() + rotation, getEntity().getID(),
				strength, rotation, getActor().getScaleX() / 2);
		getEntity().getManager().addEntity(simpleShoot);
		return true;
	}

	// @Override
	// public void update(GameContainer gc, StateBasedGame sb, int delta,
	// Component event) {
	//
	//
	// float scaling = event.getOwnerEntity().getScale();
	// Vector2f position = new Vector2f(event.getOwnerEntity().getPosition());
	//
	//
	// Entity simpleShoot = new ShootFactory(strength,
	// event.getOwnerEntity().getID(), rotation, scaling * 0.5f, position.x,
	// position.y ,Tanks.debug).createEntity();
	// StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(),simpleShoot);
	// }

}
