package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.action.EEAMovement;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu.darmstadt.informatik.tanks2.interfaces.IShootAmmo;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;

public class ShootAction extends EEAMovement {

	private int strength;
	private ShootFactory shotFactory;

	public ShootAction(IResourcesManager resourcesManager) {
		this.strength = 0;
		shotFactory = new ShootFactory(false, resourcesManager);
	}

	@Override
	public boolean act(float delta) {
		if (IStrength.class.isInstance(getActor())) {
			this.strength = ((IStrength) getActor()).getStrength();
			((IShootAmmo) getActor()).changeShootAmmo(-1);
		}
		
		float x = getActor().getX();
		float y = getActor().getY();
		
		String owner = ((Entity) getActor()).getID();
		
		float rotation = getActor().getRotation();
		float scale = getActor().getScaleX();

		// TODO Set to center
		x -= 2f * scale / 2.0f * java.lang.Math.sin(java.lang.Math.toRadians(rotation)) + 10;
		y += 2f * scale / 2.0f * java.lang.Math.cos(java.lang.Math.toRadians(rotation)) + 10;

		Entity simpleShoot = shotFactory.createShot(x, y, owner, strength, rotation, scale);

		getEntity().getManager().addEntity(simpleShoot);

		if (getEntity().getID().equals("\"PlayerOne\"")) {
			GameplayLog.getInstance().incrementNumberOfShots(1);
		}
		return true;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		return new Vector2(getActor().getX(), getActor().getY());
	}

}
