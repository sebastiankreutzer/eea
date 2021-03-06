package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.EEAGraphics;
import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.component.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.component.TextRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.ANDEvent;
import de.tu_darmstadt.informatik.eea.event.input.MouseClickedEvent;
import de.tu_darmstadt.informatik.eea.event.input.MouseEnteredEvent;

/**
 * Eine Factory zun Erzeugen von Menueeintraegen. Ein Menueeintrag besteht aus
 * zwei Entities, eine mit TextRenderComponent und eine mit ImageRenderComponent
 * und Actions die beim Klicken ausgefuehrt werden.
 * 
 * @author jr
 *
 */
public class MenuEntryFactory {

	protected final IResourceManager resourcesManager;
	protected final EEAGraphics graphics;

	private float startX = 0f, startY = 0f;
	private float distX = 100f, distY = 20f;
	private float space = 10f;

	private String name;
	private EEAAction[] actions;
	private String texturePath;

	/**
	 * Erzeugt eine neue MenuEntryFactory mit den Standardwerten fuer Position
	 * und Groesse.
	 */
	public MenuEntryFactory() {
		this.resourcesManager = EEAGame.getResourceManager();
		this.graphics = EEAGame.getGraphics();
	}

	/**
	 * Setzt Ursprung des Menues und die Groesse der einzelnen Menueeintraege.
	 * 
	 * @param x
	 *            Der linke Rand des Menues
	 * @param y
	 *            Der untere Rand des Menues
	 * @param width
	 *            Die Breite eines Menueeintrages
	 * @param height
	 *            Die Hoehe eines Menueeintrages
	 */
	public void setDimensions(float x, float y, float width, float height) {
		startX = x;
		startY = y;
		distX = width;
		distY = height;
	}

	/**
	 * Bereitet einen Menueeintrag vor
	 * 
	 * @param text
	 *            Der Text des Menuepunktes
	 * @param texturePath
	 *            Das Hintergrundbild des Menueeintrages
	 * @param action
	 *            Die mit diesem Menueeintrag verknuepften Actions
	 */
	public void prepareMenuEntry(String text, String texturePath, EEAAction... action) {
		this.name = text;
		this.texturePath = texturePath;
		this.actions = action;

		startY = startY - distY - space;
	}

	/**
	 * Erzeugt die Hintergrund Entity fuer diesen Menueeintrag. Diese besitzt
	 * eine Event das ausloest wenn der Eintrag angeklickt wird und fuehrt die
	 * Actions aus.
	 * 
	 * @return Eine Entity mit Event und Actions
	 */
	public Entity makeMenuEntry() {
		// Erzeuge die Entity, fuege das Bild hinzu und setze Position und
		// Groesse
		Entity imageEntity = new Entity(name);
		imageEntity.addComponent(new ImageRenderComponent(texturePath));
		imageEntity.setBounds(startX, startY, distX, distY);
		// Beim anklicken des Entrags sollen die Actions ausgefuehrt werden
		ANDEvent event = new ANDEvent(new MouseClickedEvent(), new MouseEnteredEvent());
		for (EEAAction a : actions)
			event.addAction(a);
		imageEntity.addComponent(event);

		return imageEntity;
	}

	/**
	 * Eine Entity die den zughoerigen Text fuer den Menueeintrag darstellt
	 * 
	 * @return Eine Entity mit TextRenderComponent
	 */
	public Entity makeMenuEntryText() {
		Entity textEntity = new Entity(name + "Text");
		textEntity.addComponent(new TextRenderComponent(name));
		textEntity.setBounds(startX + 10, startY, distX - 20, distY);

		return textEntity;
	}
}
