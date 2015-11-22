package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.RotateAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.ANDEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeShootAmmoAction;
import de.tu.darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tower;
import de.tu.darmstadt.informatik.tanks2.events.AIRotateLeftEvent;
import de.tu.darmstadt.informatik.tanks2.events.AIRotateRightEvent;
import de.tu.darmstadt.informatik.tanks2.events.AIShootEvent;
import de.tu.darmstadt.informatik.tanks2.events.HasShootAmmoLeftEvent;
import de.tu.darmstadt.informatik.tanks2.events.TimeEvent;

public class TowerFactory {

	private final int maxLife;
	private final int life; 
	private final int maxShoots;
	private final int shoots;
	private final int streangth;
	private final float speed;
	private final int rotation;
	private final float scaling;
	private final Vector2 position;
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
		position = new Vector2(x,y);
		this.debug = debug;
		
	}
	
	public Entity createEntity() {
		
		Tower tower = new Tower("Tower"+Math.random());
		tower.setMaxLife(maxLife);
		tower.setLife(life);
		tower.setShootMaxAmmo(maxShoots);
		tower.setShootAtmmo(shoots);
		tower.setStreangth(streangth);
		tower.setRotation(rotation);
		tower.setScale(scaling);
		tower.setPosition(position.x, position.y);
		tower.setSpeed(speed);
		tower.setPassable(false);
		
		tower.addComponent(new ImageRenderComponent(new Texture("flac.png")));
		
		
		EEAEvent mainEvent = new AIRotateLeftEvent();
		mainEvent.addAction(new RotateAction(-speed));
		tower.addComponent(mainEvent);
		
		mainEvent = new AIRotateRightEvent();
		mainEvent.addAction(new RotateAction(speed));
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
