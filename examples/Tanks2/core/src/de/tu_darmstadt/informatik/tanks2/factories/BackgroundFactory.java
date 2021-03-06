package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.component.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;

public class BackgroundFactory {
	
	private final String file;
	private IResourceManager resourcesManager;
	
	public BackgroundFactory(String file, IResourceManager resourcesManager){
		this.file = file;
		this.resourcesManager = resourcesManager;
	}
	
	public Entity createEntity() {
		Entity background = new Entity("background");
		background.addComponent(new ImageRenderComponent(file));
		return background;
	}

}
