package de.tu_darmstadt.informatik.tanks2;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.EEAGameState;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.component.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.component.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.input.KeyPressedEvent;
import de.tu_darmstadt.informatik.tanks2.factories.MenuEntryFactory;
import de.tu_darmstadt.informatik.tanks2.misc.Options;

public class OptionsState extends EEAGameState {
	
	private Options options;
	private TextRenderComponent difficultyText, soundText;

	public OptionsState(EEAGame game, Options options) {
		super(game);
		this.options = options;
		difficultyText = new TextRenderComponent("");
		soundText = new TextRenderComponent("");
	}

	@Override
	protected void update(float delta) {
		difficultyText.setText("Schwierigkeit : " + options.getDifficulty());
		if(options.isSoundEnabled()) soundText.setText("Ton einschalten");
		else soundText.setText("Ton ausschalten");			
	}

	@Override
	protected void init() {
		Entity background = new Entity("background");	// Entitaet fuer Hintergrunde
		background.addComponent(new ImageRenderComponent("menu.png")); // Bildkomponente
		
		// Erzeuge folgendes Event "Escape-Taste gedrueckt"
		EEAEvent ESC_pressed = new KeyPressedEvent(Input.Keys.ESCAPE);
		// Erzeuge die Action "Gehe ins Hauptmenue" und fuege sie dem 
		// ESC_pressed Event hinzu
		ESC_pressed.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu, false));
		background.addComponent(ESC_pressed);
		
		// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
		em.addEntity(background);
		
		Entity mainMenuText = new Entity("OptionsText");
		mainMenuText.setPosition(70, 410);
		mainMenuText.addComponent(new TextRenderComponent("Einstellungen"));
		em.addEntity(mainMenuText);
		
		MenuEntryFactory mef = new MenuEntryFactory();
		mef.setDimensions(55, 330, 380, 60);
		
    	// Ton einschalten/ausschalten
    	EEAAction toggleSound = new EEAAction() {
    		@Override
			public boolean act(float delta) {
    			// Toggle sound
				if(options.isSoundEnabled()) options.disableSound();
				else  options.enableSound();
				return true;
			}    		
    	};
		mef.prepareMenuEntry("Ton", "entry.png", toggleSound);
		em.addEntity(mef.makeMenuEntry());
		Entity soundTextEntity = mef.makeMenuEntryText();
		soundTextEntity.addComponent(soundText);
		em.addEntity(soundTextEntity);
		
		EEAAction difficulty = new EEAAction() {
			@Override
			public boolean act(float delta) {
				if(options.getDifficulty().equals("EASY")) {
					options.setDifficulty(Options.Difficulty.NORMAL);
				} else if(options.getDifficulty().equals("NORMAL")) {
					options.setDifficulty(Options.Difficulty.HARD);
				} else {
					options.setDifficulty(Options.Difficulty.EASY);
				}
				return true;
			}
		};
		mef.prepareMenuEntry("Schwierigkeit","entry.png", difficulty);
		em.addEntity(mef.makeMenuEntry());
		Entity textEntity = mef.makeMenuEntryText();
		textEntity.addComponent(difficultyText);
		em.addEntity(textEntity);
	}

}
