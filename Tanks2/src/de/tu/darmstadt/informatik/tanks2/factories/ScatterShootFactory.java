package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.Texture;

import de.tu.darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu.darmstadt.informatik.eea.action.MoveAction;
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
	
	public ScatterShootFactory(long time, int strength, String owner, float rotation,
			float scale, float x, float y, boolean debug) {
		super(strength, owner, rotation, scale, x, y, debug);
		this.time = time;
	}
	
	@Override
	public Entity createEntity(){
		
		Entity scatterShoot = new ScatterShoot(owner, strength, time);
		
		scatterShoot.setPosition(position.x, position.y);
		scatterShoot.setRotation(rotation);
		scatterShoot.setScale(scale);
		
		scatterShoot.addComponent(new ImageRenderComponent(new Texture("shoot.png")));
		
		
		EEAEvent mainEvent = new TimeEvent(time, false);
		mainEvent.addAction(new DestroyEntityAction());
		mainEvent.addAction(new SpawnShootAction((rotation-90 +360) %360 ,strength/5));
		mainEvent.addAction(new SpawnShootAction((rotation-45 +360) %360, strength/5));
		mainEvent.addAction(new SpawnShootAction(rotation, strength/5));
		mainEvent.addAction(new SpawnShootAction((rotation+45+360) %360, strength/5));
		mainEvent.addAction(new SpawnShootAction((rotation+90+360) %360, strength/5));
		scatterShoot.addComponent(mainEvent);
		
		mainEvent = new EntityOutOfScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		//mainEvent.addAction(new ResetEntity(new Vector2f(gc.getWidth()/2, gc.getHeight()/2)));
		scatterShoot.addComponent(mainEvent);
		
		
		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(strength));
		scatterShoot.addComponent(mainEvent);
		
		mainEvent = new LoopEvent();
		// TODO Make relative instead of absolute
		mainEvent.addAction(new MoveAction(0.30f, 0f));
		scatterShoot.addComponent(mainEvent);
		
		
		
		return scatterShoot;
	}

}
