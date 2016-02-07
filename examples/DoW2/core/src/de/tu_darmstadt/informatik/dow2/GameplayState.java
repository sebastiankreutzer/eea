package de.tu_darmstadt.informatik.dow2;

import com.badlogic.gdx.Input;

import de.tu_darmstadt.informatik.customActions.CreateDropAction;
import de.tu_darmstadt.informatik.customActions.MoveBucketAction;
import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu_darmstadt.informatik.eea.event.MouseClickedEvent;
import de.tu_darmstadt.informatik.eea.event.MouseMovedEvent;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;

public class GameplayState extends EEAGameState {

	private IResourcesManager resourcesManager;

	public GameplayState(EEAGame game, IResourcesManager resourcesManager) {
		super(game);
		this.resourcesManager = resourcesManager;
	}
	
	public void show(){
		super.show();
		
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
    	createDrop(backgroundEntity, bucket);
	}

	private Entity createBucket(final Entity backgroundEntity) {
		Entity bucket = new Entity("Bucket Entity");
		
		// Bild des Buckets
		bucket.addComponent(new ImageRenderComponent("bucket.png", resourcesManager));
		
		// Mausbewegungen verursachen eine Verschiebung
		MouseMovedEvent mouseMovedEvent = new MouseMovedEvent();
		mouseMovedEvent.addAction(new MoveBucketAction(backgroundEntity));
		bucket.addComponent(mouseMovedEvent);
		//bucket.setScale(0.5f);
		em.addEntity(bucket);
		
		return bucket;
	}

	/**
	 * Bei Mausklick soll Wassertropfen erscheinen
	 * @param backgroundEntity
	 * @param bucket 
	 */
	private void createDrop(final Entity backgroundEntity, Entity bucket) {
    	Entity mouse_Clicked_Listener = new Entity("Mouse_Clicked_Listener");
    	
    	// wenn man mit der Maus klickt, dann sollen neue Tropfen erzeugt werden
    	MouseClickedEvent mouse_Clicked = new MouseClickedEvent();
     	mouse_Clicked.addAction(new CreateDropAction(backgroundEntity, resourcesManager, em, game, bucket));
    	mouse_Clicked_Listener.addComponent(mouse_Clicked);
    	
    	
    	em.addEntity(mouse_Clicked_Listener);
	}

	/**
	 *  Bei Drücken der ESC-Taste zurueck ins Hauptmenue wechseln
	 */
	private void gotoMenuIfEsc() {		
    	Entity esc_Listener = new Entity("ESC_Listener");
    	KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.Keys.ESCAPE);
    	esc_pressed.addAction(new ChangeStateAction(game, LaunchGame.MainMenuState));
    	esc_Listener.addComponent(esc_pressed);    	
    	em.addEntity(esc_Listener);
	}

	@Override
	protected void update(float delta) {

	}

}
