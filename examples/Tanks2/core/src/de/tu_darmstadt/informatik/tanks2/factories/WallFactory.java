package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.RectangleCollisionComponent;
import de.tu_darmstadt.informatik.tanks2.entities.Wall;

public class WallFactory {
	
	private final boolean debug;
	private IResourceManager resourcesManager;
	
	public WallFactory(boolean debug, IResourceManager resourcesManager) {
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
		wall.addComponent(new RectangleCollisionComponent());
		
		wall.addComponent(new ImageRenderComponent("thematrixer-net_stahlwand.jpg", resourcesManager));
		
		return wall;
	}

}
