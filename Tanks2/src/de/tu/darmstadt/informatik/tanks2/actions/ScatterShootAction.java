package de.tu.darmstadt.informatik.tanks2.actions;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.ShootFactory;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;

public class ScatterShootAction extends EEAAction {
	

	private int strength;
	private float time;
	private ShootFactory shotFactory;

	public ScatterShootAction(float f, IResourcesManager resourcesManager){
		this.strength = 0;
		this.time = f;
		shotFactory = new ShootFactory(false, resourcesManager);
	}
	
	@Override
	public boolean act(float delta) {
		if(IStrength.class.isInstance(getActor())){
			this.strength = ((IStrength) getActor()).getStrength();
		}
		
		float x = getActor().getX();
		float y = getActor().getY();
		float rotation = getActor().getRotation();
		float scale = getActor().getScaleX();
		
		x += 2f * scale/2.0f*java.lang.Math.sin(java.lang.Math.toRadians(rotation));
		y -= 2f *scale/2.0f*java.lang.Math.cos(java.lang.Math.toRadians(rotation));
		
		Entity simpleShoot = shotFactory.createScatterShot(x, y, getEntity().getID(), strength, rotation, scale, time);
		
		getEntity().getManager().addEntity(simpleShoot);
		
		if(getEntity().getID().equals("\"PlayerOne\"")) GameplayLog.getInstance().incrementNumberOfShots(1);
		return true;
	}

}
