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
import de.tu.darmstadt.informatik.tanks2.AI.TankAI;
import de.tu.darmstadt.informatik.tanks2.AI.TowerAI;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeMineAmmoAction;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeShootAmmoAction;
import de.tu.darmstadt.informatik.tanks2.actions.ScatterShootAction;
import de.tu.darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu.darmstadt.informatik.tanks2.actions.SpawnMineAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tank;
import de.tu.darmstadt.informatik.tanks2.events.HasMinesAmmoLeftEvent;
import de.tu.darmstadt.informatik.tanks2.events.HasShootAmmoLeftEvent;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu.darmstadt.informatik.tanks2.misc.Options.Difficulty;
import temp.removeASAP.Tanks;

public class TankFactory {
	private final String difficulty;
	private final boolean debug;
	private IResourcesManager resourcesManager;
	
	
	public TankFactory(String difficulty ,boolean debug, IResourcesManager resourcesManager){

		this.difficulty = difficulty;
		this.debug = debug;
		this.resourcesManager = resourcesManager;
	}
	
	public Entity createEntity(float x, float y, String name, int maxLife, int life, 
			int shootsMax, int shoots,int minesMax, int mines, 
			int strength,float speed, float rotation, float scale){
		
		Tank tank = new Tank(name, x, y, rotation, scale);
		tank.setPassable(false);
		
		tank.setSpeed(speed);
		tank.setMaxLife(maxLife);
		tank.setLife(life);
		tank.setShootMaxAmmo(shootsMax);
		tank.setShootAtmmo(shoots);
		tank.setMinesMaxAmmo(minesMax);
		tank.setMinesAmmo(mines);
		tank.setStrength(strength);
		

		if(name.equals(Tanks.player1)){
			tank.addComponent(new ImageRenderComponent("tankPlayer.png", resourcesManager));
			
			RotateAction rightRotateAction = new RotateAction(-speed);
			RotateAction leftRotateAction = new RotateAction(speed);
			MoveRelativeAction forwardMoveAction = new MoveRelativeAction(speed, 0);
			MoveRelativeAction backwardMoveAction = new MoveRelativeAction(-speed, 0);
			
			
	    	// tank moves forward
	    	EEAEvent mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.UP), new MovementDoesNotCollideEvent(forwardMoveAction));
	    	mainEvents.addAction(forwardMoveAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank moves backward
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.DOWN), new MovementDoesNotCollideEvent(backwardMoveAction));
	    	mainEvents.addAction(backwardMoveAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates left
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.LEFT), new MovementDoesNotCollideEvent(leftRotateAction));
	    	mainEvents.addAction(leftRotateAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates right
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.RIGHT), new MovementDoesNotCollideEvent(rightRotateAction));
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
	    	mainEvents.addAction(new ScatterShootAction(1.5f,resourcesManager));
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
	    	EEAEvent mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.W), new MovementDoesNotCollideEvent(forwardMoveAction));
	    	mainEvents.addAction(forwardMoveAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank moves backward
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.S), new MovementDoesNotCollideEvent(backwardMoveAction));
	    	mainEvents.addAction(backwardMoveAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates left
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.A), new MovementDoesNotCollideEvent(leftRotateAction));
	    	mainEvents.addAction(leftRotateAction);
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates right
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.D), new MovementDoesNotCollideEvent(rightRotateAction));
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
			
			EEAComponent componentAI;
			if(this.difficulty.equals(Difficulty.EASY.toString())) componentAI = new TowerAI(Tanks.player1, resourcesManager);
			else componentAI = new TankAI(Tanks.player1, resourcesManager);
			tank.addComponent(componentAI);
			
			EEAEvent mainEvents = new TimeEvent(10, true);
	    	mainEvents.addAction(new ChangeShootAmmoAction(1));
	    	tank.addComponent(mainEvents);
		}
		return tank;
	}

}
