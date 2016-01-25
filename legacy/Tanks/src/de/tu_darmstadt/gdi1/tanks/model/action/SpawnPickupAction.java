package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.factory.PickupFactory;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

public class SpawnPickupAction implements Action {

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		String type = Tanks.healthpack;
		
		if(Math.random() > 0.5){
			type = Tanks.ammopack;
		}
		
		StateBasedEntityManager.getInstance().addEntity(Tanks.GAMEPLAYSTATE, new PickupFactory(type, 100, 0, 0.3f, (float)Math.random()*800, (float)Math.random()*600, Tanks.debug).createEntity());

	}

}
