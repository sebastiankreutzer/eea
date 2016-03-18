package de.tu_darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.AddComponentsAction;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.RemoveEventAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.RectangleTriggerComponent;
import de.tu_darmstadt.informatik.eea.event.CollisionEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.TimeEvent;
import de.tu_darmstadt.informatik.tanks2.actions.ChangeAmmoAction;
import de.tu_darmstadt.informatik.tanks2.actions.ChangeLifeAction;
import de.tu_darmstadt.informatik.tanks2.entities.Pickup;
import de.tu_darmstadt.informatik.tanks2.entities.Pickup.PickupType;

/**
 * Eine Factroy zum erzeugen von Pickup Entities.
 * 
 * @author jr
 *
 */
public class PickupFactory {

	private final boolean debug;
	private IResourceManager resourcesManager;

	/**
	 * Erzeugt eine neue PickupFactory
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager fuer die Bilder
	 * @param debug
	 *            Der Debugmodus
	 */
	public PickupFactory(IResourceManager resourcesManager, boolean debug) {
		this.resourcesManager = resourcesManager;
		this.debug = debug;
	}

	/**
	 * Erstellt eine neues Pickup.
	 * 
	 * @param type
	 *            Der {@link PickupType} des Pickups
	 * @param strength
	 *            Die Staerke des Pickups
	 * @param x
	 *            Die x Position
	 * @param y
	 *            Die y Position
	 * @param scale
	 *            Die Skalierung
	 * @return Ein Pickup, das bei Kontakt Lebenspunkte oder Munition auffuelt.
	 */
	public Pickup createPickup(PickupType type, int strength, float x, float y, float scale) {
		// Erzeuge ein neues Pickup mit den gegebenen Parametern
		Pickup pickup = new Pickup(type);
		pickup.setStrength(strength);
		pickup.setScale(scale);
		pickup.setPosition(x, y);
		pickup.addComponent(new RectangleTriggerComponent());

		// Nach 8 Sekunden soll das Pickup verschwinden
		TimeEvent disappearEvent = new TimeEvent(8, false);
		disappearEvent.addAction(new DestroyEntityAction());
		pickup.addComponent(disappearEvent);

		// Erzeuge ein Event mit Action welche das Pickup blinken laesst
		EEAEvent blinkEvent = new TimeEvent(0.1f, true);
		blinkEvent.addAction(new Action() {

			@Override
			public boolean act(float delta) {
				getActor().setVisible(!getActor().isVisible());
				return true;
			}
		});

		// Nach 5 Sekunden soll das Pickup anfange zu blinken
		TimeEvent enableBlinking = new TimeEvent(5, false);
		enableBlinking.addAction(new AddComponentsAction(pickup, blinkEvent));
		enableBlinking.addAction(new RemoveEventAction(enableBlinking));
		pickup.addComponent(enableBlinking);

		// Bei einer Kollision soll je nach PickupType die Munition oder
		// Lebenspunkte aufgefuellt werden.
		EEAEvent collisionEvent = new CollisionEvent();
		switch (type) {
		case HEALTH:
			pickup.addComponent(new ImageRenderComponent("healthpack.png", resourcesManager));
			collisionEvent.addAction(new ChangeLifeAction(strength));
			break;

		case AMMUNITION:
			pickup.addComponent(new ImageRenderComponent("munipack.png", resourcesManager));
			collisionEvent.addAction(new ChangeAmmoAction(strength));
			break;

		default:
			break;
		}
		collisionEvent.addAction(new DestroyEntityAction());
		pickup.addComponent(collisionEvent);

		return pickup;
	}

}
