package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.tanks.model.GameplayLog;
import de.tu_darmstadt.gdi1.tanks.model.action.ChangeMineAmmoAction;
import de.tu_darmstadt.gdi1.tanks.model.action.ChangeShootAmmoAction;
import de.tu_darmstadt.gdi1.tanks.model.action.ScatterShootAction;
import de.tu_darmstadt.gdi1.tanks.model.action.ShootAction;
import de.tu_darmstadt.gdi1.tanks.model.action.SpawnMineAction;
import de.tu_darmstadt.gdi1.tanks.model.entities.Tank;
import de.tu_darmstadt.gdi1.tanks.model.event.AIMoveBackwardEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.AIMoveForwardEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.AIRotateLeftEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.AIRotateRightEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.AIShootEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.HasMinesAmmoLeftEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.HasShootAmmoLeftEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.TimeEvent;
import de.tu_darmstadt.gdi1.tanks.model.options.Options.Difficulty;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.action.basicactions.MoveBackwardAction;
import eea.engine.action.basicactions.MoveForwardAction;
import eea.engine.action.basicactions.RotateLeftAction;
import eea.engine.action.basicactions.RotateRightAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.MovementDoesNotCollideEvent;
import eea.engine.interfaces.IEntityFactory;

public class TankFactory implements IEntityFactory{
	
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
	private final Vector2f position;
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
		this.position = new Vector2f(x,y);
		this.difficulty = difficulty;
		this.debug = debug;
	}
	
	public Entity createEntity(){
		
		Tank tank = new Tank(name);
		tank.setPassable(false);
		
		tank.setSpeed(speed);
		tank.setRotation(rotation);
		tank.setPosition(position);
		tank.setScale(scaling);
		tank.setMaxLife(maxLife);
		tank.setLife(life);
		tank.setShootMaxAmmo(shootsMax);
		tank.setShootAtmmo(shoots);
		tank.setMinesMaxAmmo(minesMax);
		tank.setMinesAmmo(mines);
		tank.setStreangth(streangth);
		
		if(name.equals(Tanks.player1)){
			try {
				if(!debug)tank.addComponent(new ImageRenderComponent(new Image("assets/tankPlayer.png")));
				else tank.setSize(new Vector2f(10,10));
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
	    	// tank moves forward
	    	Event mainEvents = new ANDEvent(new KeyDownEvent(Input.KEY_UP), new MovementDoesNotCollideEvent(speed * 10, new MoveForwardAction(speed)));
	    	mainEvents.addAction(new MoveForwardAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank moves backward
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.KEY_DOWN), new MovementDoesNotCollideEvent(speed * 10, new MoveBackwardAction(speed)));
	    	mainEvents.addAction(new MoveBackwardAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates left
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.KEY_LEFT), new MovementDoesNotCollideEvent(speed * 10, new RotateLeftAction(speed)));
	    	mainEvents.addAction(new RotateLeftAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates right
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.KEY_RIGHT), new MovementDoesNotCollideEvent(speed * 10, new RotateRightAction(speed)));
	    	mainEvents.addAction(new RotateRightAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank shoots
	    	mainEvents = new ANDEvent((new KeyPressedEvent(Input.KEY_K)) , new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ShootAction());
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank mines
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.KEY_M), new HasMinesAmmoLeftEvent());
	    	mainEvents.addAction(new SpawnMineAction());
	    	mainEvents.addAction(new ChangeMineAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	//Tank ScatterShoot
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.KEY_L), new HasShootAmmoLeftEvent());
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
		} else if(name.equals(Tanks.player2)){
			
			// Mehrspielermodus
			GameplayLog.getInstance().setMultiplayer(true);
			
			try {
				if(!debug)tank.addComponent(new ImageRenderComponent(new Image("assets/tankPlayer2.png")));
				else tank.setSize(new Vector2f(10,10));
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
	    	// tank moves forward
	    	Event mainEvents = new ANDEvent(new KeyDownEvent(Input.KEY_W), new MovementDoesNotCollideEvent(speed * 10, new MoveForwardAction(speed)));
	    	mainEvents.addAction(new MoveForwardAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank moves backward
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.KEY_S), new MovementDoesNotCollideEvent(speed * 10, new MoveBackwardAction(speed)));
	    	mainEvents.addAction(new MoveBackwardAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates left
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.KEY_A), new MovementDoesNotCollideEvent(speed * 10, new RotateLeftAction(speed)));
	    	mainEvents.addAction(new RotateLeftAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank rotates right
	    	mainEvents = new ANDEvent(new KeyDownEvent(Input.KEY_D), new MovementDoesNotCollideEvent(speed * 10, new RotateRightAction(speed)));
	    	mainEvents.addAction(new RotateRightAction(speed));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank shoots
	    	mainEvents = new ANDEvent((new KeyPressedEvent(Input.KEY_G)) , new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ShootAction());
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	// tank mines
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.KEY_F), new HasMinesAmmoLeftEvent());
	    	mainEvents.addAction(new SpawnMineAction());
	    	mainEvents.addAction(new ChangeMineAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	//Tank ScatterShoot
	    	mainEvents = new ANDEvent(new KeyPressedEvent(Input.KEY_H), new HasShootAmmoLeftEvent());
	    	mainEvents.addAction(new ScatterShootAction(500));
	    	mainEvents.addAction(new ChangeShootAmmoAction(-1));
	    	tank.addComponent(mainEvents);
	    	
	    	
	    	mainEvents = new TimeEvent(5000, true);
	    	mainEvents.addAction(new ChangeMineAmmoAction(1));
	    	mainEvents.addAction(new ChangeShootAmmoAction(1));
	    	tank.addComponent(mainEvents);
		} else {
			try {
				if(!debug) tank.addComponent(new ImageRenderComponent(new Image("assets/tankOppenent.png")));
				else tank.setSize(new Vector2f(10,10));
			} catch (SlickException e) {
				e.printStackTrace();
			}
			//Component component = new EasyAI(0.05f, 850000000f, 50); 
			//tank.addComponent(component);
			Event mainEvents = new AIRotateLeftEvent();
			mainEvents.addAction(new RotateLeftAction(speed));
			tank.addComponent(mainEvents);
			
			mainEvents = new AIRotateRightEvent();
			mainEvents.addAction(new RotateRightAction(speed));
			tank.addComponent(mainEvents);
			
			if(this.difficulty.equals(Difficulty.NORMAL.toString())){
				mainEvents = new AIMoveForwardEvent(speed*10, new MoveForwardAction(speed));
				mainEvents.addAction(new MoveForwardAction(speed));
				tank.addComponent(mainEvents);
				
				mainEvents = new AIMoveBackwardEvent(speed*10, new MoveBackwardAction(speed));
				mainEvents.addAction(new MoveBackwardAction(speed));
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
