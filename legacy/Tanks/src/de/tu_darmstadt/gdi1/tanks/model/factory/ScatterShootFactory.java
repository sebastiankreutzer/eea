package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.tanks.model.action.HitAction;
import de.tu_darmstadt.gdi1.tanks.model.action.SpawnShootAction;
import de.tu_darmstadt.gdi1.tanks.model.entities.ScatterShoot;
import de.tu_darmstadt.gdi1.tanks.model.event.TimeEvent;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;


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


		
		
		scatterShoot.setPosition(position);
		scatterShoot.setRotation(rotation);
		scatterShoot.setScale(scale);
		
		if(!debug){
			try {
				Image image = new Image("assets/shoot.png");
				scatterShoot.addComponent(new ImageRenderComponent(image));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		} else scatterShoot.setSize(new Vector2f(10,10));
		
		
		Event mainEvent = new TimeEvent(time, false);
		mainEvent.addAction(new DestroyEntityAction());
		mainEvent.addAction(new SpawnShootAction((rotation-90 +360) %360 ,strength/5));
		mainEvent.addAction(new SpawnShootAction((rotation-45 +360) %360, strength/5));
		mainEvent.addAction(new SpawnShootAction(rotation, strength/5));
		mainEvent.addAction(new SpawnShootAction((rotation+45+360) %360, strength/5));
		mainEvent.addAction(new SpawnShootAction((rotation+90+360) %360, strength/5));
		scatterShoot.addComponent(mainEvent);
		
		mainEvent = new LeavingScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		//mainEvent.addAction(new ResetEntity(new Vector2f(gc.getWidth()/2, gc.getHeight()/2)));
		scatterShoot.addComponent(mainEvent);
		
		
		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(strength));
		scatterShoot.addComponent(mainEvent);
		
		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveForwardAction(0.30f));
		scatterShoot.addComponent(mainEvent);
		
		
		
		return scatterShoot;
	}

}
