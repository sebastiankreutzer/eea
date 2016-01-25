package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;

public class BackgroundFactory {
	
	private final String file;
	private IResourcesManager resourcesManager;
	
	public BackgroundFactory(String file, IResourcesManager resourcesManager){
		this.file = file;
		this.resourcesManager = resourcesManager;
	}
	
	public Entity createEntity() {
		Entity background = new Entity("background");
		background.addComponent(new ImageRenderComponent(file, resourcesManager));
		return background;
	}

}
