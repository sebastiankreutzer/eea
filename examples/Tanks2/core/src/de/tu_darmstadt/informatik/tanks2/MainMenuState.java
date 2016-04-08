package de.tu_darmstadt.informatik.tanks2;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.MusicAction;
import de.tu_darmstadt.informatik.eea.action.QuitAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.LoopEvent;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;
import de.tu_darmstadt.informatik.tanks2.factories.MenuEntryFactory;
import de.tu_darmstadt.informatik.tanks2.maps.Map;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu_darmstadt.informatik.tanks2.misc.Options;

/**
 * Der Hauptmenue Spielzustand dient zur Auswahl der angebotenen Funktionen wie
 * neues Spiel starten und Highscores einsehen.
 * 
 * @author jr
 *
 */
public class MainMenuState extends EEAGameState {

	public MainMenuState(EEAGame game) {
		super(game);
	}

	@Override
	protected void update(float delta) {

	}

	@Override
	protected void init() {
		Entity background = new Entity("background");
		background.addComponent(new ImageRenderComponent("menu.png"));

		// Spiele Hintergrundmusik falls der Ton in den Optionen
		// eingeschaltet ist.
		if (Options.getInstance().isSoundEnabled()) {
			EEAEvent soundEvent = new LoopEvent();
			MusicAction backgroundSound = new MusicAction("theme.ogg", 1);
			soundEvent.addAction(backgroundSound);
			background.addComponent(soundEvent);
		}

		em.addEntity(background);

		Entity mainMenuText = new Entity("MainManuText");
		mainMenuText.setPosition(70, 410);
		mainMenuText.addComponent(new TextRenderComponent("Hauptmenü"));
		em.addEntity(mainMenuText);

		// Erzeuge mit einer Factory die unterschiedlichen Menueeintraege.
		MenuEntryFactory mef = new MenuEntryFactory();
		mef.setDimensions(55, 390, 380, 60);

		mef.prepareMenuEntry("Neues Spiel", "entry.png", new ChangeStateAction(game, LaunchTanks.gameState, false) {
			@Override
			public boolean act(float delta) {
				super.act(delta);
				GameplayLog.getInstance().timer.reset();
				GameplayLog.getInstance().timer.start();
				return true;
			}
		});
		em.addEntity(mef.makeMenuEntry());
		em.addEntity(mef.makeMenuEntryText());

		mef.prepareMenuEntry("Spielstand laden", "entry.png", new ChangeStateAction(game, LaunchTanks.gameState, false) {

			@Override
			public boolean act(float delta) {
				JFileChooser fc = new JFileChooser("saves/");
				fc.setFileFilter(new FileFilter() {
					@Override
					public boolean accept(File f) {
						return f.isDirectory() || f.getName().toLowerCase().endsWith(".tanks");
					}

					@Override
					public String getDescription() {
						return "Tanks-Spielstaende";
					}
				});
				int state = fc.showOpenDialog(null);

				if (state == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					Map.getInstance().loadSavegame(file.getPath());
					super.act(delta);
					// TODO Verify init
					// sb.enterState(Tanks.GAMEPLAYSTATE);
					// try {
					// sb.init(gc);
					// } catch (SlickException e) {
					// e.printStackTrace();
					// }
				}

				return true;
			}
		});
		em.addEntity(mef.makeMenuEntry());
		em.addEntity(mef.makeMenuEntryText());

		mef.prepareMenuEntry("Highscore", "entry.png", new ChangeStateAction(game, LaunchTanks.highScoreState, false));
		em.addEntity(mef.makeMenuEntry());
		em.addEntity(mef.makeMenuEntryText());

		mef.prepareMenuEntry("Einstellungen", "entry.png", new ChangeStateAction(game, LaunchTanks.optionsState, false));
		em.addEntity(mef.makeMenuEntry());
		em.addEntity(mef.makeMenuEntryText());

		mef.prepareMenuEntry("Beenden", "entry.png", new QuitAction());
		em.addEntity(mef.makeMenuEntry());
		em.addEntity(mef.makeMenuEntryText());
	}

}
