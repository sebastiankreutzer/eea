package de.tu_darmstadt.informatik.tanks2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Align;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.EEARenderComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.BorderCollisionComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.BorderCollisionComponent.Border;
import de.tu_darmstadt.informatik.eea.event.ANDEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu_darmstadt.informatik.eea.event.OREvent;
import de.tu_darmstadt.informatik.eea.event.TimeEvent;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;
import de.tu_darmstadt.informatik.tanks2.actions.SpawnPickupAction;
import de.tu_darmstadt.informatik.tanks2.entities.Tank;
import de.tu_darmstadt.informatik.tanks2.events.EntityDestroyedEvent;
import de.tu_darmstadt.informatik.tanks2.events.RandomEvent;
import de.tu_darmstadt.informatik.tanks2.exceptions.SemanticException;
import de.tu_darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu_darmstadt.informatik.tanks2.highscore.Highscore;
import de.tu_darmstadt.informatik.tanks2.highscore.HighscoreList;
import de.tu_darmstadt.informatik.tanks2.maps.Map;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;
import de.tu_darmstadt.informatik.tanks2.misc.Options;
import temp.removeASAP.Tanks;

/**
 * Im GameplayState findet das eigentliche Spielgeschehen statt.
 * 
 * @author jr
 *
 */
public class GameplayState extends EEAGameState {

	private Map map;
	private Options options;

	private TextRenderComponent player1Text, ammo1Text, mine1Text, life1Text;
	private TextRenderComponent player2Text, ammo2Text, mine2Text, life2Text;
	private TextRenderComponent fpsText;

	private IResourcesManager resourcesManager;

	/**
	 * Erzeugt einen neuen GameplayState fuer das EEAGame
	 * 
	 * @param game
	 *            Das EEAGame
	 * @param options
	 *            Die Options
	 */
	public GameplayState(EEAGame game, Options options) {
		super(game);
		IResourcesManager _resourcesManager = game.getResourcesManager();
		this.resourcesManager = _resourcesManager;
		map = Map.getInstance();
		this.options = options;
	}

	/**
	 * Initialisiert die TextRenderComponents und erzeugt Entities um diese
	 * darzustellen.
	 */
	private void createUI() {
		player1Text = new TextRenderComponent("Spieler 1", game.graphics);
		createLabel("Player1Text", 10, 20, player1Text);

		ammo1Text = new TextRenderComponent("", game.graphics);
		createLabel("Player1Ammo", 10, 40, ammo1Text);

		mine1Text = new TextRenderComponent("", game.graphics);
		createLabel("Player1Mines", 10, 60, mine1Text);

		life1Text = new TextRenderComponent("", game.graphics);
		createLabel("Player1Life", 10, 80, life1Text);

		player2Text = new TextRenderComponent("", game.graphics);
		createLabel("Player2Text", 510, 20, player2Text);

		ammo2Text = new TextRenderComponent("", game.graphics);
		createLabel("Player2Ammo", 510, 40, ammo2Text);

		mine2Text = new TextRenderComponent("", game.graphics);
		createLabel("Player2Mines", 510, 60, mine2Text);

		life2Text = new TextRenderComponent("", game.graphics);
		createLabel("Player2Life", 510, 80, life2Text);

		fpsText = new TextRenderComponent("", game.graphics);
		createLabel("FPSLabel", 320, 20, fpsText);
	}

	/**
	 * Erstelle eine Entity and der definierten Position mit einer
	 * TextRenderComponent und fuegt sie dem EntityManager hinzu.
	 * 
	 * @param name
	 *            Der Name der Entity
	 * @param x
	 *            Die x Position
	 * @param y
	 *            Die y Position
	 * @param textRenderComponent
	 *            Die TextRenderComponent
	 */
	private void createLabel(String name, int x, int y, TextRenderComponent textRenderComponent) {
		Entity fpsLabel = new Entity(name);
		fpsLabel.setPosition(x, y);
		fpsLabel.addComponent(textRenderComponent);
		em.addEntity(fpsLabel);
	}

	@Override
	protected void update(float delta) {
		// Stelle die Informationen fuer Spieler1 dar
		Tank tank = ((Tank) em.getEntity(Tanks.player1));
		if (tank != null) {
			ammo1Text.setText("Übrige Schüsse: " + tank.getAmmunition() + "/" + tank.getMaxAmmunition());
			mine1Text.setText("Übrige Minen: " + tank.getActualMinesAmmo() + "/" + tank.getMaxMinesAmmo());
			life1Text.setText("Leben: " + tank.getLife() + "/" + tank.getMaxLife());
		}
		// Stelle im Mehrspielermodus die Informationen fuer Spieler2 dar, zeige
		// ansonsten die Zeit an
		if (GameplayLog.getInstance().isMultiplayer()) {
			player1Text.setText("Spieler 1");
			Tank tank2 = ((Tank) em.getEntity(Tanks.player2));
			if (tank2 != null) {
				player2Text.setText("Spieler 2");
				ammo2Text.setText("Übrige Schüsse: " + tank2.getAmmunition() + "/" + tank2.getMaxAmmunition());
				mine2Text.setText("Übrige Minen: " + tank2.getActualMinesAmmo() + "/" + tank2.getMaxMinesAmmo());
				life2Text.setText("Leben: " + tank2.getLife() + "/" + tank2.getMaxLife());
			}
		} else {
			player1Text.setText("Vergangene Zeit: " + GameplayLog.getInstance().timer.get() / 1000 + " s");
		}

		fpsText.setText("FPS" + game.getFramerate());
	}

	@Override
	protected void init() {
		em.setDebug(true);
		// Parse die Map. Bei Fehlern wird eine entsprechende Fehlermeldung
		// erzeugt und ins Hauptmenue gewechselt.
		try {
			map.parse(resourcesManager, options, Tanks.debug);
			// Alle Entities der Map dem EntityManager uebergeben
			Iterator<Entity> it = map.getEntities().iterator();
			while (it.hasNext()) {
				em.addEntity(it.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JFrame frame = new JFrame("");
			String message, title;
			if (e instanceof SyntaxException) {
				message = "Die Map konnte nicht geparst werden";
				title = "Syntax Fehler";
			} else if (e instanceof SemanticException) {
				message = "Die Map konnte nicht geladen werden. Sie ist semantisch inkorrekt.";
				title = "Semantischer Fehler";
			} else {
				message = e.getLocalizedMessage();
				title = e.getClass().getSimpleName();
			}
			JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
			reset();
			game.setScreen(LaunchTanks.mainMenu);
			return;
		}

		// Wird die Escape-Taste gedrueckt, wechsel in den PauseState
		Entity escapeListener = new Entity("EscapeListener");
		EEAEvent escapePressedEvent = new KeyPressedEvent(Input.Keys.ESCAPE);
		escapePressedEvent.addAction(new ChangeStateAction(game, LaunchTanks.pauseState) {
			@Override
			public boolean act(float delta) {
				// ... und stoppe den Timer
				GameplayLog.getInstance().timer.stop();
				return super.act(delta);
			}
		});
		escapeListener.addComponent(escapePressedEvent);
		em.addEntity(escapeListener);

		// Hinzufuegen der Spiellogik Entity
		em.addEntity(createGameLogic());
		
		Entity leftBorder = new Entity("LeftBorder");
		leftBorder.addComponent(new BorderCollisionComponent(Border.LEFT));
		leftBorder.setPosition(0, 0);
		em.addEntity(leftBorder);
		
		Entity rightBorder = new Entity("RightBorder");
		rightBorder.addComponent(new BorderCollisionComponent(Border.RIGHT));
		rightBorder.setPosition(game.getViewport().getCamera().viewportWidth, 0);
		em.addEntity(rightBorder);
		
		Entity bottomBorder = new Entity("BottomBorder");
		bottomBorder.addComponent(new BorderCollisionComponent(Border.BOTTOM));
		bottomBorder.setPosition(0, 0);
		em.addEntity(bottomBorder);
		
		Entity topBorder = new Entity("TopBorder");
		topBorder.addComponent(new BorderCollisionComponent(Border.TOP));
		topBorder.setPosition(0, game.getViewport().getWorldHeight());
		em.addEntity(topBorder);
		
		createUI();
	}

	/**
	 * Erzeugt eine Entity mit welche die Sieg- und Niederlagebedingungen
	 * ueberpruefen und mit Actions die das Ergebnis darstellen und den Wechsel
	 * in den naechsten GameState einleiten.
	 * 
	 * @return Eine Entity mit Events und Actions.
	 */
	private Entity createGameLogic() {
		// Erzeuge eine Entity, welche die Events und Actions der Spiellogik
		// enthaelt
		Entity entity = new Entity("GameLogicEntity");

		// Erzeuge ein Event welches ausloest wenn alle Tanks ausser Spieler1
		// zerstoert sind
		List<EEAEvent> enemyDestroyedEvents = new ArrayList<EEAEvent>();
		for (Entity e : em.getAllEntities()) {
			if (e instanceof Tank && !e.getID().equals(Tanks.player1)) {
				enemyDestroyedEvents.add(new EntityDestroyedEvent(e));
			}
		}
		EEAEvent allEnemiesDestroyed = new ANDEvent(enemyDestroyedEvents.toArray(new EEAEvent[0]));
		// Zeige an das Spieler1 gewonnen hat und bestimme den naechsten
		// GameState
		allEnemiesDestroyed.addAction(createDisplayResultAction(true));
		allEnemiesDestroyed.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu, true) {
			@Override
			public boolean act(float delta) {
				GameplayLog gameplayLog = GameplayLog.getInstance();
				String nextMap = gameplayLog.getNextMap().substring(1, gameplayLog.getNextMap().length() - 1);
				if (nextMap.equals(Tanks.finish)) {
					map.resetToDefault();
				} else {
					map.loadMap(nextMap);
					newState = LaunchTanks.gameState;
				}
				return super.act(delta);
			}
		});
		entity.addComponent(allEnemiesDestroyed);

		// Erzeuge ein Event das ausloest wenn Spieler1 zerstoert wurde oder
		// falls das Zeitlimit, sofern vorhanden, abgelaufen ist.
		long timeLimit = GameplayLog.getInstance().getTimeLimit();
		Entity player1 = em.getEntity(Tanks.player1);
		EEAEvent defeatEvent = new EntityDestroyedEvent(player1);
		if (timeLimit > 0) {
			EEAEvent event = new TimeEvent(timeLimit - GameplayLog.getInstance().timer.get(), false);
			defeatEvent = new OREvent(event, new EntityDestroyedEvent(player1));
		}
		// Zeige an das Spieler1 verloren hat und gehe danach ins Hauptmenue
		// zurueck
		defeatEvent.addAction(createDisplayResultAction(false));
		defeatEvent.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu, true));
		entity.addComponent(defeatEvent);

		// Hin und wieder werden zufaellig PickUp Objekte erzeugt
		EEAEvent event = new RandomEvent(1, 4);
		event.addAction(new SpawnPickupAction(resourcesManager));
		entity.addComponent(event);

		return entity;
	}

	/**
	 * Erzeugt eine spezielle Action, welche den Timer stoppt, ein JFrame
	 * oeffnet und das Spielergebnis darstellt und gegebenenfalls die Erzeugung
	 * eines neuen Highscoreeintrags bewirkt.
	 * 
	 * @param playerOneWon
	 *            Ob Spieler1 gewonnen hat
	 * @return Eine EEAAction die ein JFrame erzeugt
	 */
	private EEAAction createDisplayResultAction(final boolean playerOneWon) {
		return new EEAAction() {
			// Waehle die Texte in Abhaengigkeit des Spielergebnisses
			String winner = playerOneWon ? "Spieler 1" : "Spieler 2";
			String result = playerOneWon ? "gewonnen" : "verloren";
			String misc = playerOneWon ? "Sehr gut." : "Schade.";
			String highscore = playerOneWon ? "Gewonnen und" : "Verloren, aber";

			@Override
			public boolean act(float delta) {
				GameplayLog gameplayLog = GameplayLog.getInstance();
				gameplayLog.timer.stop();
				int shots = gameplayLog.getNumberOfShots();
				long time = gameplayLog.timer.get();

				JFrame frame = new JFrame("");

				// Im Mehrspielermodus soll der Name des Gewinners angezeigt
				// werden
				if (gameplayLog.isMultiplayer()) {
					JOptionPane.showMessageDialog(frame, winner + " hat gewonnen!", winner + " gewinnt!", 1);
				} else {
					// ansonsten muss ueberprueft werden ob ein Highscore
					// vorliegt, falls ja muss der Name abgefragt werden
					HighscoreList highscoreList = HighscoreList.load(map.getName(), resourcesManager);
					if (highscoreList.isNewHighscore(new Highscore("", shots, time)) && !Tanks.debug) {
						String name = JOptionPane.showInputDialog(frame, "Bitte nennen Sie Ihren Namen:",
								highscore + " neuer Highscore", 1);
						highscoreList.addHighscore(new Highscore(name, shots, time));
					} else {
						JOptionPane.showMessageDialog(frame, "Sie haben " + result + ".", misc, 1);
					}
				}
				return true;
			}
		};
	}

	/**
	 * Gibt eine Liste aller Entities zurueck, die von dem EntityManager dieses
	 * GameplayStates verwaltet werden.
	 * 
	 * @return Eine Liste von Entities
	 */
	public List<Entity> getEntities() {
		return em.getAllEntities();
	}
}
