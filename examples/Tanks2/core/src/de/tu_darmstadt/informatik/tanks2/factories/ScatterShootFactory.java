package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.event.CollisionEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.EntityOutOfScreenEvent;
import de.tu_darmstadt.informatik.eea.event.LoopEvent;
import de.tu_darmstadt.informatik.eea.event.TimeEvent;
import de.tu_darmstadt.informatik.tanks2.actions.HitAction;
import de.tu_darmstadt.informatik.tanks2.actions.SpawnShootAction;
import de.tu_darmstadt.informatik.tanks2.entities.ScatterShoot;

public class ScatterShootFactory extends ShootFactory {

	public ScatterShootFactory(IResourceManager resourcesManager, ExplosionFactory explosionFactory) {
		this(resourcesManager, explosionFactory, false);
	}
	
	public ScatterShootFactory(IResourceManager resourcesManager, ExplosionFactory explosionFactory, boolean debug) {
		super(resourcesManager, explosionFactory, debug);
	}

	public Entity createEntity(float x, float y, String owner, int strength, float time, float rotation, float scale) {

		Entity scatterShoot = new ScatterShoot(owner, strength, time);

		scatterShoot.setPosition(x, y);
		scatterShoot.setRotation(rotation);
		scatterShoot.setScale(scale);

		scatterShoot.addComponent(new ImageRenderComponent("shoot.png", resourcesManager));

		EEAEvent mainEvent = new TimeEvent(time, false);
		mainEvent.addAction(new DestroyEntityAction());
//		mainEvent.addAction(new SpawnShootAction((rotation - 90 + 360) % 360, strength / 5, resourcesManager));
//		mainEvent.addAction(new SpawnShootAction((rotation - 45 + 360) % 360, strength / 5, resourcesManager));
//		mainEvent.addAction(new SpawnShootAction(rotation, strength / 5, resourcesManager));
//		mainEvent.addAction(new SpawnShootAction((rotation + 45 + 360) % 360, strength / 5, resourcesManager));
//		mainEvent.addAction(new SpawnShootAction((rotation + 90 + 360) % 360, strength / 5, resourcesManager));
		scatterShoot.addComponent(mainEvent);

		mainEvent = new EntityOutOfScreenEvent();
		mainEvent.addAction(new DestroyEntityAction());
		scatterShoot.addComponent(mainEvent);

		mainEvent = new CollisionEvent();
		mainEvent.addAction(new HitAction(strength, explosionFactory));
		scatterShoot.addComponent(mainEvent);

		mainEvent = new LoopEvent();
		mainEvent.addAction(new MoveRelativeAction(30f, 0f));
		scatterShoot.addComponent(mainEvent);

		return scatterShoot;
	}

}
