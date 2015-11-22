package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.entities.Pickup.PickUpType;
import de.tu.darmstadt.informatik.tanks2.factories.PickupFactory;

public class SpawnPickupAction extends Action {
	
	private PickUpType type;
	
	@Override
	public boolean act(float delta) {
		if(Math.random() > 0.5)	type = PickUpType.AMMUNITION;
		else type = PickUpType.HEALTH;
		
		Entity pickup = new PickupFactory(type, 100, 0, 0.3f, (float)Math.random()*800, (float)Math.random()*600, true).createEntity();
		// TODO Add to EntityManager
		// em.addEntity(pickup);
		
		return true;
	}

//	@Override
//	public void update(GameContainer gc, StateBasedGame sb, int delta,
//			Component event) {
//		String type = Tanks.healthpack;
//		
//		if(Math.random() > 0.5){
//			type = Tanks.ammopack;
//		}
//		
//		StateBasedEntityManager.getInstance().addEntity(Tanks.GAMEPLAYSTATE, new PickupFactory(type, 100, 0, 0.3f, (float)Math.random()*800, (float)Math.random()*600, Tanks.debug).createEntity());
//
//	}

}
