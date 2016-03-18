package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.component.collision.RectangleCollisionComponent;
import de.tu_darmstadt.informatik.tanks2.entities.Wall;

/**
 * Ein Factory zum Erzeugen von Wall Hindernissen.
 * @author jr
 *
 */
public class WallFactory {
	
	private final boolean debug;
	
	/**
	 * Erzeugt eine neue Wallfactory.
	 * @param debug
	 * Der Debugmodus
	 */
	public WallFactory(boolean debug) {
		this.debug = debug;
	}
	
	/**
	 * Erzeugt eine neue Wall mit den angegebenen Parametern.
	 * @param x
	 * @param y
	 * @param maxLife
	 * @param life
	 * @param rotation
	 * @param scale
	 * @return
	 */
	public Wall createWall(float x, float y, int maxLife, int life, float rotation, float scale) {
		Wall wall = new Wall("Wall" +Math.random());
		wall.setRotation(rotation);
		wall.setMaxLife(maxLife);
		wall.setLife(life);
		wall.setPosition(x, y);
		wall.setScale(scale);
		wall.addComponent(new RectangleCollisionComponent());
		
		wall.addComponent(new ImageRenderComponent("thematrixer-net_stahlwand.jpg"));
		
		return wall;
	}

}
