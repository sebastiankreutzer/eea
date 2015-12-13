package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.action.MoveRelativeAction;
import de.tu.darmstadt.informatik.eea.action.RotateAction;
import de.tu.darmstadt.informatik.eea.entity.EEAComponent;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.ANDEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.KeyDownEvent;
import de.tu.darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu.darmstadt.informatik.eea.event.MovementDoesNotCollideEvent;
import de.tu.darmstadt.informatik.eea.event.TimeEvent;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeMineAmmoAction;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeShootAmmoAction;
import de.tu.darmstadt.informatik.tanks2.actions.ScatterShootAction;
import de.tu.darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu.darmstadt.informatik.tanks2.actions.SpawnMineAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tank;
import de.tu.darmstadt.informatik.tanks2.events.HasMinesAmmoLeftEvent;
import de.tu.darmstadt.informatik.tanks2.events.HasShootAmmoLeftEvent;
import de.tu.darmstadt.informatik.tanks2.misc.EasyAI;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu.darmstadt.informatik.tanks2.misc.Options.Difficulty;
import temp.removeASAP.Tanks;

public class TankFactory {
	
	private final String name;
	private final int life;
	private final int maxLife;
	private final int shoots;
	private final int shootsMax;
	private final int mines;
	private final int minesMax;
	private final int strength;
	private final float speed;
	private final int rotation;
	private final float scaling;
	private final Vector2 position;
	private final String difficulty;
	private final boolean debug;
	private IResourcesManager resourcesManager;
	
	
	public TankFactory(String name, int maxLife, int life, 
			int shootsMax, int shoots,int minesMax, int mines, 
			int streangth,float speed, int rotation, float scaling, int x, int y,String difficulty ,boolean debug, IResourcesManager resourcesManager){
		this.name = name;
		this.maxLife = maxLife;
		this.life = life;
		this.shootsMax = shootsMax;
		this.shoots = shoots;
		this.minesMax = minesMax;
		this.mines = mines;
		this.strength = streangth;
		// TODO Debug
		this.speed = speed *10;
		this.rotation = rotation;
		this.scaling = scaling;
		this.position = new Vector2(x,y);
		this.difficulty = difficulty;
		this.debug = debug;
		this.resourcesManager = resourcesManager;
	}
	
	public Entity createEntity(){
		
		Tank tank = new Tank(name);
		tank.setPassable(false);
		
		tank.setSpeed(speed);
		tank.setRotation(rotation);
		tank.setPosition(position.x, position.y);
		tank.setScale(scaling);
		tank.setMaxLife(maxLife);
		tank.setLife(life);
		tank.setShootMaxAmmo(shootsMax);
		tank.setShootAtmmo(shoots);
		tank.setMinesMaxAmmo(minesMax);
		tank.setMinesAmmo(mines);
		tank.setStrength(strength);
		

		if(name.equals(Tanks.player1)){
			tank.addComponent(new ImageRenderComponent("tankPlayer.png", resourcesManager));
			
			// TODO Remove debug values
			RotateAction rightRotateAction = new RotateAction(-speed*2);
			RotateAction leftRotateAction = new RotateAction(speed*2);
			MoveRelativeAction forwardMoveAction = new MoveRelativeAction(speed, 0);
			MoveRelativeAction backwardMoveAction = new MoveRelativeAction(-speed, 0);
			
			
	    	// tank moves forward
	    	EEAEvent mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.UP), new MovementDoesNotCollideEvent(speed, forwardMoveAction));
	    	mainEvents.addAction(forwardMoveAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank moves backward
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.DOWN), new MovementDoesNotCollideEvent(speed, backwardMoveAction));
	    	mainEvents.addAction(backwardMoveAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates left
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.LEFT), new MovementDoesNotCollideEvent(speed, leftRotateAction));
	    	mainEvents.addAction(leftRotateAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates right
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.RIGHT), new MovementDoesNotCollideEvent(speed, rightRotateAction));
	    	mainEvents.addAction(rightRotateAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank shoots
	    	mainEvents = new ANDEvent((new KeyPressedEvent(Input.Keys.K)) , new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ShootAction(resourcesManager));
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank mines
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.Keys.M), new HasMinesAmmoLeftEvent());
	    	mainEvents.addAction(new SpawnMineAction(resourcesManager));
	    	mainEvents.addAction(new ChangeMineAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	//Tank ScatterShoot
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.Keys.L), new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ScatterShootAction(500,resourcesManager));
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	
	    	mainEvents = new TimeEvent(5000, true);
	    	mainEvents.addAction(new ChangeMineAmmoAction(1));
	    	mainEvents.addAction(new ChangeShootAmmoAction(1));
	    	tank.addComponent(mainEvents);
	    	
		} else if(name.equals(Tanks.player2)){
			
			// Mehrspielermodus
			GameplayLog.getInstance().setMultiplayer(true);
			
			tank.addComponent(new ImageRenderComponent("tankPlayer2.png", resourcesManager));
			
			RotateAction rightRotateAction = new RotateAction(-speed);
			RotateAction leftRotateAction = new RotateAction(speed);
			MoveRelativeAction forwardMoveAction = new MoveRelativeAction(speed, 0);
			MoveRelativeAction backwardMoveAction = new MoveRelativeAction(-speed, 0);
			
	    	// tank moves forward
	    	EEAEvent mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.W), new MovementDoesNotCollideEvent(speed * 10, forwardMoveAction));
	    	mainEvents.addAction(forwardMoveAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank moves backward
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.S), new MovementDoesNotCollideEvent(speed * 10, backwardMoveAction));
	    	mainEvents.addAction(backwardMoveAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates left
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.A), new MovementDoesNotCollideEvent(speed * 10, leftRotateAction));
	    	mainEvents.addAction(leftRotateAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates right
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.D), new MovementDoesNotCollideEvent(speed * 10, rightRotateAction));
	    	mainEvents.addAction(rightRotateAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank shoots
	    	mainEvents = new ANDEvent((new KeyPressedEvent(Input.Keys.G)) , new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ShootAction(resourcesManager));
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank mines
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.Keys.F), new HasMinesAmmoLeftEvent());
	    	mainEvents.addAction(new SpawnMineAction(resourcesManager));
	    	mainEvents.addAction(new ChangeMineAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	//Tank ScatterShoot
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.Keys.H), new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ScatterShootAction(500,resourcesManager));
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	
	    	mainEvents = new TimeEvent(5000, true);
	    	mainEvents.addAction(new ChangeMineAmmoAction(1));
	    	mainEvents.addAction(new ChangeShootAmmoAction(1));
	    	tank.addComponent(mainEvents);
		} else {
			tank.addComponent(new ImageRenderComponent("tankOppenent.png", resourcesManager));
			
			if(this.difficulty.equals(Difficulty.NORMAL.toString())){
				// TODO Disable movement for easy difficulty?
			}
			
			EEAComponent component = new EasyAI(Tanks.player1, resourcesManager);
			tank.addComponent(component);
			
			EEAEvent mainEvents = new TimeEvent(10, true);
	    	mainEvents.addAction(new ChangeShootAmmoAction(1));
	    	tank.addComponent(mainEvents);
		}
		
		return tank;
	}

}
