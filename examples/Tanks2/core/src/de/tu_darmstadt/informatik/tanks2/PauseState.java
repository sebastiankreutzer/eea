package de.tu_darmstadt.informatik.tanks2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.tu_darmstadt.informatik.eea.EEA;
import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;
import de.tu_darmstadt.informatik.tanks2.factories.MenuEntryFactory;
import de.tu_darmstadt.informatik.tanks2.maps.Map;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;

/**
 * Ein PauseState dient zum Unterbrechen, Abbrechen oder Speichern eines GamePlayStates.
 * @author jr
 *
 */
public class PauseState extends EEAGameState {

	/**
	 * Erzeugt einen neuen PauseState.
	 * 
	 * @param game
	 *            Das EEAGame
	 */
	public PauseState(EEAGame game) {
		super(game);
	}

	@Override
	protected void update(float delta) {

	}

	@Override
	protected void init() {
		// Erzeuge eine Entity und fuege das Hintergrundbild als RenderComponent
		// hinzu
		Entity background = new Entity("background");
		background.addComponent(new ImageRenderComponent("menu.png"));
		em.addEntity(background);
		// Erzeuge eine Entity mit einer TextRenderComponent fuer die
		// Ueberschrift
		Entity pauseMenuText = new Entity("PauseMenuText");
		pauseMenuText.setPosition(70, 410);
		pauseMenuText.addComponent(new TextRenderComponent("Spiel ist pausiert", game.graphics));
		em.addEntity(pauseMenuText);

		// Erzeuge und Initialisiere eine MenuEntryFactory
		MenuEntryFactory mef = new MenuEntryFactory(EEA.getResourceManager(), game.graphics);
		mef.setDimensions(55, 390, 380, 60);

		// Erstelle einen Menuepunkt der zum Spiel zurueckfuehrt
		mef.prepareMenuEntry("Zurück zum pausierten Spiel", "entry.png",
				new ChangeStateAction(game, LaunchTanks.gameState) {
					@Override
					public boolean act(float delta) {
						// Starte den Timer wieder
						GameplayLog.getInstance().timer.start();
						return super.act(delta);
					}
				});
		em.addEntity(mef.makeMenuEntry());
		em.addEntity(mef.makeMenuEntryText());

		// Erstelle einen Menuepunkt zum Speichern des Spiels
		EEAAction saveAction = createSaveCurrentGameplayState();
		mef.prepareMenuEntry("Spielstand speichern", "entry.png", saveAction);
		em.addEntity(mef.makeMenuEntry());
		em.addEntity(mef.makeMenuEntryText());

		// Erstelle einen Menuepunkt der zum Hauptmenue fuehrt
		EEAAction backToMainMenu = createReturnToMainMenuAction();
		mef.prepareMenuEntry("Zurück zum Hauptmenü", "entry.png", backToMainMenu);
		em.addEntity(mef.makeMenuEntry());
		em.addEntity(mef.makeMenuEntryText());
	}

	/**
	 * Gibt eine EEAAction zurueck, die in den {@link LaunchTanks#mainMenu}
	 * GameState wechselt.
	 * 
	 * @return Eine angepasste ChangeStateAction.
	 */
	private EEAAction createReturnToMainMenuAction() {
		return new ChangeStateAction(game, LaunchTanks.mainMenu, true) {
			@Override
			public boolean act(float delta) {
				// Setzte auch den GamePlayState zurueck
				LaunchTanks.gameState.reset();
				return super.act(delta);
			}
		};
	}

	/**
	 * Gibt eine EEAAction zurueck, die in den eine Eingabemaske fuer den Namen
	 * des Spielstands oeffnet und danach die Map speichert.
	 * 
	 * @return Eine EEAAction
	 */
	private EEAAction createSaveCurrentGameplayState() {
		return new EEAAction() {

			@Override
			public boolean act(float delta) {
				// Frage nach einem Namen fuer den Spielstand und speichere
				String name = JOptionPane.showInputDialog(new JFrame(""), "Spielstand speichern unter dem Namen:",
						"Spielstand speichern", 1);
				Map map = Map.getInstance();
				IResourceManager resourcesManager = EEA.getResourceManager();
				map.save(name, LaunchTanks.gameState.getEntities(), resourcesManager);
				return true;
			}
		};
	}

}
