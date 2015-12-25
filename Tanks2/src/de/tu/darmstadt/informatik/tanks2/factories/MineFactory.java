package de.tu.darmstadt.informatik.tanks2.factories;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.action.AddComponentsAction;
import de.tu.darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu.darmstadt.informatik.eea.action.RemoveEventAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.CollisionEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.TimeEvent;
import de.tu.darmstadt.informatik.tanks2.actions.HitAction;
import de.tu.darmstadt.informatik.tanks2.entities.Mine;

public class MineFactory {

	private final boolean debug;
	private IResourcesManager resourcesManager;

	public MineFactory(boolean debug, IResourcesManager resourcesManager) {
		this.debug = debug;
		this.resourcesManager = resourcesManager;
	}

	public Entity createEntity(float x, float y, float scale, int strength) {
		Entity mine = new Mine("Mine" + Math.random(), strength);
		mine.setScale(scale);
		mine.setPosition(x, y);

		mine.addComponent(new ImageRenderComponent("mine.png", resourcesManager));

		// If something collides with the mine, deal damage and destroy the
		// mine.
		EEAEvent collisionEvent = new CollisionEvent();
		collisionEvent.addAction(new HitAction(strength));
		collisionEvent.addAction(new DestroyEntityAction());

		// The mine should be armed with a delay.
		TimeEvent timeEvent = new TimeEvent(2.5f, false);
		timeEvent.addAction(new AddComponentsAction(mine, collisionEvent));
		timeEvent.addAction(new RemoveEventAction(timeEvent));

		mine.addComponent(timeEvent);

		return mine;
	}

}
