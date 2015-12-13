package de.tu.darmstadt.informatik.tanks2.factories;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu.darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.CollisionEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.EntityOutOfScreenEvent;
import de.tu.darmstadt.informatik.eea.event.LoopEvent;
import de.tu.darmstadt.informatik.eea.event.TimeEvent;
import de.tu.darmstadt.informatik.tanks2.actions.HitAction;
import de.tu.darmstadt.informatik.tanks2.actions.SpawnShootAction;
import de.tu.darmstadt.informatik.tanks2.entities.ScatterShoot;

public class ScatterShootFactory extends ShootFactory {
	
	protected final long time;
	private IResourcesManager resourcesManager;
	
	public ScatterShootFactory(long time, int strength, String owner, float rotation, 
			float scale, float x, float y, boolean debug, IResourcesManager resourcesManager) {
		super(strength, owner, rotation, scale, x, y, debug, resourcesManager);
		this.time = time;
		this.resourcesManager = resourcesManager;
	}
	
	@Override
	public Entity createEntity(){
		
		Entity scatterShoot = new ScatterShoot(owner, strength, time);
		
		scatterShoot.setPosition(position.x, position.y);
		scatterShoot.setRotation(rotation);
		scatterShoot.setScale(scale);
		
		scatterShoot.addComponent(new ImageRenderComponent("shoot.png", resourcesManager));
		
		
		EEAEvent mainEvent = new TimeEvent(time, false);
		mainEvent.addAction(new DestroyEntityAction());
		mainEvent.addAction(new SpawnShootAction((rotation-90 +360) %360 ,strength/5,resourcesManager));
		mainEvent.addAction(new SpawnShootAction((rotation-45 +360) %360, strength/5,resourcesManager));
		mainEvent.addAction(new SpawnShootAction(rotation, strength/5,resourcesManager));
		mainEvent.addAction(new SpawnShootAction((rotation+45+360) %360, strength/5,resourcesManager));
		mainEvent.addAction(new SpawnShootAction((rotation+90+360) %360, strength/5,resourcesManager));
		scatterShoot.addComponent(mainEvent);
		
		mainEvent = new EntityOutOfScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		scatterShoot.addComponent(mainEvent);
		
		
		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(strength));
		scatterShoot.addComponent(mainEvent);
		
		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveRelativeAction(30f, 0f));
		scatterShoot.addComponent(mainEvent);
		
		
		
		return scatterShoot;
	}

}
