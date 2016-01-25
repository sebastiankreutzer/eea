package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.tanks.model.action.HitAction;
import de.tu_darmstadt.gdi1.tanks.model.entities.Shoot;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.interfaces.IEntityFactory;

public class ShootFactory implements IEntityFactory {
	
	protected final float rotation;
	protected final float scale;
	protected final Vector2f position;
	protected final String owner;
	protected final int strength;
	protected final boolean debug;
	
	public ShootFactory(int strength, String owner,float rotation, float scale , float x, float y, boolean debug){
		this.owner = owner;
		this.rotation = rotation;
		this.scale = scale;
		this.position = new Vector2f(x,y);
		this.strength = strength;
		this.debug = debug;
	}

	@Override
	public Entity createEntity() {
		
		Entity simpleShot = new Shoot(owner+"SimpleShoot"+Math.random(), strength);


		
		
		simpleShot.setPosition(position);
		simpleShot.setRotation(rotation);
		simpleShot.setScale(scale);
		
		if(!debug){
			try {
				Image image = new Image("assets/shoot.png");
				//image.setRotation(event.getOwnerEntity().getRotation());
				simpleShot.addComponent(new ImageRenderComponent(image));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		} else {
			simpleShot.setSize(new Vector2f(10,10));
		}
		

		
		Event mainEvent = new LeavingScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		//mainEvent.addAction(new ResetEntity(new Vector2f(gc.getWidth()/2, gc.getHeight()/2)));
		simpleShot.addComponent(mainEvent);
		
		
		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(strength));
		simpleShot.addComponent(mainEvent);
		
		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveForwardAction(0.30f));
		simpleShot.addComponent(mainEvent);
		
		
		return simpleShot;
	}

}
