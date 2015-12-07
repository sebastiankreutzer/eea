package de.tu.darmstadt.informatik.tanks2.factories;

import de.tu.darmstadt.informatik.eea.EEAGraphics;
import de.tu.darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu.darmstadt.informatik.eea.action.MoveAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.CollisionEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.EntityOutOfScreenEvent;
import de.tu.darmstadt.informatik.eea.event.LoopEvent;
import de.tu.darmstadt.informatik.tanks2.actions.HitAction;
import de.tu.darmstadt.informatik.tanks2.actions.SpawnShootAction;
import de.tu.darmstadt.informatik.tanks2.entities.ScatterShoot;
import de.tu.darmstadt.informatik.tanks2.events.TimeEvent;

public class ScatterShootFactory extends ShootFactory {
	
	protected final long time;
	private EEAGraphics eeaGraphics;
	
	public ScatterShootFactory(long time, int strength, String owner, float rotation,
			float scale, float x, float y, boolean debug, EEAGraphics eeaGraphics) {
		super(strength, owner, rotation, scale, x, y, debug, eeaGraphics);
		this.time = time;
		this.eeaGraphics = eeaGraphics;
	}
	
	@Override
	public Entity createEntity(){
		
		Entity scatterShoot = new ScatterShoot(owner, strength, time);
		
		scatterShoot.setPosition(position.x, position.y);
		scatterShoot.setRotation(rotation);
		scatterShoot.setScale(scale);
		
		scatterShoot.addComponent(new ImageRenderComponent("shoot.png", eeaGraphics));
		
		
		EEAEvent mainEvent = new TimeEvent(time, false);
		mainEvent.addAction(new DestroyEntityAction());
		mainEvent.addAction(new SpawnShootAction((rotation-90 +360) %360 ,strength/5,eeaGraphics));
		mainEvent.addAction(new SpawnShootAction((rotation-45 +360) %360, strength/5,eeaGraphics));
		mainEvent.addAction(new SpawnShootAction(rotation, strength/5,eeaGraphics));
		mainEvent.addAction(new SpawnShootAction((rotation+45+360) %360, strength/5,eeaGraphics));
		mainEvent.addAction(new SpawnShootAction((rotation+90+360) %360, strength/5,eeaGraphics));
		scatterShoot.addComponent(mainEvent);
		
		mainEvent = new EntityOutOfScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		//mainEvent.addAction(new ResetEntity(new Vector2f(gc.getWidth()/2, gc.getHeight()/2)));
		scatterShoot.addComponent(mainEvent);
		
		
		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(strength, eeaGraphics));
		scatterShoot.addComponent(mainEvent);
		
		mainEvent = new LoopEvent();
		// TODO Make relative instead of absolute
		mainEvent.addAction(new MoveAction(0.30f, 0f));
		scatterShoot.addComponent(mainEvent);
		
		
		
		return scatterShoot;
	}

}
