package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.TimeEvent;
import de.tu.darmstadt.informatik.tanks2.AI.TowerAI;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeShootAmmoAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tower;
import temp.removeASAP.Tanks;


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
	private IResourcesManager resourcesManager;
	
	public TowerFactory(int maxLife, int life, int maxShoots, int shoots, int streangth, float speed,int rotation,
			float scaling, int x, int y, boolean debug, IResourcesManager resourcesManager){
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
		this.resourcesManager = resourcesManager;
		
	}
	
	public Entity createEntity() {
		
		Tower tower = new Tower("Tower"+Math.random());
		tower.setMaxLife(maxLife);
		tower.setLife(life);
		tower.setShootMaxAmmo(maxShoots);
		tower.setShootAtmmo(shoots);
		tower.setStrength(streangth);
		tower.setRotation(rotation);
		tower.setScale(scaling);
		tower.setPosition(position.x, position.y);
		// TODO Debug values
		tower.setSpeed(speed * 50);
		tower.setPassable(false);
		
		tower.addComponent(new ImageRenderComponent("flac.png", resourcesManager));
		tower.addComponent(new TowerAI(Tanks.player1, resourcesManager));

		EEAEvent mainEvent = new TimeEvent(1000, true);
    	mainEvent.addAction(new ChangeShootAmmoAction(1));
    	tower.addComponent(mainEvent);
		
		
		return tower;
	}

}
