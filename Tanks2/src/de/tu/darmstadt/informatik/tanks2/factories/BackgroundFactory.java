package de.tu.darmstadt.informatik.tanks2.factories;

import de.tu.darmstadt.informatik.eea.EEAGraphics;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;

public class BackgroundFactory {
	
	private final String file;
	private EEAGraphics eeaGraphics;
	
	public BackgroundFactory(String file, EEAGraphics eeaGraphics){
		this.file = file;
		this.eeaGraphics = eeaGraphics;
	}
	
	public Entity createEntity() {
		Entity background = new Entity("background");
		background.addComponent(new ImageRenderComponent(file, eeaGraphics));
		return background;
	}

}
