package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.tanks.model.action.AddComponentsAction;
import de.tu_darmstadt.gdi1.tanks.model.action.HitAction;
import de.tu_darmstadt.gdi1.tanks.model.entities.Mine;
import de.tu_darmstadt.gdi1.tanks.model.event.TimeEvent;

import eea.engine.action.basicactions.RemoveEventAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
import eea.engine.interfaces.IEntityFactory;

public class MineFactory implements IEntityFactory {
	
	private final Vector2f pos;
	private final boolean debug;
	private final int streangth;
	private final float scaling;
	
	
	public MineFactory(Vector2f pos, float scaling,int streangth,boolean debug){
		this.pos = pos;
		this.scaling = scaling;
		this.streangth = streangth;
		this.debug = debug;
	}

	@Override
	public Entity createEntity() {
		Entity mine = new Mine("Mine"+Math.random(), streangth);
		mine.setScale(scaling);
		mine.setPosition(pos);
		
		if(!debug){
			try {
				mine.addComponent(new ImageRenderComponent(new Image("assets/mine.png")));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		} else mine.setSize(new Vector2f(10,10));
		
		
		//Event mainEvent = new TimeEvent(15000, false);
		//mainEvent.addAction(new DestroyEntityAction());
		//mine.addComponent(mainEvent);
		
		Event mainEvent = new TimeEvent(2000, false);
		
		Event secondaryEvent = new CollisionEvent();
		secondaryEvent.addAction(new HitAction(streangth));
		
		mainEvent.addAction(new AddComponentsAction(secondaryEvent));
		mainEvent.addAction(new RemoveEventAction());
		
		
		mine.addComponent(mainEvent);
		
		
		
		
		return mine;
	}

}
