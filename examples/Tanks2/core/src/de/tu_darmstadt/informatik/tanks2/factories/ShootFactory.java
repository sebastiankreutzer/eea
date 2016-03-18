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

/**
 * Eine Factory fuer Schuesse und Streuschuesse.
 * 
 * @author jr
 *
 */
public class ShootFactory {

	protected IResourcesManager resourcesManager;
	protected ExplosionFactory explosionFactory;
	protected final boolean debug;

	/**
	 * Erzeugt eine neue ShootFactory
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager fuer die Bilder
	 * @param explosionFactory
	 *            Die ExplosionFactory fuer die Explosionen
	 * @param debug
	 *            Der Debugmodus
	 */
	public ShootFactory(IResourcesManager resourcesManager, ExplosionFactory explosionFactory, boolean debug) {
		this.resourcesManager = resourcesManager;
		this.explosionFactory = explosionFactory;
		this.debug = debug;
	}

	/**
	 * Erzeugt einen neuen Schuss.
	 * 
	 * @param x
	 * @param y
	 * @param ownerID
	 *            Die ID des Schuetzen
	 * @param damage
	 *            Die Staerke des Schusses
	 * @param rotation
	 *            Die Rotation
	 * @param scale
	 *            Die Skalierung
	 * @return Einen Shot
	 */
	public Shoot createShot(float x, float y, String owner, int strength, float rotation, float scale) {

		// Erstelle einen Schuss und setzte Position, Rotation und Skalierung
		Shoot simpleShot = new Shoot(owner + "SimpleShoot" + Math.random(), strength);
		initShot(simpleShot, strength, 75f, x, y, rotation, scale);

		return simpleShot;
	}

	/**
	 * Setzt Position, Rotation und Skalierung eines Schusses und fuegt die
	 * spezifischen Events und Actions hinzu.
	 * 
	 * @param shot
	 * Der Schuss
	 * @param damage
	 * Der Schaden des Schusses
	 * @param speed
	 * Die Geschwindigkeit des Schusses
	 * @param x
	 * @param y
	 * @param rotation
	 * @param scale
	 */
	private void initShot(Entity shot, int damage, float speed, float x, float y, float rotation, float scale) {
		shot.setPosition(x, y);
		shot.setRotation(rotation);
		shot.setScale(scale);
		shot.addComponent(new ImageRenderComponent("shoot.png", resourcesManager));
		shot.addComponent(new RectangleTriggerComponent());

		// Der Schuss soll beim Verlassen des Bildschirms zerstoert werden.
		EntityOutOfScreenEvent outOfScreen = new EntityOutOfScreenEvent();
		outOfScreen.addAction(new DestroyEntityAction());
		shot.addComponent(outOfScreen);

		// Bei einer Kollision soll Schaden hinzugefuegt werden.
		CollisionEvent collision = new CollisionEvent();
		collision.addAction(new HitAction(damage, explosionFactory));
		shot.addComponent(collision);

		// Der Schuss soll sich vorwaerts bewegem
		LoopEvent loopEvent = new LoopEvent();
		loopEvent.addAction(new MoveRelativeAction(speed, 0f));
		shot.addComponent(loopEvent);
	}

	/**
	 * Erzeugt eine neuen ScatterShot.
	 * 
	 * @param x
	 * @param y
	 * @param ownerID
	 *            Die ID des Schuetzen
	 * @param damage
	 *            Die Staerke des Schusses
	 * @param rotation
	 *            Die Rotation
	 * @param scale
	 *            Die Skalierung
	 * @param fuseTime
	 *            Die Zeit bis die Muntion zerfaellt
	 * @return Einen ScatterShot
	 */
	public ScatterShoot createScatterShot(float x, float y, String ownerID, int damage, float rotation, float scale,
			float fuseTime) {
		ScatterShoot scatterShoot = new ScatterShoot(ownerID, damage, fuseTime);

		initShot(scatterShoot, damage, 60f, x, y, rotation, scale);

		// Der Schuss soll nach der Zuendzeit in kleinere Schuesse zerfallen.
		EEAEvent mainEvent = new TimeEvent(fuseTime, false);
		mainEvent.addAction(new SpawnShootAction(rotation - 90, damage / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation - 45, damage / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation, damage / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation + 45, damage / 5, this));
		mainEvent.addAction(new SpawnShootAction(rotation + 90, damage / 5, this));
		mainEvent.addAction(new DestroyEntityAction());
		scatterShoot.addComponent(mainEvent);

		return scatterShoot;
	}

}
