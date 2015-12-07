package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.tanks2.entities.Wall;

public class WallFactory {
	
	private final int maxLife;
	private final int life;
	private final int rotation;
	private final float scaling;
	private final Vector2 position;
	private final boolean debug;
	
	
	public WallFactory(int maxLife, int life, int rotation, int scaling,int x, int y, boolean debug){
		this.maxLife = maxLife;
		this.life = life;
		this.rotation = rotation;
		this.scaling = scaling / 100.0f;
		this.position = new Vector2(x,y);
		this.debug = debug;
	}
	
	public Entity createEntity() {
		Wall wall = new Wall("Wall" +Math.random());
		wall.setRotation(rotation);
		wall.setMaxLife(maxLife);
		wall.setLife(life);
		wall.setPosition(position.x, position.y);
		wall.setScale(scaling);
		wall.setPassable(false);
		
		wall.addComponent(new ImageRenderComponent(new Texture("thematrixer-net_stahlwand.jpg")));
		
		return wall;
	}

}
