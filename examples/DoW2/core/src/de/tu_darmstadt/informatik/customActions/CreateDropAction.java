package de.tu_darmstadt.informatik.customActions;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;

import de.tu_darmstadt.informatik.dow2.DropOfWaterGame;
import de.tu_darmstadt.informatik.dow2.GameplayState;
import de.tu_darmstadt.informatik.eea.EEA;
import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.action.MoveAction;
import de.tu_darmstadt.informatik.eea.action.SoundAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.CircleCollisionComponent;
import de.tu_darmstadt.informatik.eea.event.CollisionEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.EntityOutOfScreenEvent;
import de.tu_darmstadt.informatik.eea.event.IMouseStatus;
import de.tu_darmstadt.informatik.eea.event.LoopEvent;
import de.tu_darmstadt.informatik.eea.states.EntityManager;

public class CreateDropAction extends EEAAction {
	private final EntityManager em;
	private final EEAGame game;
	private final Entity bucket;
	private final GameplayState gameplayState;
	private final IMouseStatus mouseMovedEvent;
	
	public CreateDropAction(EntityManager em, EEAGame game, Entity bucket, GameplayState gameplayState, IMouseStatus mouseMovedEvent) {
		this.em = em;
		this.game = game;
		this.bucket = bucket;
		this.gameplayState = gameplayState;
		this.mouseMovedEvent = mouseMovedEvent;
	}
	
	@Override
	public boolean act(float delta) {
		// Wassertropfen wird erzeugt
		Entity drop = new Entity("drop of water");
		
		dropPainting(drop);
		positionDrop(drop);
		dropMovement(drop);
    	dropDisplayCollision(drop);
    	dropBucketCollision(drop);
    	
    	em.addEntity(drop);
    	
    	return true;
	}

	/**
	 * der Drop soll mit dem Bucket kollidieren
	 * @param drop
	 */
	private void dropBucketCollision(Entity drop) {
		// füge dem Drop eine Kollisionskomponente hinzu.
		// Ohne Kollisionskomponente würde keine Kollision getriggert werden.
		drop.addComponent(new CircleCollisionComponent());
		
		EEAEvent collisionEvent = new CollisionEvent();
		collisionEvent.addAction(new DropBucketCollisionAction(bucket, gameplayState));
		drop.addComponent(collisionEvent);
		
		SoundAction createDropSound = new SoundAction("WaterDrop.mp3");
		drop.addAction(createDropSound);
	}

	/**
	 * Sorgt dafür, dass der Drop gemalt wird
	 * @param drop
	 */
	private void dropPainting(Entity drop) {
		drop.addComponent(new ImageRenderComponent("drop.png"));
	}

	/**
	 * Wenn ein Drop erzeugt wird, dann muss er positioniert werden.
	 * @param drop
	 */
	private void positionDrop(Entity drop) {
		Random random = new Random();
		float x = random.nextInt((int) EEA.getGraphics().getWorldWidth());
		float y = EEA.getGraphics().getWorldHeight();
		// Alternative:
		// x = mouseMovedEvent.getMouseX();
		drop.setPosition(x, y, Align.top | Align.center);
	}

	/**
	 * Wenn der Drop den Bildschirm verlässt, gehe in das Hauptmenü
	 * @param drop
	 */
	private void dropDisplayCollision(Entity drop) {
		// Wenn der Bildschirm verlassen wird, dann ...
    	EntityOutOfScreenEvent lse = new EntityOutOfScreenEvent();
    	// spiele einen Sound ab
    	SoundAction failSoundAction = new SoundAction("bubbles.mp3");
    	lse.addAction(failSoundAction);
    	
    	// ... wechsle ins Hauptmenü
    	lse.addAction(new ChangeStateAction(game, DropOfWaterGame.MainMenuState, true));
    	
    	// ... und zerstöre den Wassertropfen
    	lse.addAction(new DestroyEntityAction());
    	
    	drop.addComponent(lse);
	}

	/**
	 * Der Tropfen soll nach unten fallen
	 * @param drop
	 */
	private void dropMovement(Entity drop) {
		// Wassertropfen faellt nach unten
		LoopEvent loop = new LoopEvent();
    	loop.addAction(new MoveAction(0f, -1000f));
    	drop.addComponent(loop);
	}  
}
