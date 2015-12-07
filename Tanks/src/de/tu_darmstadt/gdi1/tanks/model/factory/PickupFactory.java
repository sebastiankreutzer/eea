package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.action.AddComponentsAction;
import de.tu_darmstadt.gdi1.tanks.model.action.ChangeAmmoAction;
import de.tu_darmstadt.gdi1.tanks.model.action.ChangeLifeAction;
import de.tu_darmstadt.gdi1.tanks.model.entities.Pickup;
import de.tu_darmstadt.gdi1.tanks.model.event.TimeEvent;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.RemoveEventAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.interfaces.IEntityFactory;

public class PickupFactory implements IEntityFactory {
	
	private final String type;
	private final int strength;
	private final float rotation;
	private final float scaling;
	private final Vector2f position;
	private final boolean debug;

	public PickupFactory(String type, int strength, float rotation, float scaling, float x, float y, boolean debug){
		this.type = type;
		this.strength = strength;
		this.rotation = rotation;
		this.scaling = scaling;
		this.position = new Vector2f(x,y);
		this.debug = debug;
	}
	
	@Override
	public Entity createEntity() {
		Pickup pickup = new Pickup(type);
		pickup.setStreangth(strength);
		pickup.setScale(scaling);
		pickup.setPosition(position);
		pickup.setRotation(rotation);
		
		Event mainEvent = new TimeEvent(8000, false);
		mainEvent.addAction(new DestroyEntityAction());
		pickup.addComponent(mainEvent);
		
		mainEvent = new TimeEvent(5000, false);
		Event secondaryEvent = new TimeEvent(100, true);
		secondaryEvent.addAction(new Action(){

			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
				event.getOwnerEntity().setVisible(!event.getOwnerEntity().isVisible());
				
			}
			
		});
		mainEvent.addAction(new AddComponentsAction(secondaryEvent));
		mainEvent.addAction(new RemoveEventAction());
		pickup.addComponent(mainEvent);
		
		mainEvent = new CollisionEvent();
		
		if(type.equals(Tanks.healthpack)){
			if(!debug){
				try {
					pickup.addComponent(new ImageRenderComponent(new Image("assets/healthpack.png")));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			} else pickup.setSize(new Vector2f(10,10));
			mainEvent.addAction(new ChangeLifeAction(strength));
			
		} else if(type.equals(Tanks.ammopack)){
			if(!debug){
				try {
					pickup.addComponent(new ImageRenderComponent(new Image("assets/munipack.png")));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			} else pickup.setSize(new Vector2f(10,10));
			
			mainEvent.addAction(new ChangeAmmoAction(strength));
		}
		
		pickup.addComponent(mainEvent);
		
		return pickup;
	}

}
