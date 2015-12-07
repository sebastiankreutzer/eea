package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.MineFactory;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;

public class SpawnMineAction extends EEAAction {
	
	private int strength;
	
	public SpawnMineAction(){
		strength = 0;
	}
	
	@Override
	public boolean act(float delta) {
		if(IStrength.class.isInstance(getActor())){
			strength = ((IStrength) getActor()).getStrength() *2;
		}
		
		Entity entity = new MineFactory(new Vector2(getActor().getX(), getActor().getY()),
				getActor().getScaleX()*2,
				strength,
				true).createEntity();
		
		getEntity().getManager().addEntity(entity);
		return true;
	}

//	@Override
//	public void update(GameContainer gc, StateBasedGame sb, int delta,
//			Component event) {
//		if(IStreangth.class.isInstance(event.getOwnerEntity()))
//			streangth = ((IStreangth) event.getOwnerEntity()).getStreangth() *2;
//		
//		Entity entity = new MineFactory(new Vector2f(event.getOwnerEntity().getPosition()), event.getOwnerEntity().getScale()*2, streangth, Tanks.debug).createEntity();
//		
//		StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(), entity);
//
//	}

}
