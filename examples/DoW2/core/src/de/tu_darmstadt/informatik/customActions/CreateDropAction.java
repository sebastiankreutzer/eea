package de.tu_darmstadt.informatik.customActions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.tu_darmstadt.informatik.dow2.LaunchGame;
import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.action.MoveAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.event.CollisionEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.EntityOutOfScreenEvent;
import de.tu_darmstadt.informatik.eea.event.LoopEvent;
import de.tu_darmstadt.informatik.eea.states.EntityManager;

public class CreateDropAction extends EEAAction {
	private Actor background;
	private IResourcesManager resourcesManager;
	private EntityManager em;
	private EEAGame game;
	private Entity bucket;

	public CreateDropAction(Actor background, IResourcesManager resourcesManager, EntityManager em, EEAGame game, Entity bucket) {
		this.background = background;
		this.resourcesManager = resourcesManager;
		this.em = em;
		this.game = game;
		this.bucket = bucket;
	}
	
	@Override
	public boolean act(float delta) {
		// Wassertropfen wird erzeugt
		Entity drop = new Entity("drop of water");
		
		positionDrop(drop);
		dropPainting(drop);
		dropMovement(drop);
    	dropDisplayCollision(drop);
    	dropBucketCollision(drop);
    	
    	em.addEntity(drop);
    	
    	return true;
	}

	private void dropBucketCollision(Entity drop) {
		// TODO Auto-generated method stub
		EEAEvent collisionEvent = new CollisionEvent();
		
		collisionEvent.addAction(new Action() {
			
			@Override
			public boolean act(float delta) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		drop.addComponent(collisionEvent);		
		// collisionEvent.addAction(new DropBucketCollisionAction(bucket));
	}

	private void dropPainting(Entity drop) {
		drop.addComponent(new ImageRenderComponent("drop.png", resourcesManager));
	}

	/**
	 * Wenn ein Drop erzeugt wird, dann muss er positioniert werden.
	 * @param drop
	 */
	private void positionDrop(Entity drop) {
		Vector2 v = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		background.getStage().screenToStageCoordinates(v);
		background.stageToLocalCoordinates(v);
		drop.setPosition(v.x, v.y);
	}

	/**
	 * Wenn der Drop den Bildschirm verlässt, gehe in das Hauptmenü
	 * @param drop
	 */
	private void dropDisplayCollision(Entity drop) {
		// Wenn der Bildschirm verlassen wird, dann ...
    	EntityOutOfScreenEvent lse = new EntityOutOfScreenEvent();
    	// ... und wechsle ins Hauptmenue
    	lse.addAction(new ChangeStateAction(game, LaunchGame.MainMenuState));
    	// ... zerstoere den Wassertropfen
    	lse.addAction(new DestroyEntityAction());
    	drop.addComponent(lse);
	}

	/**
	 * der Tropfen soll nach unten fallen
	 * @param drop
	 */
	private void dropMovement(Entity drop) {
		// Wassertropfen faellt nach unten
		LoopEvent loop = new LoopEvent();
    	loop.addAction(new MoveAction(0f, -35f));
    	drop.addComponent(loop);
	}  
}
