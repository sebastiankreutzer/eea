package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.factories.ExplosionFactory;
import de.tu_darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;

public class ScatterShootAction extends EEAAction {
	
	private float fuseTime;
	private ShootFactory shotFactory;
	
	public ScatterShootAction(float fuseTime, ShootFactory shotFactory){
		this(fuseTime, shotFactory, false);
	}

	public ScatterShootAction(float fuseTime, ShootFactory shotFactory, boolean debug){
		this.fuseTime = fuseTime;
		this.shotFactory = shotFactory;
	}
	
	@Override
	public boolean act(float delta) {
		if(IStrength.class.isInstance(getActor())){
			int strength = ((IStrength) getActor()).getStrength();
			float x = getActor().getX();
			float y = getActor().getY();
			float rotation = getActor().getRotation();
			float scale = getActor().getScaleX();
			
			x += 2f * scale/2.0f*java.lang.Math.sin(java.lang.Math.toRadians(rotation));
			y -= 2f *scale/2.0f*java.lang.Math.cos(java.lang.Math.toRadians(rotation));
			
			Entity simpleShoot = shotFactory.createScatterShot(x, y, getEntity().getID(), strength, rotation, scale, fuseTime);
			
			getEntity().getManager().addEntity(simpleShoot);
			
			if(getEntity().getID().equals("\"PlayerOne\"")) GameplayLog.getInstance().incrementNumberOfShots(1);
		}
		
		return true;
	}

}
