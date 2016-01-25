package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.tanks.model.action.ChangeShootAmmoAction;
import de.tu_darmstadt.gdi1.tanks.model.action.ShootAction;
import de.tu_darmstadt.gdi1.tanks.model.entities.Tower;
import de.tu_darmstadt.gdi1.tanks.model.event.AIRotateLeftEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.AIRotateRightEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.AIShootEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.HasShootAmmoLeftEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.TimeEvent;

import eea.engine.action.basicactions.RotateLeftAction;
import eea.engine.action.basicactions.RotateRightAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.ANDEvent;
import eea.engine.interfaces.IEntityFactory;

public class TowerFactory implements IEntityFactory {

	private final int maxLife;
	private final int life; 
	private final int maxShoots;
	private final int shoots;
	private final int streangth;
	private final float speed;
	private final int rotation;
	private final float scaling;
	private final Vector2f position;
	private final boolean debug;
	
	public TowerFactory(int maxLife, int life, int maxShoots, int shoots, int streangth, float speed,int rotation, float scaling, int x, int y, boolean debug){
		this.maxLife = maxLife;
		this.life = life;
		this.maxShoots = maxShoots;
		this.shoots = shoots;
		this.streangth = streangth;
		this.speed = speed;
		this.rotation = rotation;
		this.scaling = scaling;
		position = new Vector2f(x,y);
		this.debug = debug;
		
	}
	
	
	@Override
	public Entity createEntity() {
		
		Tower tower = new Tower("Tower"+Math.random());
		tower.setMaxLife(maxLife);
		tower.setLife(life);
		tower.setShootMaxAmmo(maxShoots);
		tower.setShootAtmmo(shoots);
		tower.setStreangth(streangth);
		tower.setRotation(rotation);
		tower.setScale(scaling);
		tower.setPosition(position);
		tower.setSpeed(speed);
		tower.setPassable(false);
		
		try {
			if(!debug)tower.addComponent(new ImageRenderComponent(new Image("assets/flac.png")));
			else tower.setSize(new Vector2f(10,10));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
		Event mainEvent = new AIRotateLeftEvent();
		mainEvent.addAction(new RotateLeftAction(speed));
		tower.addComponent(mainEvent);
		
		mainEvent = new AIRotateRightEvent();
		mainEvent.addAction(new RotateRightAction(speed));
		tower.addComponent(mainEvent);
		
		mainEvent = new ANDEvent(new HasShootAmmoLeftEvent(),new AIShootEvent());
		mainEvent.addAction(new ShootAction());
		mainEvent.addAction(new ChangeShootAmmoAction(-1));		
		tower.addComponent(mainEvent);
		
		mainEvent = new TimeEvent(1000, true);
    	mainEvent.addAction(new ChangeShootAmmoAction(1));
    	tower.addComponent(mainEvent);
		
		
		return tower;
	}

}
