package de.tu_darmstadt.informatik.dow2;

import com.badlogic.gdx.Input;

import de.tu_darmstadt.informatik.customActions.CreateDropAction;
import de.tu_darmstadt.informatik.customActions.MoveBucketAction;
import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.EEAGameState;
import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.MusicAction;
import de.tu_darmstadt.informatik.eea.component.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.component.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.component.collision.RectangleCollisionComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.LoopEvent;
import de.tu_darmstadt.informatik.eea.event.input.KeyPressedEvent;
import de.tu_darmstadt.informatik.eea.event.input.MouseClickedEvent;

public class GameplayState extends EEAGameState {

	public TextRenderComponent scoreRenderComponent;
	private int score;
	
	
	public GameplayState(EEAGame game) {
		super(game);
		IResourceManager rm = EEAGame.getResourceManager();
		rm.loadTextureAsync("drop.png");
		rm.loadSoundAsync("WaterDrop.mp3");
	}
	
	@Override
	public void init() {
    	final Entity backgroundMusicEntity = new Entity("backgroundMusic");
    	final MusicAction musicAction = new MusicAction("BabblingBrook.mp3");
    	backgroundMusicEntity.addAction(musicAction);
    	em.addEntity(backgroundMusicEntity);
		
    	// Hintergrund laden
    	final Entity backgroundEntity = new Entity("background");
    	backgroundEntity.addComponent(new ImageRenderComponent("background.png")); // Bildkomponente
    	
    	// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
    	em.addEntity(backgroundEntity);
    	
    	createEscapeAction();
    	Entity bucket = createBucket(backgroundEntity);
    	createDropByClick(backgroundEntity, bucket);
    	createScore();
	}

	/**
	 * Für die Anzeige der aktuell gefangenen Drops
	 */
	private void createScore() {
		Entity score = new Entity("Score");
    	score.setPosition(10, 20);
    	scoreRenderComponent = new TextRenderComponent("0 Drops catched");
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
		bucket.addComponent(new ImageRenderComponent("bucket.png"));
		
		// damit Kollisionen beachtet werden
		bucket.addComponent(new RectangleCollisionComponent());
		
		// Mausbewegungen verursachen eine Verschiebung
		LoopEvent loopInputEvent = new LoopEvent();
		loopInputEvent.addAction(new MoveBucketAction());
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
    	Entity mouseClickedListener = new Entity("Mouse_Clicked_Listener");
    	
    	// Wenn man mit der Maus klickt, dann sollen neue Tropfen erzeugt werden
    	MouseClickedEvent mouseClicked = new MouseClickedEvent();
     	mouseClicked.addAction(new CreateDropAction(em, game, bucket, this, mouseClicked));
    	mouseClickedListener.addComponent(mouseClicked);
    	em.addEntity(mouseClickedListener);
	}

	/**
	 *  Bei Drücken der ESC-Taste zurueck ins Hauptmenue wechseln
	 */
	private void createEscapeAction() {		
    	Entity escListener = new Entity("ESC_Listener");
    	KeyPressedEvent escPressed = new KeyPressedEvent(Input.Keys.ESCAPE);
    	escPressed.addAction(new ChangeStateAction(game, DropOfWaterGame.MainMenuState, false));
    	escListener.addComponent(escPressed);    	
    	em.addEntity(escListener);
	}

	@Override
	protected void update(float delta) {
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public void reset() {
		super.reset();
		score = 0;
	}

}
