package de.tu_darmstadt.informatik.tanks2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.Input;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.EEARenderComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.event.ANDEvent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.KeyPressedEvent;
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

public class GameplayState extends EEAGameState {

	private Map map;
	private Options options;

	private TextRenderComponent player1text, ammo1Text, mine1Text, life1Text;
	private TextRenderComponent player2text, ammo2Text, mine2Text, life2Text;
	private TextRenderComponent fpsText;

	private IResourcesManager resourcesManager;

	public GameplayState(EEAGame game, Options options) {
		super(game);
		IResourcesManager _resourcesManager = game.getResourcesManager();
		this.resourcesManager = _resourcesManager;
		map = Map.getInstance();
		this.options = options;
	}

	private void createUI() {
		player1text = new TextRenderComponent("Spieler 1", game.graphics);
		createLabel("Player1Text", 10, 20, player1text);

		ammo1Text = new TextRenderComponent("", game.graphics);
		createLabel("Player1Ammo", 10, 40, ammo1Text);

		mine1Text = new TextRenderComponent("", game.graphics);
		createLabel("Player1Mines", 10, 60, mine1Text);

		life1Text = new TextRenderComponent("", game.graphics);
		createLabel("Player1Life", 10, 80, mine1Text);

		player2text = new TextRenderComponent("", game.graphics);
		createLabel("Player2Text", 510, 20, player2text);

		ammo2Text = new TextRenderComponent("", game.graphics);
		createLabel("Player2Ammo", 510, 40, ammo2Text);

		mine2Text = new TextRenderComponent("", game.graphics);
		createLabel("Player2Mines", 510, 60, mine2Text);

		life2Text = new TextRenderComponent("", game.graphics);
		createLabel("Player2Life", 510, 80, life2Text);

		fpsText = new TextRenderComponent("", game.graphics);
		createLabel("FPSLabel", 320, 20, fpsText);
	}

	private void createLabel(String name, int x, int y, TextRenderComponent textRenderComponent) {
		Entity fpsLabel = new Entity(name);
		fpsLabel.setPosition(x, y);
		fpsLabel.addComponent(textRenderComponent);
		em.addEntity(fpsLabel);
	}

	@Override
	protected void update(float delta) {
		Tank tank = ((Tank) em.getEntity(Tanks.player1));
		if (tank != null) {
			ammo1Text.setText("Übrige Schüsse: " + tank.getActualShootAmmo() + "/" + tank.getMaxShootAmmo());
			mine1Text.setText("Übrige Minen: " + tank.getActualMinesAmmo() + "/" + tank.getMaxMinesAmmo());
			life1Text.setText("Leben: " + tank.getActualLife() + "/" + tank.getMaxLife());
		}
		if (GameplayLog.getInstance().isMultiplayer()) {
			player1text.setText("Spieler 1");
			Tank tank2 = ((Tank) em.getEntity(Tanks.player2));
			if (tank2 != null) {
				player2text.setText("Spieler 2");
				ammo2Text.setText("Übrige Schüsse: " + tank2.getActualShootAmmo() + "/" + tank2.getMaxShootAmmo());
				mine2Text.setText("Übrige Minen: " + tank2.getActualMinesAmmo() + "/" + tank2.getMaxMinesAmmo());
				life2Text.setText("Leben: " + tank2.getActualLife() + "/" + tank2.getMaxLife());
			}
		} else {
			player1text.setText("Vergangene Zeit: " + GameplayLog.getInstance().timer.get() / 1000 + " s");
		}

		fpsText.setText("FPS" + game.getFramerate());
	}

	@Override
	protected void init() {
		em.setDebug(true);
		// Map parsen
		try {
			map.parse(map.getSource(), resourcesManager, true, options);
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
			game.setScreen(LaunchTanks.mainMenu);
			return;
		}

		// Alle Entities der Map dem EntityManager uebergeben
		Iterator<Entity> it = map.getEntities().iterator();
		while (it.hasNext()) {
			em.addEntity(it.next());
		}

		Entity entity = new Entity("Dummy");

		// Wird der Panzer des Spielers zerstoert verliert Spieler 1
		Entity player1 = em.getEntity(Tanks.player1);
		EEAEvent playerDestroyedEvent = new EntityDestroyedEvent(player1);
		playerDestroyedEvent.addAction(displayResult(false));
		// und wechsle ins Hauptmenue
		playerDestroyedEvent.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu, true));
		entity.addComponent(playerDestroyedEvent);

		List<EEAEvent> enemyDestroyedEvents = new ArrayList<EEAEvent>();
		for (Entity e : em.getAllEntities()) {
			if (e instanceof Tank && !e.getID().equals(Tanks.player1)) {
				enemyDestroyedEvents.add(new EntityDestroyedEvent(e));
			}
		}

		// Wenn alle gegnerischen Tanks zerstoert sind gewinnt Spieler 1
		EEAEvent allEnemiesDestroyed = new ANDEvent(enemyDestroyedEvents.toArray(new EEAEvent[0]));
		allEnemiesDestroyed.addAction(displayResult(true));
		// Wechsle in das naechste Level
		allEnemiesDestroyed.addAction(createWinAction());
		entity.addComponent(allEnemiesDestroyed);

		// Wird die Escape-Taste gedrueckt, ...
		EEAEvent escapePressedEvent = new KeyPressedEvent(Input.Keys.ESCAPE);
		// ... dann wechsle ins OnPauseMenu ...
		escapePressedEvent.addAction(new ChangeStateAction(game, LaunchTanks.pauseState) {
			@Override
			public boolean act(float delta) {
				super.act(delta);
				// ... und stoppe den Timer
				GameplayLog.getInstance().timer.stop();
				return true;
			}
		});
		entity.addComponent(escapePressedEvent);

		long timeLimit = GameplayLog.getInstance().getTimeLimit();
		if (timeLimit > 0) {
			EEAEvent event = new TimeEvent(timeLimit - GameplayLog.getInstance().timer.get(), false);
			event.addAction(new EEAAction() {

				@Override
				public boolean act(float delta) {
					JFrame frame = new JFrame("");
					Highscore h = new Highscore("", GameplayLog.getInstance().getNumberOfShots(),
							GameplayLog.getInstance().timer.get());

					// Wenn kein Multiplayer-Spiel und kein Debug, dann zeige
					// Dialog
					if (!GameplayLog.getInstance().isMultiplayer() && !Tanks.debug) {
						// Wenn ein neuer Highscore aufgestellt wurde, dann Name
						// abfragen, sonst Schade
						if (!HighscoreList.getInstance().isNewHighscore(h)) {
							JOptionPane.showMessageDialog(frame, "Sie haben verloren.", "Schade.", 1);
						} else {
							String name = JOptionPane.showInputDialog(frame, "Bitte nennen Sie Ihren Namen:",
									"Verloren, aber neuer Highscore", 1);
							h = new Highscore(name, GameplayLog.getInstance().getNumberOfShots(),
									GameplayLog.getInstance().timer.get());
							HighscoreList.getInstance().addHighscore(h);
						}
					}
					return true;
				}

			});

			event.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu));
			entity.addComponent(event);
		}
		EEAEvent event = new RandomEvent(1, 4);
		event.addAction(new SpawnPickupAction(resourcesManager));
		entity.addComponent(event);

		// Hinzufuegen der dummy-Entity
		em.addEntity(entity);

		createUI();
	}

	private EEAAction displayResult(final boolean playerOneWon) {
		return new EEAAction() {
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

				if (gameplayLog.isMultiplayer()) {
					JOptionPane.showMessageDialog(frame, winner + " hat gewonnen!", winner + " gewinnt!", 1);
				} else {
					HighscoreList highscoreList = HighscoreList.getInstance();
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

	private EEAAction createWinAction() {
		return new EEAAction() {

			@Override
			public boolean act(float delta) {
				GameplayLog gameplayLog = GameplayLog.getInstance();
				em.reset();

				String nextMap = gameplayLog.getNextMap().substring(1, gameplayLog.getNextMap().length() - 1);
				if (nextMap.equals(Tanks.finish)) {
					map.resetToDefault();
					game.setScreen(LaunchTanks.mainMenu);

					// TODO Find out what this method does and whether we need
					// it.
					// gc.reinit();
				} else {
					map.load(nextMap);
					// TODO Find out what this method does and whether we need
					// it.
					// sb.init(gc);
				}

				return true;
			}

		};
	}

}
