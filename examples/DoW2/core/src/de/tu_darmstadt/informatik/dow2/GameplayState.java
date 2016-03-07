package de.tu_darmstadt.informatik.dow2;

import com.badlogic.gdx.Input;

import de.tu_darmstadt.informatik.customActions.CreateDropAction;
import de.tu_darmstadt.informatik.customActions.MoveBucketAction;
import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.RectangleCollisionComponent;
import de.tu_darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu_darmstadt.informatik.eea.event.LoopInputEvent;
import de.tu_darmstadt.informatik.eea.event.MouseClickedEvent;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;

public class GameplayState extends EEAGameState {

	private IResourcesManager resourcesManager;
	public TextRenderComponent scoreRenderComponent;
	private int Score;
	
	
	public GameplayState(EEAGame game, IResourcesManager resourcesManager) {
		super(game);
		this.resourcesManager = resourcesManager;
	}
	
	@Override
	public void init() {
    	
    	// Hintergrund laden
    	final Entity backgroundEntity = new Entity("background");
    	backgroundEntity.addComponent(new ImageRenderComponent("background.png", resourcesManager)); // Bildkomponente
    	
    	// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
    	em.addEntity(backgroundEntity);
    	
    	gotoMenuIfEsc();
    	Entity bucket = createBucket(backgroundEntity);
    	createDropByClick(backgroundEntity, bucket);
    	createScore();
	}

	/**
	 * Für die Anzeige der aktuell gefangenen Drops
	 */
	private void createScore() {
		Entity score = new Entity("Score");
    	score.setPosition(100, 100);
    	scoreRenderComponent = new TextRenderComponent("0 Drops catched", game.graphics);
    	score.addComponent(scoreRenderComponent);
    	em.addEntity(score);
	}

	/**
	 * Erzeugt den Bucket mit der dazugehörigen Logik
	 * @param backgroundEntity
	 * @return
	 */
	private Entity createBucket(final Entity backgroundEntity) {
		// Ein allgemeines Entity erstellen
		Entity bucket = new Entity("Bucket Entity");
		
		// Bild des Buckets setzen
		bucket.addComponent(new ImageRenderComponent("bucket.png", resourcesManager));
		
		// damit Kollisionen beachtet werden
		bucket.addComponent(new RectangleCollisionComponent());
		
		// Mausbewegungen verursachen eine Verschiebung
		LoopInputEvent loopInputEvent = new LoopInputEvent();
		loopInputEvent.addAction(new MoveBucketAction(loopInputEvent));
		bucket.addComponent(loopInputEvent);
		em.addEntity(bucket);
		
		return bucket;
	}

	/**
	 * Bei Mausklick soll Wassertropfen erscheinen
	 * @param backgroundEntity
	 * @param bucket 
	 */
	private void createDropByClick(final Entity backgroundEntity, Entity bucket) {
    	Entity mouse_Clicked_Listener = new Entity("Mouse_Clicked_Listener");
    	
    	// wenn man mit der Maus klickt, dann sollen neue Tropfen erzeugt werden
    	MouseClickedEvent mouse_Clicked = new MouseClickedEvent();
     	mouse_Clicked.addAction(new CreateDropAction(resourcesManager, em, game, bucket, this, mouse_Clicked));
    	mouse_Clicked_Listener.addComponent(mouse_Clicked);    	
    	em.addEntity(mouse_Clicked_Listener);
	}

	/**
	 *  Bei Drücken der ESC-Taste zurueck ins Hauptmenue wechseln
	 */
	private void gotoMenuIfEsc() {		
    	Entity esc_Listener = new Entity("ESC_Listener");
    	KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.Keys.ESCAPE);
    	esc_pressed.addAction(new ChangeStateAction(game, GameBootstrapper.MainMenuState));
    	esc_Listener.addComponent(esc_pressed);    	
    	em.addEntity(esc_Listener);
	}

	@Override
	protected void update(float delta) {
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

}