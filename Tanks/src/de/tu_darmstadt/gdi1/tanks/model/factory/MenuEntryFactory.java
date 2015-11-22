package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.GameplayLog;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import eea.engine.interfaces.IEntityFactory;

public class MenuEntryFactory implements IEntityFactory {

	private Entity entry;
	
	private String name;
    private Action action;
    private int entry_height = 150;
    
	public MenuEntryFactory(String name, GameContainer c, Action action, int entry_height) {
		this.name = name;
	    this.action = action;
	    this.entry_height = entry_height;
	}

	@Override
	public Entity createEntity() {
		
		try {
			float scale = 0.312f;
			
			entry = new Entity(name);
			entry.setPosition(new Vector2f(245,entry_height));
			entry.setScale(scale);
			if(!Tanks.debug) entry.addComponent(new ImageRenderComponent(new Image("assets/entry.png")));
			else entry.setSize(new Vector2f(10,10));
			ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
			mainEvents.addAction(action);
			
			
			if(name.equals("Neues Spiel starten")) mainEvents.addAction(new Action(){

				@Override
				public void update(GameContainer gc, StateBasedGame sb,
						int delta, Component event) {
					GameplayLog.getInstance().getTimer().reset();
					GameplayLog.getInstance().getTimer().start();
					
				}
				
			});
			
			if(name.equals("Zurück zum Spiel")) mainEvents.addAction(new Action(){

				@Override
				public void update(GameContainer gc, StateBasedGame sb,
						int delta, Component event) {
					GameplayLog.getInstance().getTimer().start();
					
				}
				
			});
			
			if(name.equals("Spielstand laden")) mainEvents.addAction(new Action(){

				@Override
				public void update(GameContainer gc, StateBasedGame sb,
						int delta, Component event) {
					GameplayLog.getInstance().getTimer().start();
					
				}
				
			});
			
			
			entry.addComponent(mainEvents);

			return entry;
			
		} catch (SlickException e) {
			
			e.printStackTrace();
			
			return null;
		}		
	}

}
