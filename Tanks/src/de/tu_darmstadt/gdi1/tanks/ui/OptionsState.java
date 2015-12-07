package de.tu_darmstadt.gdi1.tanks.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.factory.MenuEntryFactory;
import de.tu_darmstadt.gdi1.tanks.model.options.Options;
import de.tu_darmstadt.gdi1.tanks.model.options.Options.Difficulty;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyPressedEvent;

public class OptionsState extends BasicGameState {

    private int stateID;
    private StateBasedEntityManager entityManager;
    private Options options;
    
    private final int distance = 50;
    private final int start_position = 263;
    
    OptionsState( int stateID ) {
       this.stateID = stateID;
       this.entityManager = StateBasedEntityManager.getInstance();
       this.options = Options.getInstance();
    }

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		if(!Tanks.debug) setBackground();
		
    	onEscapePressed();
    	
    	// Lege eine MenuEntryFactory an
    	MenuEntryFactory m;
    	int counter = 0;
    	
    	// Ton einschalten/ausschalten
    	Action toggle_sound = new Action() {
    		@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
    			// Toggle sound
				if(options.isSoundEnabled()) {
					options.disableSound();
				}
				else {
					options.enableSound();
				}			
			}    		
    	};
    	
		m = new MenuEntryFactory("Ton", container, toggle_sound, start_position+distance*counter);
		entityManager.addEntity(this.stateID, m.createEntity());
		
		if(!Tanks.debug) {
		Entity sound_pic = new Entity("Sound_picture");
		if(options.isSoundEnabled())
			sound_pic.addComponent(new ImageRenderComponent(new Image("/assets/sound_enabled.png")));
		else
			sound_pic.addComponent(new ImageRenderComponent(new Image("/assets/sound_disabled.png")));
		sound_pic.setPosition(new Vector2f(75, start_position+distance*counter));
		entityManager.addEntity(this.stateID, sound_pic);
		
		counter++;
		}
		
		// Schwierigkeit einstellen
		
		Action difficulty = new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
				if(options.getDifficulty().equals("EASY")) {
					options.setDifficulty(Difficulty.NORMAL);
				} else if(options.getDifficulty().equals("NORMAL")) {
					options.setDifficulty(Difficulty.HARD);
				} else {
					options.setDifficulty(Difficulty.EASY);
				}
			}			
		};
		m = new MenuEntryFactory("Schwierigkeit", container, difficulty, start_position+distance*counter);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;
		
	}
	
	private void setBackground() throws SlickException {
		// Hintergrund laden
		Entity background = new Entity("background");	// Entitaet fuer Hintergrund
		background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
		background.addComponent(new ImageRenderComponent(new Image("/assets/menu.png"))); // Bildkomponente

		// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
		StateBasedEntityManager.getInstance().addEntity(stateID, background);
	}
	
	private void onEscapePressed() {
		// Erzeuge folgendes Event "Escape-Taste gedrueckt"
		Event ESC_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);

		// Erzeuge die Action "Gehe ins Hauptmenue" und fuege sie dem 
		// ESC_pressed Event hinzu
		ESC_pressed.addAction(new ChangeStateAction(Tanks.MAINMENUSTATE));

		// Erstelle eine Hintergrund-Entitaet, die auf das Druecken der 
		// "Escape-Taste" lauscht
		Entity dummy = new Entity("dummy");
		dummy.addComponent(ESC_pressed);
		
		// Fuege die dummy-Entity dem StateBasedEntityManager hinzu
		entityManager.addEntity(stateID, dummy);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		entityManager.renderEntities(container,game, g);
		
		//g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 12), true));
		
		g.drawString("Einstellungen", 70, 210);
		
		if(Options.getInstance().isSoundEnabled()) {
			entityManager.getEntity(this.stateID, "Sound_picture").removeComponent("SoundPic");
			if(!Tanks.debug) entityManager.getEntity(this.stateID, "Sound_picture").addComponent(new ImageRenderComponent(new Image("/assets/sound_enabled.png")));
			g.drawString("Ton ausschalten", 120, entityManager.getEntity(this.stateID, "Sound_picture").getPosition().y-10);
		} else {
			entityManager.getEntity(this.stateID, "Sound_picture").removeComponent("SoundPic");
			if(!Tanks.debug) entityManager.getEntity(this.stateID, "Sound_picture").addComponent(new ImageRenderComponent(new Image("/assets/sound_disabled.png")));
			g.drawString("Ton einschalten", 120, entityManager.getEntity(this.stateID, "Sound_picture").getPosition().y-10);
		}
		
		g.drawString(options.getDifficulty(), 120, entityManager.getEntity(this.stateID, "Schwierigkeit").getPosition().y-10);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return stateID;
	}


}
