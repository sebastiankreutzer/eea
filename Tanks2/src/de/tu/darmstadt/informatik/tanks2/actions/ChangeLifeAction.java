package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.tanks2.interfaces.ILife;

public class ChangeLifeAction extends Action {
	
	private int strength;
	
	public ChangeLifeAction(int strength){
		this.strength = strength;
	}
	
	@Override
	public boolean act(float delta) {
		if(ILife.class.isInstance(getActor())){
			ILife l = (ILife) getActor();
			l.changeLife(strength);
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
//				life.changeLife(strength);
//				StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
//			}
//		}
//	}

}
