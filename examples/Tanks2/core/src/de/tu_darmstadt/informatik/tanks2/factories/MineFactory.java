package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.AddComponentsAction;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.RemoveEventAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.event.CollisionEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.TimeEvent;
import de.tu_darmstadt.informatik.tanks2.actions.HitAction;
import de.tu_darmstadt.informatik.tanks2.entities.Mine;

public class MineFactory {

	private final boolean debug;
	private IResourcesManager resourcesManager;
	private ExplosionFactory explosionFactory;

	public MineFactory(IResourcesManager resourcesManager, ExplosionFactory explosionFactory) {
		this(resourcesManager, explosionFactory, false);
	}

	public MineFactory(IResourcesManager resourcesManager, ExplosionFactory explosionFactory, boolean debug) {
		this.resourcesManager = resourcesManager;
		this.explosionFactory = explosionFactory;
		this.debug = debug;
	}

	public Entity createEntity(float x, float y, float scale, int strength) {
		Entity mine = new Mine("Mine" + Math.random(), strength);
		mine.setScale(scale);
		mine.setPosition(x, y);

		mine.addComponent(new ImageRenderComponent("mine.png", resourcesManager));

		// If something collides with the mine, deal damage and destroy the
		// mine.
		EEAEvent collisionEvent = new CollisionEvent();
		collisionEvent.addAction(new HitAction(strength, explosionFactory));
		collisionEvent.addAction(new DestroyEntityAction());

		// The mine should be armed with a delay.
		TimeEvent timeEvent = new TimeEvent(2.5f, false);
		timeEvent.addAction(new AddComponentsAction(mine, collisionEvent));
		timeEvent.addAction(new RemoveEventAction(timeEvent));

		mine.addComponent(timeEvent);

		return mine;
	}

}
