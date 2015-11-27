package de.tu.darmstadt.informatik.tanks2;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu.darmstadt.informatik.eea.states.EEAGameState;
import de.tu.darmstadt.informatik.tanks2.factories.MenuEntryFactory;
import de.tu.darmstadt.informatik.tanks2.misc.Options;

public class OptionsState extends EEAGameState {
	
	// TODO move this to the Game class?
	private Options options;
	private TextRenderComponent difficultyText, soundText;

	public OptionsState(EEAGame game) {
		super(game);
		this.options = Options.getInstance();
		difficultyText = new TextRenderComponent("", game.graphics);
		soundText = new TextRenderComponent("", game.graphics);
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
		background.addComponent(new ImageRenderComponent(new Texture("menu.png"))); // Bildkomponente
		
		// Erzeuge folgendes Event "Escape-Taste gedrueckt"
		EEAEvent ESC_pressed = new KeyPressedEvent(Input.Keys.ESCAPE);
		// Erzeuge die Action "Gehe ins Hauptmenue" und fuege sie dem 
		// ESC_pressed Event hinzu
		ESC_pressed.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu));
		background.addComponent(ESC_pressed);
		
		// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
		em.addEntity(background);
		
		Entity mainMenuText = new Entity("OptionsText");
		mainMenuText.setPosition(70, 410);
		mainMenuText.addComponent(new TextRenderComponent("Einstellungen", game.graphics));
		em.addEntity(mainMenuText);
		
		MenuEntryFactory mef = new MenuEntryFactory(em, game.graphics);
		mef.setDimensions(55, 330, 380, 60);
		
    	// Ton einschalten/ausschalten
    	Action toggleSound = new Action() {
    		@Override
			public boolean act(float delta) {
    			// Toggle sound
				if(options.isSoundEnabled()) options.disableSound();
				else  options.enableSound();
				return true;
			}    		
    	};
		mef.prepareMenuEntry("Ton", new Texture("entry.png"), toggleSound);
		mef.makeMenuEntry();
		mef.makeMenuEntryText().addComponent(soundText);
		
		Action difficulty = new Action() {
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
		mef.prepareMenuEntry("Schwierigkeit", new Texture("entry.png"), difficulty);
		mef.makeMenuEntry();
		mef.makeMenuEntryText().addComponent(difficultyText);
	}

}