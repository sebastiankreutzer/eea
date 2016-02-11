package de.tu_darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
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
import de.tu_darmstadt.informatik.tanks2.entities.Pickup.PickUpType;

public class PickupFactory {

	private final boolean debug;
	private IResourcesManager resourcesManager;

	public PickupFactory(boolean debug, IResourcesManager resourcesManager) {
		this.debug = debug;
		this.resourcesManager = resourcesManager;
	}

	public Entity createEntity(PickUpType type, int strength, float x, float y, float scale) {
		Pickup pickup = new Pickup(type);
		pickup.setStrength(strength);
		pickup.setScale(scale);
		pickup.setPosition(x, y);

		EEAEvent mainEvent = new TimeEvent(8, false);
		mainEvent.addAction(new DestroyEntityAction());
		pickup.addComponent(mainEvent);

		mainEvent = new TimeEvent(5, false);
		EEAEvent secondaryEvent = new TimeEvent(0.1f, true);
		secondaryEvent.addAction(new Action() {

			@Override
			public boolean act(float delta) {
				getActor().setVisible(!getActor().isVisible());
				return true;
			}
		});

		mainEvent.addAction(new AddComponentsAction(pickup, secondaryEvent));
		mainEvent.addAction(new RemoveEventAction(mainEvent));
		pickup.addComponent(mainEvent);

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
		
		pickup.addComponent(new RectangleTriggerComponent());

		return pickup;
	}

}
