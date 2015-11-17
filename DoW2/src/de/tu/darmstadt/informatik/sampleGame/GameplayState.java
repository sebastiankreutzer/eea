package de.tu.darmstadt.informatik.sampleGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu.darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu.darmstadt.informatik.eea.action.MoveAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.event.EntityOutOfScreenEvent;
import de.tu.darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu.darmstadt.informatik.eea.event.LoopEvent;
import de.tu.darmstadt.informatik.eea.event.MouseClickedEvent;
import de.tu.darmstadt.informatik.eea.states.BasicGameState;
import de.tu.darmstadt.informatik.sampleGame.LaunchGame;

public class GameplayState extends BasicGameState {

	public GameplayState(EEAGame game) {
		super(game);
		//init();
	}
	
	public void show(){
		init();
	}
	
	public void init() {
    	
    	// Hintergrund laden
    	final Entity background = new Entity("background");
    	background.addComponent(new ImageRenderComponent(new Texture("background.png"))); // Bildkomponente
    	    	
    	// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
    	em.addEntity(background);
    	
    	// Bei Drücken der ESC-Taste zurueck ins Hauptmenue wechseln
    	Entity esc_Listener = new Entity("ESC_Listener");
    	KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.Keys.ESCAPE);
    	esc_pressed.addAction(new ChangeStateAction(game, LaunchGame.MainMenuState));
    	esc_Listener.addComponent(esc_pressed);    	
    	em.addEntity(esc_Listener);
    	
    	// Bei Mausklick soll Wassertropfen erscheinen
    	Entity mouse_Clicked_Listener = new Entity("Mouse_Clicked_Listener");
    	MouseClickedEvent mouse_Clicked = new MouseClickedEvent();
    	
    	mouse_Clicked.addAction(new Action() {
			@Override
			public boolean act(float delta) {
				// Wassertropfen wird erzeugt
				Entity drop = new Entity("drop of water");
				
				Vector2 v = new Vector2(Gdx.input.getX(), Gdx.input.getY());
				background.getStage().screenToStageCoordinates(v);
				background.stageToLocalCoordinates(v);
				drop.setPosition(v.x, v.y);
				
				drop.addComponent(new ImageRenderComponent(new Texture("drop.png")));
				
				// Wassertropfen faellt nach unten
				LoopEvent loop = new LoopEvent();
		    	loop.addAction(new MoveAction(0f, -35f));
		    	drop.addComponent(loop);
		    	
		    	// Wenn der Bildschirm verlassen wird, dann ...
		    	EntityOutOfScreenEvent lse = new EntityOutOfScreenEvent();
		    	
		    	// ... zerstoere den Wassertropfen
		    	lse.addAction(new DestroyEntityAction());
		    	// ... und wechsle ins Hauptmenue
		    	lse.addAction(new ChangeStateAction(game, LaunchGame.MainMenuState));
		    	
		    	drop.addComponent(lse);
		    	em.addEntity(drop);
		    	
		    	return true;
			}    		
    	});
    	mouse_Clicked_Listener.addComponent(mouse_Clicked);
    	
    	em.addEntity(mouse_Clicked_Listener);    	
    	
    }

	@Override
	protected void update(float delta) {
		// TODO Auto-generated method stub

	}

}
