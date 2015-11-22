package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.ShootFactory;

public class SpawnShootAction extends Action {
	
	private int strength;
	private float rotation;
	
	public SpawnShootAction(float rotation , int strength){
		this.strength = strength;
		this.rotation = rotation;
	}
	
	@Override
	public boolean act(float delta) {
		// TODO this cast is not nice
		Entity simpleShoot = new ShootFactory(strength,
				((Entity) getActor()).getID(),
				rotation,
				getActor().getScaleX() * 0.5f,
				getActor().getX(),
				getActor().getY(),
				true).createEntity();
		
		// TODO add to entitymanager
		//em.addEntity(simpleShoot);
		return true;
	}

//	@Override
//	public void update(GameContainer gc, StateBasedGame sb, int delta,
//			Component event) {
//		
//		
//		float scaling = event.getOwnerEntity().getScale();
//		Vector2f position = new Vector2f(event.getOwnerEntity().getPosition());
//		
//
//		Entity simpleShoot = new ShootFactory(strength, event.getOwnerEntity().getID(), rotation, scaling * 0.5f, position.x, position.y ,Tanks.debug).createEntity();
//		StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(),simpleShoot);
//	}

}
