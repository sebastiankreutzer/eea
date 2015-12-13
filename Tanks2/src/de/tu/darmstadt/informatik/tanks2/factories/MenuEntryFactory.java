package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu.darmstadt.informatik.eea.event.ANDEvent;
import de.tu.darmstadt.informatik.eea.event.MouseClickedEvent;
import de.tu.darmstadt.informatik.eea.event.MouseEnteredEvent;
import de.tu.darmstadt.informatik.eea.states.EntityManager;

public class MenuEntryFactory {
	
	private final EntityManager em;
	private final EEAGame eeaGame;
	
	private float startX = 0f, startY = 0f;
	private float distX = 100f, distY = 20f;
	private float space = 10f;
	
	private String name;
	private Action[] actions;
	private String texturePath;
	
	
	public MenuEntryFactory(EntityManager entitymanager, EEAGame eeaGame){
		em = entitymanager;
		this.eeaGame = eeaGame;
	}
	
	public void setDimensions(float x, float y, float width, float height){
		startX = x;
		startY = y;
		distX = width;
		distY = height;
	}
	

	public void prepareMenuEntry(String name, String texturePath, Action... action){
		this.name = name;
		this.texturePath = texturePath;
		this.actions = action;
		
		startY = startY - distY - space;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Entity makeMenuEntry(){
		
		Entity imageEntity = new Entity(name);
		imageEntity.addComponent(new ImageRenderComponent(texturePath, this.eeaGame.getResourcesManager()));
		imageEntity.setBounds(startX, startY, distX, distY);
		em.addEntity(imageEntity);
		
		ANDEvent event = new ANDEvent(new MouseClickedEvent(), new MouseEnteredEvent());
		for(Action a : actions)
		event.addAction(a);
		imageEntity.addComponent(event);
		
		return imageEntity;
	}
	
	public Entity makeMenuEntryText(){
		Entity textEntity = new Entity(name + "Text");
		textEntity.addComponent(new TextRenderComponent(name, this.eeaGame.graphics));
		textEntity.setBounds(startX + 10, startY, distX - 20, distY);
		em.addEntity(textEntity);
		
		return textEntity;
	}
}
