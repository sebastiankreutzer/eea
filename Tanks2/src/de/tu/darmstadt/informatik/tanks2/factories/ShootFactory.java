package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu.darmstadt.informatik.eea.action.MoveAction;
import de.tu.darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.CollisionEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.EntityOutOfScreenEvent;
import de.tu.darmstadt.informatik.eea.event.LoopEvent;
import de.tu.darmstadt.informatik.tanks2.actions.HitAction;
import de.tu.darmstadt.informatik.tanks2.entities.Shoot;

public class ShootFactory {
	
	protected final float rotation;
	protected final float scale;
	protected final Vector2 position;
	protected final String owner;
	protected final int strength;
	protected final boolean debug;
	
	public ShootFactory(int strength, String owner,float rotation, float scale , float x, float y, boolean debug){
		this.owner = owner;
		this.rotation = rotation;
		this.scale = scale;
		this.position = new Vector2(x,y);
		this.strength = strength;
		this.debug = debug;
	}
	
	public Entity createEntity() {
		
		Entity simpleShot = new Shoot(owner+"SimpleShoot"+Math.random(), strength);
		
		simpleShot.setPosition(position.x, position.y);
		simpleShot.setRotation(rotation);
		simpleShot.setScale(scale);
		
		simpleShot.addComponent(new ImageRenderComponent(new Texture("shoot.png")));
		
		EEAEvent mainEvent = new EntityOutOfScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		//mainEvent.addAction(new ResetEntity(new Vector2f(gc.getWidth()/2, gc.getHeight()/2)));
		simpleShot.addComponent(mainEvent);
		
		
		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(strength));
		simpleShot.addComponent(mainEvent);
		
		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveRelativeAction(25f, 0f));
		simpleShot.addComponent(mainEvent);
		
		
		return simpleShot;
	}

}
