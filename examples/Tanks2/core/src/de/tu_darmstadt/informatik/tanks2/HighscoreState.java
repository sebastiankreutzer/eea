package de.tu_darmstadt.informatik.tanks2;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.action.ChangeStateAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.event.KeyPressedEvent;
import de.tu_darmstadt.informatik.eea.states.EEAGameState;
import de.tu_darmstadt.informatik.tanks2.highscore.Highscore;
import de.tu_darmstadt.informatik.tanks2.highscore.HighscoreList;
import de.tu_darmstadt.informatik.tanks2.maps.Map;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;

/**
 * Ein GameplayState der zur Darstellung der Highscores der zuletzt geladenen
 * Map dient.
 * 
 * @author jr
 *
 */
public class HighscoreState extends EEAGameState {

	/**
	 * Erstellt einen neuen HighscoreState.
	 * 
	 * @param game
	 *            Die Instanz des Spiels
	 */
	public HighscoreState(EEAGame game) {
		super(game);
	}

	@Override
	protected void update(float delta) {
		// Dieser GameplayState muss nicht laufend aktualisiert werden, da sich
		// der Highscore nur zwischen den Partien oder beim Wechsel der Map
		// aendert.
	}

	@Override
	protected void init() {
		// Erstelle eine Entity fuer das Hintergrundbild
		Entity background = createBackgroundEntity();
		// Erstelle eine Entity fuer den EscapeListener
		Entity escapeListener = createEscapeEntity();
		// Fuege die Entities dem EntityManager hinzu
		em.addEntity(background);
		em.addEntity(escapeListener);

		// Hole die aktuelle HighscoreList
		String map = GameplayLog.getInstance().getMapName();
		HighscoreList highscoreList = HighscoreList.load(map, game.getResourcesManager());

		// Pruefe ob die HighscoreList geladen wurde
		if (!highscoreList.hasHighscoreLoaded()) {
			// Erstelle eine Entity fuer die Fehlermeldung
			Entity error_Message = new Entity("error_message");
			// Fuege der Entity eine TextRenderComponent mit der Fehlermeldung
			// hinzu
			error_Message.addComponent(new TextRenderComponent(
					"Fuer diese Map wurden keine gespeicherten Highscores gefunden.", game.graphics));
			// Zentriere die Entity und fuege sie dem EntityManager hinzu
			error_Message.setPosition(200, 300);
			em.addEntity(error_Message);
		} else {
			// Hole die Highscore Eintraege
			List<Highscore> highscores = highscoreList.getHighscores();
			// Erstelle die dazugehoerigen Entities
			List<Entity> hightscoreEntries = createHighscoreEntries(highscores);
			// Fuege diese dem EntityManager hinzu
			em.addEntities(hightscoreEntries);
		}
	}

	/**
	 * Erstellt fuer eine Liste von Highscores eine Liste von Entities, wobei
	 * jede Entity eine TextRenderComponent mit einer Darstellung eines
	 * Highscore Eintrags besitzt. Wenn die Liste der Highscores leer ist
	 * enthaelt die Liste der Entities nur ein Element mit einer entsprechenden
	 * Meldung als TextRenderComponent.
	 * 
	 * @param highscores
	 *            Die Liste der Highscore Eintraege, kann leer aber nicht null
	 *            sein
	 * @return Eine Liste von Entities mit TextRenderComponents, die mindestens
	 *         ein oder die Anzahl der Highscores viele Elemente enthaelt.
	 */
	private List<Entity> createHighscoreEntries(List<Highscore> highscores) {
		List<Entity> entries = new ArrayList<Entity>();
		if (highscores.isEmpty()) {
			Entity empty_message = new Entity("empty_message");
			empty_message.addComponent(
					new TextRenderComponent("Es hat noch niemand einen Highscore erspielt.", game.graphics));
			empty_message.setPosition(200, 300);
			entries.add(empty_message);
		} else {
			StringBuilder sb;
			Highscore hsc;
			for (int i = 0; i < highscores.size(); i++) {
				sb = new StringBuilder();
				sb.append(i + 1);
				sb.append(". ");

				hsc = highscores.get(i);
				if (hsc.getPlayerName() != null) {
					sb.append(hsc.getPlayerName());
				} else {
					sb.append("Unbekannt");
				}

				sb.append(" - ");
				sb.append(hsc.getFiredShots());
				sb.append(" Shots, ");
				sb.append(hsc.getPassedTime() / 1000);
				sb.append(" Sekunden");

				Entity highscoreEntity = new Entity("entry" + i);
				highscoreEntity.addComponent(new TextRenderComponent(sb.toString(), game.graphics));
				highscoreEntity.setPosition(100, 450 - i * 50);
				entries.add(highscoreEntity);
			}
		}

		return entries;
	}

	/**
	 * Gitb eine Entity die das Hintergrundbild als RenderComponent besitzt
	 * zurueck.
	 * 
	 * @return Eine Entity mit RenderComponent.
	 */
	private Entity createBackgroundEntity() {
		// Instanziert eine Entity fuer das Hintergrundbild
		Entity backgroundEntity = new Entity("background");
		// Fuegt dieser Entity eine ImageRenderComponent mit dem Hintergrundbild
		// hinzu
		backgroundEntity.addComponent(new ImageRenderComponent("highscore_menu.png", game.getResourcesManager()));
		return backgroundEntity;
	}

	/**
	 * Gibt eine Entity zurueck, die ein KeyPressedEvent mit der Escape-Taste
	 * als Trigger besitzt. Dieses Event loest eine ChangeStateAction aus die
	 * ins Hauptmenue wechselt.
	 * 
	 * @return Eine Entity mit KeyPressedEvent.
	 */
	private Entity createEscapeEntity() {
		// Erzeugt ein Event das beim Druecken der Escape Taste triggert
		KeyPressedEvent escapePressedEvent = new KeyPressedEvent(Keys.ESCAPE);

		// Fuegt dem Event eine ChangeStateAction hinzu die ins Hauptmenue
		// wechselt
		escapePressedEvent.addAction(new ChangeStateAction(game, LaunchTanks.mainMenu));

		// Erstelle eine Hintergrund-Entitaet, die auf das Druecken der
		// "Escape-Taste" lauscht
		Entity escapeListenerEntity = new Entity("Escape_Listener");
		escapeListenerEntity.addComponent(escapePressedEvent);

		return escapeListenerEntity;
	}
}
