package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.action.AddComponentsAction;
import de.tu.darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu.darmstadt.informatik.eea.action.RemoveEventAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.CollisionEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.TimeEvent;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeAmmoAction;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeLifeAction;
import de.tu.darmstadt.informatik.tanks2.entities.Pickup;
import de.tu.darmstadt.informatik.tanks2.entities.Pickup.PickUpType;

public class PickupFactory {
	
	private final PickUpType type;
	private final int strength;
	private final float rotation;
	private final float scaling;
	private final Vector2 position;
	private final boolean debug;

	public PickupFactory(PickUpType type, int strength, float rotation, float scaling, float x, float y, boolean debug){
		this.type = type;
		this.strength = strength;
		this.rotation = rotation;
		this.scaling = scaling;
		this.position = new Vector2(x,y);
		this.debug = debug;
	}
	
	public Entity createEntity() {
		Pickup pickup = new Pickup(type);
		pickup.setStrength(strength);
		pickup.setScale(scaling);
		pickup.setPosition(position.x, position.y);
		pickup.setRotation(rotation);
		
		EEAEvent mainEvent = new TimeEvent(8000, false);
		mainEvent.addAction(new DestroyEntityAction());
		pickup.addComponent(mainEvent);
		
		mainEvent = new TimeEvent(5000, false);
		EEAEvent secondaryEvent = new TimeEvent(100, true);
		secondaryEvent.addAction(new Action() {
			
			@Override
			public boolean act(float delta) {
				getActor().setVisible(!getActor().isVisible());
				return false;
			}
		});
		
		mainEvent.addAction(new AddComponentsAction(pickup, secondaryEvent));
		mainEvent.addAction(new RemoveEventAction(mainEvent));
		pickup.addComponent(mainEvent);
		
		mainEvent = new CollisionEvent();
		
		switch (type) {
		case AMMUNITION:
			pickup.addComponent(new ImageRenderComponent(new Texture("healthpack.png")));
			mainEvent.addAction(new ChangeLifeAction(strength));
			break;
			
		case HEALTH:
			pickup.addComponent(new ImageRenderComponent(new Texture("munipack.png")));
			mainEvent.addAction(new ChangeAmmoAction(strength));
			break;

		default:
			break;
		}
		
		pickup.addComponent(mainEvent);
		
		return pickup;
	}

}
