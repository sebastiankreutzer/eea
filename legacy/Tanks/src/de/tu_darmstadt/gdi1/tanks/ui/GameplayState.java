package de.tu_darmstadt.gdi1.tanks.ui;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.GameplayLog;
import de.tu_darmstadt.gdi1.tanks.model.action.SpawnPickupAction;
import de.tu_darmstadt.gdi1.tanks.model.entities.Tank;
import de.tu_darmstadt.gdi1.tanks.model.event.EntityDestroyedEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.RandomEvent;
import de.tu_darmstadt.gdi1.tanks.model.event.TimeEvent;
import de.tu_darmstadt.gdi1.tanks.model.exceptions.SemanticException;
import de.tu_darmstadt.gdi1.tanks.model.exceptions.SyntaxException;
import de.tu_darmstadt.gdi1.tanks.model.highscore.Highscore;
import de.tu_darmstadt.gdi1.tanks.model.highscore.HighscoreList;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.IMap;
import de.tu_darmstadt.gdi1.tanks.model.map.Map;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.Checker;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.ErrorReporter;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.Parser;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.Scanner;
import de.tu_darmstadt.gdi1.tanks.model.map.parser.SourceFile;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyPressedEvent;

public class GameplayState extends BasicGameState {

	private int stateID;
	private StateBasedEntityManager entityManager;
	private Map map;
    
    GameplayState( int stateID ) {
       this.stateID = stateID;
       this.entityManager = StateBasedEntityManager.getInstance();
       this.map = Map.getInstance();
    }
    
    
    @Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
    	
    	// Map parsen
    	try {
			map.parse(map.getSource(), Tanks.debug);
		} catch (SyntaxException e) {
			e.printStackTrace();
			JFrame frame = new JFrame("");
			JOptionPane.showMessageDialog(frame, "Die Map konnte nicht geparst werden", "Syntax Fehler", JOptionPane.ERROR_MESSAGE);
			game.enterState(Tanks.MAINMENUSTATE);
			return;
		} catch (SemanticException e) {
			e.printStackTrace();
			JFrame frame = new JFrame("");
			JOptionPane.showMessageDialog(frame, "Die Map konnte nicht geladen werden. Sie ist semantisch inkorrekt.", "Semantischer Fehler", JOptionPane.ERROR_MESSAGE);
			game.enterState(Tanks.MAINMENUSTATE);
			return;
		}
    	
    	// Alle Mapentities dem Entitiymanager uebergeben
		Iterator<Entity> it = map.getEntities().iterator();
		while(it.hasNext()){
			entityManager.addEntity(stateID,it.next());
		}
		
    	Entity entity = new Entity("Dummy");
    	
    	// Wird der Panzer des Spielers zerstoert, ...
    	Event event = new EntityDestroyedEvent(Tanks.player1);
    	
    	// ... dann stoppe den Timer, ...
    	event.addAction(new Action(){

			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
				GameplayLog.getInstance().getTimer().stop();
				
			}
    		
    	});
    	
    	// ... .oeffne MessageDialog "Sie haben verloren." ...
    	event.addAction(new Action() {
    		@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
    			JFrame frame = new JFrame("");
    			
    			if (!GameplayLog.getInstance().isMultiplayer() && !Tanks.debug) {
	    			Highscore h = new Highscore("", GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().getTimer().getElapsedTime());
	    			if(!HighscoreList.getInstance().isNewHighscore(h)) {
	    				JOptionPane.showMessageDialog(frame, "Sie haben verloren.","Schade.",1);
	    			} else {
	    				String name = JOptionPane.showInputDialog(frame, "Bitte nennen Sie Ihren Namen:","Verloren, aber neuer Highscore",1);
	    				h = new Highscore(name, GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().getTimer().getElapsedTime());
	    				HighscoreList.getInstance().addHighscore(h);
	    			}
    			}
    			else {
    				JOptionPane.showMessageDialog(frame, "Spieler 2 hat gewonnen!","Spieler 2 gewinnt!",1);
    			}
    		}
    	});
    	// ... und wechsle ins Hauptmenue
    	event.addAction(new ChangeStateAction(Tanks.MAINMENUSTATE));
    	entity.addComponent(event);
    	
    	
    	// Wird ein gegnerischer Panzer zerstoert, ...
    	event = new ANDEvent(new EntityDestroyedEvent(Tanks.opponentTank), new EntityDestroyedEvent(Tanks.player2));
    	// ... dann ueberpruefe, ob alle Tanks zerstoert wurden. 
    	// Wenn ja, dann oeffne MessageDialog "Sie haben gewonnen" und wechsle ins Hauptmenue,
    	// ansonsten bleibe im laufenden Spiel
    	event.addAction(new Action(){

			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
				GameplayLog.getInstance().getTimer().stop();
			
				gc.pause();
				// DialogFenster				
				JFrame frame = new JFrame("");
				
				if(!GameplayLog.getInstance().isMultiplayer() && !Tanks.debug) {
	    			Highscore h = new Highscore("", GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().getTimer().getElapsedTime());
	    			if(!HighscoreList.getInstance().isNewHighscore(h)) {
	    				JOptionPane.showMessageDialog(frame, "Sie haben gewonnen.","Herzlichen Glückwunsch.",1);
	    			} else {
	    				String name = JOptionPane.showInputDialog(frame, "Bitte nennen Sie Ihren Namen:","Gewonnen und neuer Highscore",1);
	    				h = new Highscore(name, GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().getTimer().getElapsedTime());
	    				HighscoreList.getInstance().addHighscore(h);
	    			}
				}
				else {
					JOptionPane.showMessageDialog(frame, "Spieler 1 hat gewonnen!","Spieler 1 gewinnt!",1);
				}
				
				StateBasedEntityManager.getInstance().clearEntitiesFromState(stateID);
				
				String nextMap = GameplayLog.getInstance().getNextMap().substring(1, GameplayLog.getInstance().getNextMap().length()-1);
				if(nextMap.equals(Tanks.finish)){
					map.resetToDefault();
					sb.enterState(Tanks.MAINMENUSTATE);
					try {
						gc.reinit();
					} catch (SlickException e) {
						e.printStackTrace();
					}
				} else {
					map.load(nextMap);
					try {
						sb.init(gc);
					} catch (SlickException e) {
						e.printStackTrace();
						sb.enterState(Tanks.MAINMENUSTATE);
					}
				}
			}
    		
    	});
    	entity.addComponent(event);
    	
    	
    	// Wird die Taste 'p' gedrueckt, ...
    	event = new KeyPressedEvent(Input.KEY_P);
    	// ... dann wird das Spiel "eingefroren"
    	event.addAction(new Action(){
    		@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
				if(gc.isPaused()){
					gc.resume();
					GameplayLog.getInstance().getTimer().start();
				} else {
					gc.pause();
					GameplayLog.getInstance().getTimer().stop();
				}
				
			}    		
    	});
    	entity.addComponent(event);
    	
    	// Wird die Escape-Taste gedrueckt, ...
    	event = new KeyPressedEvent(Input.KEY_ESCAPE);
    	// ... dann wechsle ins OnPauseMenu ...
    	
    	// ... und stoppe den Timer
    	event.addAction(new Action(){

			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
				GameplayLog.getInstance().getTimer().stop();
				
			}
    		
    	});
    	
    	event.addAction(new ChangeStateAction(Tanks.ONPAUSEMENUSTATE));
    	entity.addComponent(event);
    	
    	long timeLimit = GameplayLog.getInstance().getTimeLimit();
    	if(timeLimit > 0){
    		event = new TimeEvent(timeLimit - GameplayLog.getInstance().getTimer().getElapsedTime(), false);
    		event.addAction(new Action(){

				@Override
				public void update(GameContainer gc, StateBasedGame sb,
						int delta, Component event) {
					
					JFrame frame = new JFrame("");
					Highscore h = new Highscore("", GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().getTimer().getElapsedTime());
	    			
					// Wenn kein Multiplayer-Spiel und kein Debug, dann zeige Dialog
					if(!GameplayLog.getInstance().isMultiplayer() && !Tanks.debug) {
						
						// Wenn ein neuer Highscore aufgestellt wurde, dann Name abfragen, sonst Schade
						if(!HighscoreList.getInstance().isNewHighscore(h)) {
							JOptionPane.showMessageDialog(frame, "Sie haben verloren.","Schade.",1);
						} else {
							String name = JOptionPane.showInputDialog(frame, "Bitte nennen Sie Ihren Namen:","Verloren, aber neuer Highscore",1);
							h = new Highscore(name, GameplayLog.getInstance().getNumberOfShots(), GameplayLog.getInstance().getTimer().getElapsedTime());
							HighscoreList.getInstance().addHighscore(h);
						}
					}
					
				}
    			
    		});
    		
    		event.addAction(new ChangeStateAction(Tanks.MAINMENUSTATE));
        	entity.addComponent(event);
    	}
    	
    	event = new RandomEvent(0.25);
    	event.addAction(new SpawnPickupAction());
    	entity.addComponent(event);
    	
    	
    	// Hinzufuegen der dummy-Entity
    	entityManager.addEntity(stateID, entity);
    	
    	// starte den Timer bei der Initialisierung
    	//GameplayLog.getInstance().getTimer().start();
    	
	}
    

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		entityManager.renderEntities(container, game, g);
		
		boolean multiplayer = GameplayLog.getInstance().isMultiplayer();
		
		//g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 12), true));
		
		Tank tank = ((Tank)entityManager.getEntity(this.stateID, Tanks.player1));
		if(tank != null) {
			if (multiplayer) {
				g.drawString("Spieler 1", 10, 20);
			}
			else {
				g.drawString("Vergangene Zeit: "+GameplayLog.getInstance().getTimer().getElapsedTime()/1000+" s", 10, 20);
			}
			g.drawString("Übrige Schüsse: "+tank.getActualShootAmmo()+"/"+tank.getMaxShootAmmo(), 10, 40);
			g.drawString("Übrige Minen: "+tank.getActualMinesAmmo()+"/"+tank.getMaxMinesAmmo(), 10, 60);
			g.drawString("Leben: "+tank.getActualLife()+"/"+tank.getMaxLife(), 10, 80);
		}
		if (multiplayer) {
			Tank tank2 = ((Tank) entityManager.getEntity(this.stateID, Tanks.player2));
			if (tank2 != null) {
				g.drawString("Spieler 2", 580, 20);
				g.drawString("Übrige Schüsse: "+tank2.getActualShootAmmo()+"/"+tank2.getMaxShootAmmo(), 580, 40);
				g.drawString("Übrige Minen: "+tank2.getActualMinesAmmo()+"/"+tank2.getMaxMinesAmmo(), 580, 60);
				g.drawString("Leben: "+tank2.getActualLife()+"/"+tank2.getMaxLife(), 580, 80);
			}
		}
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
	
	public void loadMap(String source) throws SyntaxException, SemanticException, SlickException {
    	// Lese die Map ein
		/*
    	try{
    	*/
		System.out.println("loading map " + source);
		
		entityManager.clearEntitiesFromState(getID());
		
    	
    	// Load highscore file of map if exist
    	HighscoreList.getInstance().load(source);
    	
		SourceFile sc = new SourceFile(source);
		Scanner lexer = new Scanner(sc);
		Parser parser = new Parser(lexer, new ErrorReporter());
		parser.setDebug(Tanks.debug);
		IMap map = new Checker(parser.parseMap()).check();
		
		Iterator<Entity> it = map.getEntities().iterator();
		while(it.hasNext()){
			entityManager.addEntity(stateID,it.next());
		}
    	/*
    	} catch (SyntaxException e) {
    		e.printStackTrace();
			throw new SlickException("Could not parse the map!");
		} catch (SemanticException e) {
			e.printStackTrace();
			throw new SlickException("Could not parse the map!");
		} 
		*/
	}
	
	
}
