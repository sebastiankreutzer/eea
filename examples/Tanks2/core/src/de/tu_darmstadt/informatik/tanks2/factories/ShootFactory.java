package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.RectangleCollisionComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.RectangleTriggerComponent;
import de.tu_darmstadt.informatik.eea.event.CollisionEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.EntityOutOfScreenEvent;
import de.tu_darmstadt.informatik.eea.event.LoopEvent;
import de.tu_darmstadt.informatik.eea.event.TimeEvent;
import de.tu_darmstadt.informatik.tanks2.actions.HitAction;
import de.tu_darmstadt.informatik.tanks2.actions.SpawnShootAction;
import de.tu_darmstadt.informatik.tanks2.entities.ScatterShoot;
import de.tu_darmstadt.informatik.tanks2.entities.Shoot;

public class ShootFactory {

	protected IResourcesManager resourcesManager;
	protected ExplosionFactory explosionFactory;
	protected final boolean debug;
	
	public ShootFactory(IResourcesManager resourcesManager, ExplosionFactory explosionFactory) {
		this(resourcesManager, explosionFactory, false);
	} 

	public ShootFactory(IResourcesManager resourcesManager, ExplosionFactory explosionFactory, boolean debug) {
		this.resourcesManager = resourcesManager;
		this.explosionFactory = explosionFactory;
		this.debug = debug;
	}

	public Entity createShot(float x, float y, String owner, int strength, float rotation, float scale) {

		Entity simpleShot = new Shoot(owner + "SimpleShoot" + Math.random(), strength);

		simpleShot.setPosition(x, y);
		simpleShot.setRotation(rotation);
		simpleShot.setScale(scale);

		simpleShot.addComponent(new ImageRenderComponent("shoot.png", resourcesManager));

		EEAEvent mainEvent = new EntityOutOfScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		simpleShot.addComponent(mainEvent);

		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(strength, explosionFactory));
		simpleShot.addComponent(mainEvent);

		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveRelativeAction(75f, 0f));
		simpleShot.addComponent(mainEvent);
		
		simpleShot.addComponent(new RectangleTriggerComponent());

		return simpleShot;
	}
	
	public Entity createScatterShot(float x, float y, String owner, int damage, float rotation, float scale, float time) {
		Entity scatterShoot = new ScatterShoot(owner, damage, time);

		scatterShoot.setPosition(x, y);
		scatterShoot.setRotation(rotation);
		scatterShoot.setScale(scale);

		scatterShoot.addComponent(new ImageRenderComponent("shoot.png", resourcesManager));

		EEAEvent mainEvent = new TimeEvent(time, false);
		mainEvent.addAction(new SpawnShootAction(rotation - 90, damage / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation - 45, damage / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation, damage / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation + 45, damage / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation + 90, damage / 5, this));
		mainEvent.addAction(new DestroyEntityAction());
		scatterShoot.addComponent(mainEvent);

		mainEvent = new EntityOutOfScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		scatterShoot.addComponent(mainEvent);

		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(damage, explosionFactory));
		scatterShoot.addComponent(mainEvent);

		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveRelativeAction(60f, 0f));
		scatterShoot.addComponent(mainEvent);
		
		scatterShoot.addComponent(new RectangleTriggerComponent());

		return scatterShoot;
	}

}
