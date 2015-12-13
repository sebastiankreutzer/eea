package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu.darmstadt.informatik.tanks2.interfaces.IShootAmmo;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;

public class ShootAction extends EEAAction {

	private int strength;
	private IResourcesManager resourcesManager;
	
	public ShootAction(IResourcesManager resourcesManager){
		this.strength = 0;
		this.resourcesManager = resourcesManager;
	}

	@Override
	public boolean act(float delta) {
		if (IStrength.class.isInstance(getActor())) {
			this.strength = ((IStrength) getActor()).getStrength();
			((IShootAmmo) getActor()).changeShootAmmo(-1);
		}

		float rotation = getActor().getRotation();
		Vector2 pos = new Vector2(getActor().getX(), getActor().getY());
		
		Vector2 size = new Vector2(getActor().getWidth()
				* getActor().getScaleX(), getActor().getHeight()
				* getActor().getScaleY());

		// TODO Set to center
		pos.x -= 2f * size.y / 2.0f
				* java.lang.Math.sin(java.lang.Math.toRadians(rotation)) + 10;
		pos.y += 2f * size.y / 2.0f
				* java.lang.Math.cos(java.lang.Math.toRadians(rotation)) + 10;

		Entity simpleShoot = new ShootFactory(strength,
				getEntity().getID(),
				rotation,
				getActor().getScaleX() * 0.5f,
				pos.x,
				pos.y,
				true, resourcesManager).createEntity();
		
		getEntity().getManager().addEntity(simpleShoot);

		if (getEntity().getID().equals("\"PlayerOne\"")) {
			GameplayLog.getInstance().incrementNumberOfShots(1);
		}
		return true;
	}

	// @Override
	// public void update(GameContainer gc, StateBasedGame sb, int delta,
	// Component event) {
	//
	// if(IStreangth.class.isInstance(event.getOwnerEntity())){
	// this.strength = ((IStreangth) event.getOwnerEntity()).getStreangth();
	// }
	// float rotation = event.getOwnerEntity().getRotation();
	// Vector2f position = new Vector2f(event.getOwnerEntity().getPosition());
	// Vector2f size = event.getOwnerEntity().getSize();
	// position.x += 2f *
	// size.y/2.0f*java.lang.Math.sin(java.lang.Math.toRadians(rotation));
	// position.y -= 2f
	// *size.y/2.0f*java.lang.Math.cos(java.lang.Math.toRadians(rotation));
	//
	// Entity simpleShoot = new ShootFactory(strength,
	// event.getOwnerEntity().getID(), event.getOwnerEntity().getRotation(),
	// event.getOwnerEntity().getScale() * 0.5f, position.x, position.y
	// ,Tanks.debug).createEntity();
	//
	//
	// StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(),simpleShoot);
	// if(event.getOwnerEntity().getID().equals("\"PlayerOne\""))
	// GameplayLog.getInstance().incrementNumberOfShots(1);
	// }

}
