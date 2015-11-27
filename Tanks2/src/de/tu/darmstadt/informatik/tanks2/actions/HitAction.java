package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.ExplosionFactory;
import de.tu.darmstadt.informatik.tanks2.interfaces.ILife;

public class HitAction extends Action {
	
	private int strength;
	
	public HitAction(int strength){
		this.strength = strength;
	}
	
	@Override
	public boolean act(float delta) {
		if(ILife.class.isInstance(getTarget())){
			ILife l = (ILife) getTarget();
			l.changeLife(-strength);
			
			Entity explosion = new Entity("Explosion");
			// TODO Creating a new Factory every time an explosion occurs seems quite overkilling.
			explosion = new ExplosionFactory(getTarget().getX() - getTarget().getWidth()/2, getTarget().getY()-getTarget().getHeight()/2, 
					0.01f, getTarget().getWidth(), getTarget().getHeight(), false).createEntity();
			// TODO Add explosion to entitymanager
			// em.addEntity(explosion);
			
			if(!l.hasLifeLeft()){
				// TODO Check whether this is a good idea.
				getTarget().addAction(new DestroyEntityAction());
			}
		}
		return true;
	}

//	@Override
//	public void update(GameContainer gc, StateBasedGame sb, int delta,
//			Component event) {
//		if(CollisionEvent.class.isInstance(event)){
//			CollisionEvent colider = (CollisionEvent) event;
//			Entity entity = colider.getColidedEntity();
//			if(ILife.class.isInstance(entity)){
//				ILife life = (ILife) entity;
//				life.changeLife(-strength);
//				StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
//				
//				Entity explosion = new Entity("Explosion");
//				explosion =new ExplosionFactory(event.getOwnerEntity().getPosition().x-entity.getSize().y/2, event.getOwnerEntity().getPosition().y-entity.getSize().y/2, 
//						0.01f, entity.getSize().y, entity.getSize().y, Tanks.debug).createEntity();
//				
//				StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), explosion);
//				if(!life.hasLifeLeft()) {
//					
//					StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), entity);
//				}
//			}
//		}
//	}

}