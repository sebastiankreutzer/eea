package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

public class BackgroundFactory implements IEntityFactory {
	
	private final String file;
	private final boolean debug;
	
	public BackgroundFactory(String file, boolean debug){
		this.file = file;
		this.debug = debug;
	}

	@Override
	public Entity createEntity() {
		Entity background = new Entity("background");
		background.setScale(1.3f);
		try {
			if(!debug)
			background.addComponent(new ImageRenderComponent(new Image(file)));
		} catch (SlickException e) {
			e.printStackTrace();
		}
    	
    	
		return background;
	}

}
