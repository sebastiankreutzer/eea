package de.tu.darmstadt.informatik.tanks2.factories;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.tanks2.entities.Wall;

public class WallFactory {
	
	private final boolean debug;
	private IResourcesManager resourcesManager;
	
	public WallFactory(boolean debug, IResourcesManager resourcesManager) {
		this.debug = debug;
		this.resourcesManager = resourcesManager;
	}
	
	public Entity createEntity(float x, float y, int maxLife, int life, float rotation, float scale) {
		Wall wall = new Wall("Wall" +Math.random());
		wall.setRotation(rotation);
		wall.setMaxLife(maxLife);
		wall.setLife(life);
		wall.setPosition(x, y);
		wall.setScale(scale);
		wall.setPassable(false);
		
		wall.addComponent(new ImageRenderComponent("thematrixer-net_stahlwand.jpg", resourcesManager));
		
		return wall;
	}

}
