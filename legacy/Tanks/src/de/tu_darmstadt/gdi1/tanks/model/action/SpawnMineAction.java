package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.factory.MineFactory;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.IStreangth;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class SpawnMineAction implements Action {
	
	private int streangth;
	
	public SpawnMineAction(){
		streangth = 0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Component event) {
		if(IStreangth.class.isInstance(event.getOwnerEntity()))
			streangth = ((IStreangth) event.getOwnerEntity()).getStreangth() *2;
		
		Entity entity = new MineFactory(new Vector2f(event.getOwnerEntity().getPosition()), event.getOwnerEntity().getScale()*2, streangth, Tanks.debug).createEntity();
		
		StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), entity);

	}

}
