package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.CircleCollisionComponent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.TimeEvent;
import de.tu_darmstadt.informatik.tanks2.AI.TowerAI;
import de.tu_darmstadt.informatik.tanks2.actions.ChangeShootAmmoAction;
import de.tu_darmstadt.informatik.tanks2.entities.Tower;
import temp.removeASAP.Tanks;

/**
 * Eine Factory die Tower erzeugt, stationaere Entities die sich drehen und
 * schiessen koennen.
 * 
 * @author jr
 *
 */
public class TowerFactory {

	private static int counter = 0;

	private final boolean debug;
	private IResourcesManager resourcesManager;
	private ShootFactory shotFactory;

	/**
	 * Erzeugt eine TowerFactory.
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @param shotFactory
	 *            Die ShootFactory
	 * @param debug
	 *            Der DebugModus
	 */
	public TowerFactory(IResourcesManager resourcesManager, ShootFactory shotFactory, boolean debug) {
		this.debug = debug;
		this.resourcesManager = resourcesManager;
		this.shotFactory = shotFactory;

	}

	/**
	 * Erzeugt einen neuen Tower.
	 * 
	 * @param x
	 *            Die Position auf der x-Achse
	 * @param y
	 *            Die Position auf der y-Achse
	 * @param maxLife
	 *            Die maximale Anzahl an Lebenspunkten
	 * @param life
	 *            Die tatsaechliche Anzahl an Lebenspunkten
	 * @param maxShoots
	 *            Die maximale Anzahl der an Munition
	 * @param shoots
	 *            Die tatsaechliche Anzahl an Munition
	 * @param strength
	 *            Die Staerke der Schuesse
	 * @param speed
	 *            Die Rotationsgeschwindigkeit
	 * @param rotation
	 *            Die Rotation
	 * @param scaling
	 *            Die Skalierung
	 * @return Den Tower
	 */
	public Entity createTower(float x, float y, int maxLife, int life, int maxShoots, int shoots, int strength,
			float speed, float rotation, float scaling) {

		// Erzeuge einen neue Tower und setze die Parameter
		Tower tower = new Tower("Tower " + (counter++));
		tower.setMaxLife(maxLife);
		tower.setLife(life);
		tower.setShootMaxAmmo(maxShoots);
		tower.setShootAtmmo(shoots);
		tower.setStrength(strength);
		tower.setRotation(rotation);
		tower.setScale(scaling);
		tower.setPosition(x, y);
		tower.setSpeed(speed);

		// Der Turm ist rund, waehle eine entsprechende CollisionComponent
		tower.addComponent(new CircleCollisionComponent());
		// Setze die RenderComponent
		tower.addComponent(new ImageRenderComponent("flac.png", resourcesManager));
		// Der Turm wird von der KI gesteuert
		tower.addComponent(new TowerAI(Tanks.player1, shotFactory, debug));

		// Die Munition des Turms soll regelmaessig nachgeladen werden
		EEAEvent reloadEvent = new TimeEvent(1000, true);
		reloadEvent.addAction(new ChangeShootAmmoAction(1));
		tower.addComponent(reloadEvent);

		return tower;
	}

}
