package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.factory.ShootFactory;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class SpawnShootAction implements Action {
	
	private int strength;
	private float rotation;
	
	public SpawnShootAction(float rotation , int strength){
		this.strength = strength;
		this.rotation = rotation;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		
		
		float scaling = event.getOwnerEntity().getScale();
		Vector2f position = new Vector2f(event.getOwnerEntity().getPosition());
		

		Entity simpleShoot = new ShootFactory(strength, event.getOwnerEntity().getID(), rotation, scaling * 0.5f, position.x, position.y ,Tanks.debug).createEntity();
		StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(),simpleShoot);
	}

}
