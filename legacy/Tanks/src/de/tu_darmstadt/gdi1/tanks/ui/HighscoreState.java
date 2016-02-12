package de.tu_darmstadt.gdi1.tanks.ui;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.highscore.Highscore;
import de.tu_darmstadt.gdi1.tanks.model.highscore.HighscoreList;

import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyPressedEvent;

public class HighscoreState extends BasicGameState {

    private int stateID = 2;
    private StateBasedEntityManager entityManager;
    private HighscoreList highscoreList;
    
    HighscoreState( int stateID ) {
       this.stateID = stateID;
       this.entityManager = StateBasedEntityManager.getInstance();
       this.highscoreList = HighscoreList.getInstance();
    }

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		if(!Tanks.debug) setBackground();
		onEscapePressed();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		entityManager.renderEntities(container, game, g);
		
		//g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 12), true));
		
		// Get Highscores
		List<Highscore> highscores = highscoreList.getHighscores();

		// Initial position values
		float x = 150;
		float y = 175;
		
		// Headline
		g.drawString("Name", x, y - 50);
		// Fired shots
		g.drawString("Schüsse", x + 225, y - 50);
		// Passed time
		g.drawString("Zeit", x + 385, y - 50);
		// Map in which the highscores have been scored
		g.drawString("Map: " + highscoreList.getMapName(), x - 40, y + 370);
		
		// Was a highscore file successfully loaded?
		if(highscoreList.hasHighscoreLoaded()) {
			
			// Draw the highscores
			int i = 1;
			for (Highscore hsc : highscores) {
				
				String playername;
				if (hsc.getPlayerName() == null) {
					playername = "Unknown";
				}
				else {
					playername = hsc.getPlayerName();
				}
				
				// Position
				g.drawString(String.valueOf(i) + ".", x - 40, y);
				// Name
				g.drawString(playername , x, y); 
				// Fired Shots
				g.drawString(String.valueOf(hsc.getFiredShots()), x + 250, y);
				// Passed time
				g.drawString(String.valueOf(hsc.getPassedTime()/1000) + " s", x + 375, y);
				i++;
				y += 35;
			}
			// No highscore entries
			if (i == 1) {
				g.drawString("Es hat noch niemand einen Highscore erspielt.", x + 40, y + 50);
			}
		}
		else {
			g.drawString("Für diese Map wurden keine gespeicherten Highscores gefunden.", x - 40, y + 50);
		}
		
	}
	
	private void setBackground() throws SlickException {
		// Hintergrund laden
		Entity background = new Entity("background");	// Entitaet fuer Hintergrund
		background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
		/** TODO
		 * Richtiger Hintergrund
		 */
		background.addComponent(new ImageRenderComponent(new Image("/assets/highscore_menu.png"))); // Bildkomponente

		// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
		StateBasedEntityManager.getInstance().addEntity(stateID, background);
	}
	
	private void onEscapePressed() {
		// Erzeuge folgendes Event "Escape-Taste gedrueckt"
		Event ESC_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);

		// Erzeuge die Action "Gehe ins Hauptmenue" und fuege sie dem 
		// ESC_pressed Event hinzu
		ESC_pressed.addAction(new ChangeStateAction(Tanks.MAINMENUSTATE));

		// Erstelle eine Hintergrund-Entitaet, die auf das Druecken der 
		// "Escape-Taste" lauscht
		Entity dummy = new Entity("dummy");
		dummy.addComponent(ESC_pressed);
		
		// Fuege die dummy-Entity dem StateBasedEntityManager hinzu
		entityManager.addEntity(stateID, dummy);
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
