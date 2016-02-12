package de.tu_darmstadt.gdi1.tanks.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.GameplayLog;
import de.tu_darmstadt.gdi1.tanks.model.factory.MenuEntryFactory;
import de.tu_darmstadt.gdi1.tanks.model.map.Map;


import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyPressedEvent;

public class OnPauseMenuState extends BasicGameState {

    private int stateID = 0;
    private StateBasedEntityManager entityManager;
    
    private final int distance = 50;
    private final int start_position = 263;
    
    OnPauseMenuState( int stateID ) {
       this.stateID = stateID;
       this.entityManager = StateBasedEntityManager.getInstance();
    }

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		// Setze die Groesse dieses Fenster
		if(container instanceof AppGameContainer){
			AppGameContainer app = ((AppGameContainer)container);
			app.setDisplayMode(800, 600, false);
		}
		
		if(!Tanks.debug) {
		// Hintergrund laden
		Entity background = new Entity("background");
		background.setPosition(new Vector2f(400,300));
		Image image = new Image("/assets/menu.png");
		
		ImageRenderComponent irc = new ImageRenderComponent(image);
		background.addComponent(irc);
		StateBasedEntityManager.getInstance().addEntity(stateID, background);
		}
		
		// Lege eine MenuEntryFactory an
		MenuEntryFactory m;
		int counter = 0;
		
		// Zurueck zum laufenden Spiel
		Action back_to_game = new ChangeStateAction(Tanks.GAMEPLAYSTATE);
		m = new MenuEntryFactory("Zurück zum Spiel", container, back_to_game, start_position+distance*counter);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;

		// Spielstand speichern
		Action save_game = new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {

				if(!Tanks.debug) {
					JFileChooser fc = new JFileChooser("saves/");
					fc.setFileFilter( new FileFilter() {
						@Override public boolean accept( File f ) {
							return f.isDirectory() || f.getName().toLowerCase().endsWith( ".tanks" );
						}
						@Override public String getDescription() {
							return "Tanks-Spielstaende";
						}
					});
					int state = fc.showSaveDialog(null);

					if( state == JFileChooser.APPROVE_OPTION ) {
						File file = fc.getSelectedFile();
						if(file.getName().endsWith(".tanks")) Map.getInstance().save(file.getName());
						else Map.getInstance().save(file.getPath() +".tanks");

					}
				} else {
					// only for testing
					File file = new File("saves/autosave.tanks");
					Map.getInstance().save(file.getPath());
				}
			}
		};
		m = new MenuEntryFactory("Spielstand speichern", container, save_game, start_position+distance*counter);
		Entity saveGame = m.createEntity();
		Event KEY_S_pressed = new KeyPressedEvent(Input.KEY_S);
		KEY_S_pressed.addAction(save_game);
		saveGame.addComponent(KEY_S_pressed);
		entityManager.addEntity(this.stateID, saveGame);
		counter++;
		
		// Zurueck ins Hauptmenue
		Action exit = new ChangeStateAction(Tanks.MAINMENUSTATE);
		m = new MenuEntryFactory("Zurück ins Hauptmenü", container, exit, start_position+distance*counter);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;
		
		// Erzeuge folgendes Event "Escape-Taste gedrueckt"
		Event ESC_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);
		
		// Erzeuge die Action "Gehe ins Hauptmenue" und fuege sie dem 
		// ESC_pressed Event hinzu
		ESC_pressed.addAction(new ChangeStateAction(Tanks.GAMEPLAYSTATE));
		ESC_pressed.addAction(new Action(){

			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
				GameplayLog.getInstance().getTimer().start();
			}
			
		});
    	
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
		
		entityManager.renderEntities(container, game, g);
		
		//g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 12), true));
		
		int counter = 0;
		
		g.drawString("Spiel ist pausiert", 70, 210);
		g.drawString("Zurück zum pausierten Spiel", 150, start_position+counter*distance); counter++;
		g.drawString("Spielstand speichern", 150, start_position+counter*distance); counter++; 
		g.drawString("Zurück zum Hauptmenü", 150, start_position+counter*distance); counter++;
		
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
