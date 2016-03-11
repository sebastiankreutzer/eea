package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.AddComponentsAction;
import de.tu_darmstadt.informatik.eea.action.RemoveEventAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.event.CollisionEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.TimeEvent;
import de.tu_darmstadt.informatik.tanks2.actions.HitAction;

/**
 * Eine MineFactory zum erzeugen von Minen.
 * @author jr
 *
 */
public class MineFactory {

	private final boolean debug;
	private IResourcesManager resourcesManager;
	private ExplosionFactory explosionFactory;
	
	/**
	 * Erzeugt eine neue MineFactory
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @param explosionFactory
	 *            Die ExplosionFactory
	 */
	public MineFactory(IResourcesManager resourcesManager, ExplosionFactory explosionFactory) {
		this(resourcesManager, explosionFactory, false);
	}

	/**
	 * Erzeugt eine neue MineFactory
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @param explosionFactory
	 *            Die ExplosionFactory
	 * @param debug
	 *            Der Zustand des Debugmodus
	 */
	public MineFactory(IResourcesManager resourcesManager, ExplosionFactory explosionFactory, boolean debug) {
		this.resourcesManager = resourcesManager;
		this.explosionFactory = explosionFactory;
		this.debug = debug;
	}

	public Entity createEntity(float x, float y, float scale, int strength) {
		// Erzeuge einen neue Entity und setze Skalierung und Position
		Entity mine = new Entity("Mine" + Math.random());
		mine.setScale(scale);
		mine.setPosition(x, y);

		// Fuege die RenderComponent hinzu
		mine.addComponent(new ImageRenderComponent("mine.png", resourcesManager));

		// Erstelle ein CollisionEvent mit einer HitAction
		EEAEvent collisionEvent = new CollisionEvent();
		collisionEvent.addAction(new HitAction(strength, explosionFactory));

		// Die Mine soll erst nach einiger Zeit scharf gemacht werden
		TimeEvent timeEvent = new TimeEvent(2.5f, false);
		timeEvent.addAction(new AddComponentsAction(mine, collisionEvent));
		timeEvent.addAction(new RemoveEventAction(timeEvent));

		mine.addComponent(timeEvent);
		return mine;
	}

}
