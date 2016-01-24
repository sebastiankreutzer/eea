package de.tu.darmstadt.informatik.tanks2;

import java.util.List;

import com.badlogic.gdx.Input.Keys;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu.darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu.darmstadt.informatik.eea.states.EEAGameState;
import de.tu.darmstadt.informatik.tanks2.highscore.Highscore;
import de.tu.darmstadt.informatik.tanks2.highscore.HighscoreList;

public class HighscoreState extends EEAGameState {

	private HighscoreList highscoreList;

	public HighscoreState(EEAGame game) {
		super(game);
	}

	@Override
	protected void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		Entity background = new Entity("background");	// Entitaet fuer Hintergrunde
		background.addComponent(new ImageRenderComponent("highscore_menu.png", game.getResourcesManager())); // Bildkomponente
		em.addEntity(background);
		
		this.highscoreList = HighscoreList.getInstance();
		if(!highscoreList.hasHighscoreLoaded()) {
			Entity error_Message = new Entity("error_message");
			error_Message.addComponent(new TextRenderComponent("Fuer diese Map wurden keine gespeicherten Highscores gefunden.", game.graphics));
			error_Message.setPosition(200, 300);
			em.addEntity(error_Message);
		} else {
			List<Highscore> highscores = highscoreList.getHighscores();
			if(highscores.isEmpty()) {
				Entity empty_message = new Entity("empty_message");
				empty_message.addComponent(new TextRenderComponent("Es hat noch niemand einen Highscore erspielt.", game.graphics));
				empty_message.setPosition(200, 300);
				em.addEntity(empty_message);
			} else {
				StringBuilder sb;
				Highscore hsc;
				for(int i = 0; i < highscores.size(); i++) {
					sb = new StringBuilder();
					sb.append(i + 1);
					sb.append(". ");
					
					hsc = highscores.get(i);
					if(hsc.getPlayerName() != null) {
						sb.append(hsc.getPlayerName());
					} else {
						sb.append("Unbekannt");
					}
					
					sb.append(" - ");
					sb.append(hsc.getFiredShots());
					sb.append(" Shots, ");
					sb.append(hsc.getPassedTime()/1000);
					sb.append(" Sekunden");
					
					Entity highscoreEntity = new Entity("entry"+i);
					highscoreEntity.addComponent(new TextRenderComponent(sb.toString(), game.graphics));
					highscoreEntity.setPosition(100, 450 - i * 50);
					em.addEntity(highscoreEntity);
				}
			}
		}
		
		onEscapePressed();
	}
	
	private void onEscapePressed() {
		// Erzeuge folgendes Event "Escape-Taste gedrueckt"
		KeyPressedEvent ESC_pressed = new KeyPressedEvent(Keys.ESCAPE);

		// Erzeuge die Action "Gehe ins Hauptmenue" und fuege sie dem 
		// ESC_pressed Event hinzu
		ESC_pressed.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu));

		// Erstelle eine Hintergrund-Entitaet, die auf das Druecken der 
		// "Escape-Taste" lauscht
		Entity dummy = new Entity("Escape_Listener");
		dummy.addComponent(ESC_pressed);
		
		// Fuege die dummy-Entity dem StateBasedEntityManager hinzu
		em.addEntity(dummy);
	}

}
