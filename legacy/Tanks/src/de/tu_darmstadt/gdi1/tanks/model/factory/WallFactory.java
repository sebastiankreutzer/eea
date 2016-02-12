package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.tanks.model.entities.Wall;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

public class WallFactory implements IEntityFactory{
	
	private final int maxLife;
	private final int life;
	private final int rotation;
	private final float scaling;
	private final Vector2f position;
	private final boolean debug;
	
	
	public WallFactory(int maxLife, int life, int rotation, int scaling,int x, int y, boolean debug){
		this.maxLife = maxLife;
		this.life = life;
		this.rotation = rotation;
		this.scaling = scaling / 100.0f;
		this.position = new Vector2f(x,y);
		this.debug = debug;
	}

	@Override
	public Entity createEntity() {
		Wall wall = new Wall("Wall" +Math.random());
		wall.setRotation(rotation);
		wall.setMaxLife(maxLife);
		wall.setLife(life);
		wall.setPosition(position);
		wall.setScale(scaling);
		wall.setPassable(false);
		
		if(!debug){
			try {
				wall.addComponent(new ImageRenderComponent(new Image("//assets/thematrixer-net_stahlwand.jpg")));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		} else wall.setSize(new Vector2f(10,10));
		
		return wall;
	}

}
