package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.AddComponentsAction;
import de.tu.darmstadt.informatik.eea.action.RemoveEventAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.CollisionEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.TimeEvent;
import de.tu.darmstadt.informatik.tanks2.actions.HitAction;
import de.tu.darmstadt.informatik.tanks2.entities.Mine;

public class MineFactory {
	
	private final Vector2 pos;
	private final boolean debug;
	private final int streangth;
	private final float scaling;
	
	
	public MineFactory(Vector2 pos, float scaling,int streangth,boolean debug){
		this.pos = pos;
		this.scaling = scaling;
		this.streangth = streangth;
		this.debug = debug;
	}
	
	public Entity createEntity() {
		Entity mine = new Mine("Mine"+Math.random(), streangth);
		mine.setScale(scaling);
		mine.setPosition(pos.x, pos.y);

		mine.addComponent(new ImageRenderComponent(new Texture("mine.png")));
		
		//Event mainEvent = new TimeEvent(15000, false);
		//mainEvent.addAction(new DestroyEntityAction());
		//mine.addComponent(mainEvent);
		
		EEAEvent mainEvent = new TimeEvent(2000, false);
		
		EEAEvent secondaryEvent = new CollisionEvent();
		secondaryEvent.addAction(new HitAction(streangth));
		
		mainEvent.addAction(new AddComponentsAction(mine, secondaryEvent));
		mainEvent.addAction(new RemoveEventAction(mainEvent));
		
		mine.addComponent(mainEvent);
		
		return mine;
	}

}
