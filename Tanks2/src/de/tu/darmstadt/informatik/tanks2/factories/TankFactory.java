package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.action.MoveAction;
import de.tu.darmstadt.informatik.eea.action.RotateAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.ANDEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.KeyDownEvent;
import de.tu.darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu.darmstadt.informatik.eea.event.MovementDoesNotCollideEvent;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeMineAmmoAction;
import de.tu.darmstadt.informatik.tanks2.actions.ChangeShootAmmoAction;
import de.tu.darmstadt.informatik.tanks2.actions.ScatterShootAction;
import de.tu.darmstadt.informatik.tanks2.actions.ShootAction;
import de.tu.darmstadt.informatik.tanks2.actions.SpawnMineAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tank;
import de.tu.darmstadt.informatik.tanks2.events.AIMoveBackwardEvent;
import de.tu.darmstadt.informatik.tanks2.events.AIMoveForwardEvent;
import de.tu.darmstadt.informatik.tanks2.events.AIRotateLeftEvent;
import de.tu.darmstadt.informatik.tanks2.events.AIRotateRightEvent;
import de.tu.darmstadt.informatik.tanks2.events.AIShootEvent;
import de.tu.darmstadt.informatik.tanks2.events.HasMinesAmmoLeftEvent;
import de.tu.darmstadt.informatik.tanks2.events.HasShootAmmoLeftEvent;
import de.tu.darmstadt.informatik.tanks2.events.TimeEvent;
import de.tu.darmstadt.informatik.tanks2.misc.Options.Difficulty;

public class TankFactory {
	
	private final String name;
	private final int life;
	private final int maxLife;
	private final int shoots;
	private final int shootsMax;
	private final int mines;
	private final int minesMax;
	private final int streangth;
	private final float speed;
	private final int rotation;
	private final float scaling;
	private final Vector2 position;
	private final String difficulty;
	private final boolean debug;
	
	
	public TankFactory(String name, int maxLife, int life, 
			int shootsMax, int shoots,int minesMax, int mines, 
			int streangth,float speed, int rotation, float scaling, int x, int y,String difficulty ,boolean debug){
		this.name = name;
		this.maxLife = maxLife;
		this.life = life;
		this.shootsMax = shootsMax;
		this.shoots = shoots;
		this.minesMax = minesMax;
		this.mines = mines;
		this.streangth = streangth;
		this.speed = speed;
		this.rotation = rotation;
		this.scaling = scaling;
		this.position = new Vector2(x,y);
		this.difficulty = difficulty;
		this.debug = debug;
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
		tank.setStreangth(streangth);
		
		//TODO Remove magic String
		if(name.equals("\"PlayerOne\"")){
			tank.addComponent(new ImageRenderComponent(new Texture("tankPlayer.png")));
			
	    	// tank moves forward
	    	EEAEvent mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.UP), new MovementDoesNotCollideEvent(speed * 10, new MoveAction(speed, 0)));
	    	mainEvents.addAction(new MoveAction(speed, 0));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank moves backward
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.DOWN), new MovementDoesNotCollideEvent(speed * 10, new MoveAction(speed, 0)));
	    	mainEvents.addAction(new MoveAction(-speed, 0));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates left
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.LEFT), new MovementDoesNotCollideEvent(speed * 10, new RotateAction(-speed)));
	    	mainEvents.addAction(new RotateAction(-speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates right
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.RIGHT), new MovementDoesNotCollideEvent(speed * 10, new RotateAction(speed)));
	    	mainEvents.addAction(new RotateAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank shoots
	    	mainEvents = new ANDEvent((new KeyPressedEvent(Input.Keys.K)) , new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ShootAction());
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank mines
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.Keys.M), new HasMinesAmmoLeftEvent());
	    	mainEvents.addAction(new SpawnMineAction());
	    	mainEvents.addAction(new ChangeMineAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	//Tank ScatterShoot
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.Keys.L), new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ScatterShootAction(500));
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	
	    	mainEvents = new TimeEvent(5000, true);
	    	mainEvents.addAction(new ChangeMineAmmoAction(1));
	    	mainEvents.addAction(new ChangeShootAmmoAction(1));
	    	tank.addComponent(mainEvents);
	    	
	    	/*mainEvents = new MultiEvent(new MouseEnteredEvent(), new MouseClickedEvent());
	    	mainEvents.addAction(new ChangeStateAction(SlickBlocksGame.MAINMENUSTATE));//new DestroyEntityAction());
	    	tank.addEvent(mainEvents);*/
	    	
	    	//mainEvents = new leftScreenEvent();
	    	//mainEvents.addAction(new MoveBACKWARD(0.f));
	    	//player.AddEvent(mainEvents);
	    	
	    	//mainEvents = new CollisionEvent();
	    	//mainEvents.addAction(new HitAction(30));
	    	//tank.AddEvent(mainEvents);
	    	// TODO Replace this magic string
		} else if(name.equals("Player2")){
			
			// Mehrspielermodus
			// TODO GameLog
			// GameplayLog.getInstance().setMultiplayer(true);
			
			tank.addComponent(new ImageRenderComponent(new Texture("tankPlayer2.png")));
			
	    	// tank moves forward
	    	EEAEvent mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.W), new MovementDoesNotCollideEvent(speed * 10, new MoveAction(speed,0f)));
	    	mainEvents.addAction(new MoveAction(speed,0f));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank moves backward
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.S), new MovementDoesNotCollideEvent(speed * 10, new MoveAction(-speed,0)));
	    	mainEvents.addAction(new MoveAction(-speed,0));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates left
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.A), new MovementDoesNotCollideEvent(speed * 10, new RotateAction(-speed)));
	    	mainEvents.addAction(new RotateAction(-speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates right
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.Keys.D), new MovementDoesNotCollideEvent(speed * 10, new RotateAction(speed)));
	    	mainEvents.addAction(new RotateAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank shoots
	    	mainEvents = new ANDEvent((new KeyPressedEvent(Input.Keys.G)) , new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ShootAction());
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank mines
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.Keys.F), new HasMinesAmmoLeftEvent());
	    	mainEvents.addAction(new SpawnMineAction());
	    	mainEvents.addAction(new ChangeMineAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	//Tank ScatterShoot
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.Keys.H), new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ScatterShootAction(500));
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	
	    	mainEvents = new TimeEvent(5000, true);
	    	mainEvents.addAction(new ChangeMineAmmoAction(1));
	    	mainEvents.addAction(new ChangeShootAmmoAction(1));
	    	tank.addComponent(mainEvents);
		} else {
			tank.addComponent(new ImageRenderComponent(new Texture("tankOppenent.png")));
			
			//Component component = new EasyAI(0.05f, 850000000f, 50); 
			//tank.addComponent(component);
			EEAEvent mainEvents = new AIRotateLeftEvent();
			mainEvents.addAction(new RotateAction(-speed));
			tank.addComponent(mainEvents);
			
			mainEvents = new AIRotateRightEvent();
			mainEvents.addAction(new RotateAction(speed));
			tank.addComponent(mainEvents);
			
			if(this.difficulty.equals(Difficulty.NORMAL.toString())){
				mainEvents = new AIMoveForwardEvent(speed*10, new MoveAction(speed,0));
				mainEvents.addAction(new MoveAction(speed,0));
				tank.addComponent(mainEvents);
				
				mainEvents = new AIMoveBackwardEvent(speed*10, new MoveAction(-speed,0));
				mainEvents.addAction(new MoveAction(-speed,0));
				tank.addComponent(mainEvents);
				
			}
			
			mainEvents = new ANDEvent(new HasShootAmmoLeftEvent(),new AIShootEvent());
			mainEvents.addAction(new ShootAction());
			mainEvents.addAction(new ChangeShootAmmoAction(-1));
			tank.addComponent(mainEvents);
			
			mainEvents = new TimeEvent(1000, true);
	    	mainEvents.addAction(new ChangeShootAmmoAction(1));
	    	tank.addComponent(mainEvents);
		}
		
		return tank;
	}

}