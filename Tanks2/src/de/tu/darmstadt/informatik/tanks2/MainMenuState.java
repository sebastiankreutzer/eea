package de.tu.darmstadt.informatik.tanks2;

import com.badlogic.gdx.graphics.Texture;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu.darmstadt.informatik.eea.action.MusicAction;
import de.tu.darmstadt.informatik.eea.action.QuitAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.LoopEvent;
import de.tu.darmstadt.informatik.eea.states.EEAGameState;
import de.tu.darmstadt.informatik.tanks2.factories.MenuEntryFactory;
import de.tu.darmstadt.informatik.tanks2.misc.Options;

public class MainMenuState extends EEAGameState {

	public MainMenuState(EEAGame game) {
		super(game);
	}

	@Override
	protected void update(float delta) {

	}

	@Override
	protected void init() {
		Entity background = new Entity("background");	// Entitaet fuer Hintergrunde
		background.addComponent(new ImageRenderComponent(new Texture("menu.png"))); // Bildkomponente
		
		if(Options.getInstance().isSoundEnabled()) {
			EEAEvent soundEvent = new LoopEvent();
			MusicAction backgroundSound = new MusicAction("theme.ogg", 1);
			soundEvent.addAction(backgroundSound);
			background.addComponent(soundEvent);
		}

		// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
		em.addEntity(background);
		
		Entity mainMenuText = new Entity("MainManuText");
		mainMenuText.setPosition(70, 410);
		mainMenuText.addComponent(new TextRenderComponent("Hauptmenü", game.graphics));
		em.addEntity(mainMenuText);
		
		MenuEntryFactory mef = new MenuEntryFactory(em, game.graphics);
		mef.setDimensions(55, 390, 380, 60);
		
	    //Action new_game = new ChangeStateInitAction(Tanks.GAMEPLAYSTATE);
		mef.prepareMenuEntry("Neues Spiel", new Texture("entry.png"), new ChangeStateAction(game, LaunchTanks.gameState));
		mef.makeMenuEntry();
		mef.makeMenuEntryText();
		mef.prepareMenuEntry("Spielstand laden", new Texture("entry.png"), new QuitAction());
		mef.makeMenuEntry();
		mef.makeMenuEntryText();
		mef.prepareMenuEntry("Highscore", new Texture("entry.png"), new QuitAction());
		mef.makeMenuEntry();
		mef.makeMenuEntryText();
		mef.prepareMenuEntry("Einstellungen", new Texture("entry.png"), new ChangeStateAction(game, LaunchTanks.options));
		mef.makeMenuEntry();
		mef.makeMenuEntryText();
		mef.prepareMenuEntry("Beenden", new Texture("entry.png"), new QuitAction());
		mef.makeMenuEntry();
		mef.makeMenuEntryText();
	}

}
