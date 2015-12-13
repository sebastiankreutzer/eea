package de.tu.darmstadt.informatik.tanks2;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.Input;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu.darmstadt.informatik.eea.event.ANDEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu.darmstadt.informatik.eea.event.TimeEvent;
import de.tu.darmstadt.informatik.eea.states.EEAGameState;
import de.tu.darmstadt.informatik.tanks2.actions.SpawnPickupAction;
import de.tu.darmstadt.informatik.tanks2.entities.Tank;
import de.tu.darmstadt.informatik.tanks2.events.EntityDestroyedEvent;
import de.tu.darmstadt.informatik.tanks2.events.RandomEvent;
import de.tu.darmstadt.informatik.tanks2.exceptions.SemanticException;
import de.tu.darmstadt.informatik.tanks2.exceptions.SyntaxException;
import de.tu.darmstadt.informatik.tanks2.highscore.Highscore;
import de.tu.darmstadt.informatik.tanks2.highscore.HighscoreList;
import de.tu.darmstadt.informatik.tanks2.maps.Map;
import de.tu.darmstadt.informatik.tanks2.misc.GameplayLog;
import temp.removeASAP.Tanks;

public class GameplayState extends EEAGameState {

	private Map map;

	private TextRenderComponent player1text, ammo1Text, mine1Text, life1Text;
	private TextRenderComponent player2text, ammo2Text, mine2Text, life2Text;

	private IResourcesManager resourcesManager;

	public GameplayState(EEAGame game) {
		super(game);
		IResourcesManager _resourcesManager = game.getResourcesManager();
		this.resourcesManager = _resourcesManager;
		map = Map.getInstance();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		Tank tank = ((Tank)em.getEntity(Tanks.player1));
		if(tank != null) {
			ammo1Text.setText("Übrige Schüsse: "+tank.getActualShootAmmo()+"/"+tank.getMaxShootAmmo());
			mine1Text.setText("Übrige Minen: "+tank.getActualMinesAmmo()+"/"+tank.getMaxMinesAmmo());
			life1Text.setText("Leben: "+tank.getActualLife()+"/"+tank.getMaxLife());
		}
		if (GameplayLog.getInstance().isMultiplayer()) {
			Tank tank2 = ((Tank) em.getEntity(Tanks.player2));
			if (tank2 != null) {
				player2text.setText("Spieler 2");
				ammo2Text.setText("Übrige Schüsse: "+tank2.getActualShootAmmo()+"/"+tank2.getMaxShootAmmo());
				mine2Text.setText("Übrige Minen: "+tank2.getActualMinesAmmo()+"/"+tank2.getMaxMinesAmmo());
				life2Text.setText("Leben: "+tank2.getActualLife()+"/"+tank2.getMaxLife());
			}
		} else {
			player1text.setText("Vergangene Zeit: "+GameplayLog.getInstance().timer.get()/1000+" s");
		}
	}
	
	private void createUI(){
		Entity player1Label = new Entity("Player1Text");
		player1Label.setPosition(10, 20);
		player1text = new TextRenderComponent("Spieler 1", game.graphics);
		player1Label.addComponent(player1text);
		em.addEntity(player1Label);
		
		Entity ammo1Label = new Entity("Player1Ammo");
		ammo1Label.setPosition(10, 40);
		ammo1Text = new TextRenderComponent("", game.graphics);
		ammo1Label.addComponent(ammo1Text);
		em.addEntity(ammo1Label);
		
		Entity mines1Label = new Entity("Player1Mines");
		mines1Label.setPosition(10, 60);
		mine1Text = new TextRenderComponent("", game.graphics);
		mines1Label.addComponent(mine1Text);
		em.addEntity(mines1Label);
		
		Entity life1Label = new Entity("Player1Life");
		life1Label.setPosition(10, 80);
		life1Text = new TextRenderComponent("", game.graphics);
		life1Label.addComponent(life1Text);
		em.addEntity(life1Label);
		
		Entity player2Label = new Entity("");
		player2Label.setPosition(510, 20);
		player2text = new TextRenderComponent("", game.graphics);
		player2Label.addComponent(player2text);
		em.addEntity(player2Label);
		
		Entity ammo2Label = new Entity("Player2Ammo");
		ammo2Label.setPosition(510, 40);
		ammo2Text = new TextRenderComponent("", game.graphics);
		ammo2Label.addComponent(ammo2Text);
		em.addEntity(ammo2Label);
		
		Entity mines2Label = new Entity("Player2Mines");
		mines2Label.setPosition(510, 60);
		mine2Text = new TextRenderComponent("", game.graphics);
		mines2Label.addComponent(mine2Text);
		em.addEntity(mines2Label);
		
		Entity life2Label = new Entity("Player2Life");
		life2Label.setPosition(510, 80);
		life2Text = new TextRenderComponent("", game.graphics);
		life2Label.addComponent(life2Text);
		em.addEntity(life2Label);
	}

	@Override
	protected void update(float delta) {
		em.update(delta);
	}

	@Override
	protected void init() {
		em.setDebug(true);
		// Map parsen
		try {
			map.parse(map.getSource(), resourcesManager, true);
		} catch (SyntaxException e) {
			e.printStackTrace();
			JFrame frame = new JFrame("");
			JOptionPane.showMessageDialog(frame, "Die Map konnte nicht geparst werden", "Syntax Fehler", JOptionPane.ERROR_MESSAGE);
			game.setScreen(LaunchTanks.mainMenu);
			return;
		} catch (SemanticException e) {
			e.printStackTrace();
			JFrame frame = new JFrame("");
			JOptionPane.showMessageDialog(frame, "Die Map konnte nicht geladen werden. Sie ist semantisch inkorrekt.", "Semantischer Fehler", JOptionPane.ERROR_MESSAGE);
			game.setScreen(LaunchTanks.mainMenu);
			return;
		}

		// Alle Mapentities dem Entitiymanager uebergeben
		Iterator<Entity> it = map.getEntities().iterator();
		while(it.hasNext()){
			em.addEntity(it.next());
		}

		Entity entity = new Entity("Dummy");

		// Wird der Panzer des Spielers zerstoert, ...
		EEAEvent event = new EntityDestroyedEvent(Tanks.player1);

		// ... dann stoppe den Timer, ...
		event.addAction(new EEAAction() {

			@Override
			public boolean act(float delta) {
				GameplayLog.getInstance().timer.stop();
				return true;
			}

		});

		// ... .oeffne MessageDialog "Sie haben verloren." ...
		event.addAction(new EEAAction() {
			@Override
			public boolean act(float delta) {
				JFrame frame = new JFrame("");

				if (!GameplayLog.getInstance().isMultiplayer() && !Tanks.debug) {
					Highscore h = new Highscore("", GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().timer.get());
					if(!HighscoreList.getInstance().isNewHighscore(h)) {
						JOptionPane.showMessageDialog(frame, "Sie haben verloren.","Schade.",1);
					} else {
						String name = JOptionPane.showInputDialog(frame, "Bitte nennen Sie Ihren Namen:","Verloren, aber neuer Highscore",1);
						h = new Highscore(name, GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().timer.get());
						HighscoreList.getInstance().addHighscore(h);
					}
				}
				else {
					JOptionPane.showMessageDialog(frame, "Spieler 2 hat gewonnen!","Spieler 2 gewinnt!",1);
				}
				return true;
			}
		});
		// ... und wechsle ins Hauptmenue
		event.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu, true));
		entity.addComponent(event);

		// Wird ein gegnerischer Panzer zerstoert, ...
		event = new ANDEvent(new EntityDestroyedEvent(Tanks.opponentTank), new EntityDestroyedEvent(Tanks.player2));
		// ... dann ueberpruefe, ob alle Tanks zerstoert wurden. 
		// Wenn ja, dann oeffne MessageDialog "Sie haben gewonnen" und wechsle ins Hauptmenue,
		// ansonsten bleibe im laufenden Spiel
		event.addAction(new EEAAction(){

			@Override
			public boolean act(float delta) {
				GameplayLog.getInstance().timer.stop();
				// TODO Pause something, probably the simulation aka our entityManager/Stage.
				//gc.pause();

				// DialogFenster				
				JFrame frame = new JFrame("");

				if(!GameplayLog.getInstance().isMultiplayer() && !Tanks.debug) {
					Highscore h = new Highscore("", GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().timer.get());
					if(!HighscoreList.getInstance().isNewHighscore(h)) {
						JOptionPane.showMessageDialog(frame, "Sie haben gewonnen.","Herzlichen Glückwunsch.",1);
					} else {
						String name = JOptionPane.showInputDialog(frame, "Bitte nennen Sie Ihren Namen:","Gewonnen und neuer Highscore",1);
						h = new Highscore(name, GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().timer.get());
						HighscoreList.getInstance().addHighscore(h);
					}
				}
				else {
					JOptionPane.showMessageDialog(frame, "Spieler 1 hat gewonnen!","Spieler 1 gewinnt!",1);
				}

				em.reset();

				String nextMap = GameplayLog.getInstance().getNextMap().substring(1, GameplayLog.getInstance().getNextMap().length()-1);
				if(nextMap.equals(Tanks.finish)){
					map.resetToDefault();
					game.setScreen(LaunchTanks.mainMenu);

					// TODO Find out what this method does and whether we need it.
					// gc.reinit();
				} else {
					map.load(nextMap);
					//TODO Find out what this method does and whether we need it.
					// sb.init(gc);
				}

				return true;
			}

		});
		entity.addComponent(event);

		// Wird die Taste 'p' gedrueckt, ...
		event = new KeyPressedEvent(Input.Keys.P);
		// ... dann wird das Spiel "eingefroren"
		event.addAction(new EEAAction(){
			@Override
			public boolean act (float delta) {
				// TODO Find out what instance to pause, probably this screen
				//				if(gc.isPaused()){
				//					gc.resume();
				//					GameplayLog.getInstance().timer.start();
				//				} else {
				//					gc.pause();
				//					GameplayLog.getInstance().timer.stop();
				//				}
				return true;
			}    		
		});
		entity.addComponent(event);


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
		if(timeLimit > 0){
			event = new TimeEvent(timeLimit - GameplayLog.getInstance().timer.get(), false);
			event.addAction(new EEAAction(){

				@Override
				public boolean act(float delta) {

					JFrame frame = new JFrame("");
					Highscore h = new Highscore("", GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().timer.get());

					// Wenn kein Multiplayer-Spiel und kein Debug, dann zeige Dialog
					if(!GameplayLog.getInstance().isMultiplayer() && !Tanks.debug) {

						// Wenn ein neuer Highscore aufgestellt wurde, dann Name abfragen, sonst Schade
						if(!HighscoreList.getInstance().isNewHighscore(h)) {
							JOptionPane.showMessageDialog(frame, "Sie haben verloren.","Schade.",1);
						} else {
							String name = JOptionPane.showInputDialog(frame, "Bitte nennen Sie Ihren Namen:","Verloren, aber neuer Highscore",1);
							h = new Highscore(name, GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().timer.get());
							HighscoreList.getInstance().addHighscore(h);
						}
					}
					return true;
				}

			});

			event.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu));
			entity.addComponent(event);

			event = new RandomEvent(0.25);
			event.addAction(new SpawnPickupAction(resourcesManager));
			entity.addComponent(event);
		}

		// Hinzufuegen der dummy-Entity
		em.addEntity(entity);

		createUI();

	}

}
