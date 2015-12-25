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
import de.tu.darmstadt.informatik.tanks2.entities.Shoot;

public class ShootFactory {

	protected final boolean debug;
	protected IResourcesManager resourcesManager;

	public ShootFactory(boolean debug, IResourcesManager resourcesManager) {
		this.debug = debug;
		this.resourcesManager = resourcesManager;
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
		mainEvent.addAction(new HitAction(strength));
		simpleShot.addComponent(mainEvent);

		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveRelativeAction(25f, 0f));
		simpleShot.addComponent(mainEvent);

		return simpleShot;
	}
	
	public Entity createScatterShot(float x, float y, String owner, int strength, float rotation, float scale, float time) {
		Entity scatterShoot = new ScatterShoot(owner, strength, time);

		scatterShoot.setPosition(x, y);
		scatterShoot.setRotation(rotation);
		scatterShoot.setScale(scale);

		scatterShoot.addComponent(new ImageRenderComponent("shoot.png", resourcesManager));

		EEAEvent mainEvent = new TimeEvent(time, false);
		mainEvent.addAction(new SpawnShootAction((rotation - 90 + 360) % 360, strength / 5, this));
		mainEvent.addAction(new SpawnShootAction((rotation - 45 + 360) % 360, strength / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation, strength / 5, this));
		mainEvent.addAction(new SpawnShootAction((rotation + 45 + 360) % 360, strength / 5, this));
		mainEvent.addAction(new SpawnShootAction((rotation + 90 + 360) % 360, strength / 5, this));
		mainEvent.addAction(new DestroyEntityAction());
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
