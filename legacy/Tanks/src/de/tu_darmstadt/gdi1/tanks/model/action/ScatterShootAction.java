package de.tu_darmstadt.gdi1.tanks.model.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.GameplayLog;
import de.tu_darmstadt.gdi1.tanks.model.factory.ScatterShootFactory;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.IStreangth;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class ScatterShootAction implements Action {
	

	private int strength;
	private long time;

	public ScatterShootAction(long time){
		this.strength = 0;
		this.time = time;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		

				
				if(IStreangth.class.isInstance(event.getOwnerEntity())){
					this.strength = ((IStreangth) event.getOwnerEntity()).getStreangth();
				}
				float rotation = event.getOwnerEntity().getRotation();
				Vector2f position = new Vector2f(event.getOwnerEntity().getPosition());
				Vector2f size = event.getOwnerEntity().getSize();
				position.x += 2f * size.y/2.0f*java.lang.Math.sin(java.lang.Math.toRadians(rotation));
		        position.y -= 2f *size.y/2.0f*java.lang.Math.cos(java.lang.Math.toRadians(rotation));
		        
				
				Entity simpleShoot = new ScatterShootFactory(time,strength, event.getOwnerEntity().getID(), event.getOwnerEntity().getRotation(), event.getOwnerEntity().getScale() * 1f, position.x, position.y ,Tanks.debug).createEntity();
				
				
				StateBasedEntityManager.getInstance().addEntity(sb.getCurrentStateID(),simpleShoot);
				if(event.getOwnerEntity().getID().equals("\"PlayerOne\"")) GameplayLog.getInstance().incrementNumberOfShots(1);
				
				
			

	}

}
