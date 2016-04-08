package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.AddComponentsAction;
import de.tu_darmstadt.informatik.eea.action.RemoveEventAction;
import de.tu_darmstadt.informatik.eea.component.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.component.collision.CircleTriggerComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.CollisionEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.TimedEvent;
import de.tu_darmstadt.informatik.tanks2.actions.HitAction;

/**
 * Eine MineFactory zum erzeugen von Minen.
 * @author jr
 *
 */
public class MineFactory {

	private final boolean debug;
	private IResourceManager resourcesManager;
	private ExplosionFactory explosionFactory;
	
	/**
	 * Erzeugt eine neue MineFactory
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @param explosionFactory
	 *            Die ExplosionFactory
	 */
	public MineFactory(IResourceManager resourcesManager, ExplosionFactory explosionFactory) {
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
	public MineFactory(IResourceManager resourcesManager, ExplosionFactory explosionFactory, boolean debug) {
		this.resourcesManager = resourcesManager;
		this.explosionFactory = explosionFactory;
		this.debug = debug;
	}

	public Entity createMine(float x, float y, float scale, int strength) {
		// Erzeuge einen neue Entity und setze Skalierung und Position
		Entity mine = new Entity("Mine" + Math.random());
		mine.setScale(scale);
		mine.setPosition(x, y);

		// Fuege die RenderComponent hinzu
		mine.addComponent(new ImageRenderComponent("mine.png"));

		// Erstelle ein CollisionEvent mit einer HitAction
		EEAEvent collisionEvent = new CollisionEvent();
		collisionEvent.addAction(new HitAction(strength, explosionFactory));
		mine.addComponent(new CircleTriggerComponent());

		// Die Mine soll erst nach einiger Zeit scharf gemacht werden
		TimedEvent timeEvent = new TimedEvent(2.5f, false);
		timeEvent.addAction(new AddComponentsAction(mine, collisionEvent));
		timeEvent.addAction(new RemoveEventAction(timeEvent));

		mine.addComponent(timeEvent);
		return mine;
	}

}
