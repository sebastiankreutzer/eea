package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.ScatterShootFactory;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;

public class ScatterShootAction extends EEAAction {
	

	private int strength;
	private long time;

	public ScatterShootAction(long time){
		this.strength = 0;
		this.time = time;
	}
	
	@Override
	public boolean act(float delta) {
		if(IStrength.class.isInstance(getActor())){
			this.strength = ((IStrength) getActor()).getStrength();
		}
		
		float rotation = getActor().getRotation();
		Vector2 pos = new Vector2(getActor().getX(), getActor().getY());
		Vector2 size = new Vector2(getActor().getWidth(), getActor().getHeight());
		
		pos.x += 2f * size.y/2.0f*java.lang.Math.sin(java.lang.Math.toRadians(rotation));
		pos.y -= 2f *size.y/2.0f*java.lang.Math.cos(java.lang.Math.toRadians(rotation));
		
		Entity simpleShoot = new ScatterShootFactory(time,
				strength,
				getEntity().getID(),
				rotation,
				getActor().getScaleX() * 1f,
				pos.x,
				pos.y,
				true).createEntity();
		
		getEntity().getManager().addEntity(simpleShoot);
		
		if(getEntity().getID().equals("\"PlayerOne\"")) GameplayLog.getInstance().incrementNumberOfShots(1);
		return true;
	}

}
