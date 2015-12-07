package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.EEAGraphics;
import de.tu.darmstadt.informatik.eea.action.RemoveEventAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.CollisionEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.tanks2.actions.AddComponentsAction;
import de.tu.darmstadt.informatik.tanks2.actions.HitAction;
import de.tu.darmstadt.informatik.tanks2.entities.Mine;
import de.tu.darmstadt.informatik.tanks2.events.TimeEvent;

public class MineFactory {
	
	private final Vector2 pos;
	private final boolean debug;
	private final int streangth;
	private final float scaling;
	private EEAGraphics eeaGraphics;
	
	
	public MineFactory(Vector2 pos, float scaling,int streangth,boolean debug, EEAGraphics eeaGraphics){
		this.pos = pos;
		this.scaling = scaling;
		this.streangth = streangth;
		this.debug = debug;
		this.eeaGraphics = eeaGraphics;
	}
	
	public Entity createEntity() {
		Entity mine = new Mine("Mine"+Math.random(), streangth);
		mine.setScale(scaling);
		mine.setPosition(pos.x, pos.y);

		mine.addComponent(new ImageRenderComponent("mine.png", eeaGraphics));
		
		//Event mainEvent = new TimeEvent(15000, false);
		//mainEvent.addAction(new DestroyEntityAction());
		//mine.addComponent(mainEvent);
		
		EEAEvent mainEvent = new TimeEvent(2000, false);
		
		EEAEvent secondaryEvent = new CollisionEvent();
		secondaryEvent.addAction(new HitAction(streangth, eeaGraphics));
		
		mainEvent.addAction(new AddComponentsAction(mine, secondaryEvent));
		mainEvent.addAction(new RemoveEventAction(mainEvent));
		
		mine.addComponent(mainEvent);
		
		return mine;
	}

}
