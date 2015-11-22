package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.EEAGraphics;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.entity.TextRenderComponent;
import de.tu.darmstadt.informatik.eea.event.ANDEvent;
import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.eea.event.MouseClickedEvent;
import de.tu.darmstadt.informatik.eea.event.MouseEnteredEvent;
import de.tu.darmstadt.informatik.eea.states.EntityManager;

public class MenuEntryFactory {
	
	private final EntityManager em;
	private final EEAGraphics g;
	
	private float startX = 0f, startY = 0f;
	private float distX = 100f, distY = 20f;
	private float space = 10f;
	
	private String name;
	private Texture texture;
	private Action action;
	
	public MenuEntryFactory(EntityManager entitymanager, EEAGraphics graphics){
		em = entitymanager;
		g = graphics;
	}
	
	public void setDimensions(float x, float y, float width, float height){
		startX = x;
		startY = y;
		distX = width;
		distY = height;
	}
	
	public void prepareMenuEntry(String name, Texture texture, Action action){
		this.name = name;
		this.texture = texture;
		this.action = action;
		
		startY = startY - distY - space;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Entity makeMenuEntry(){
		
		Entity imageEntity = new Entity(name);
		imageEntity.addComponent(new ImageRenderComponent(texture));
		imageEntity.setBounds(startX, startY, distX, distY);
		em.addEntity(imageEntity);
		
		ANDEvent event = new ANDEvent(new MouseClickedEvent(), new MouseEnteredEvent());
		event.addAction(action);
		imageEntity.addComponent(event);
		
		return imageEntity;
	}
	
	public Entity makeMenuEntryText(){
		Entity textEntity = new Entity(name + "Text");
		textEntity.addComponent(new TextRenderComponent(name, g));
		textEntity.setBounds(startX + 10, startY, distX - 20, distY);
		em.addEntity(textEntity);
		
		return textEntity;
	}
}
