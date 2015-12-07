package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.tanks.model.entities.Border;

import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

public class BorderFactory implements IEntityFactory{
	
	private final Vector2f size;
	private final Vector2f position;
	
	
	public BorderFactory(float x, float y, float width, float height){
		this.position = new Vector2f(x,y);
		this.size = new Vector2f(width, height);
	}

	@Override
	public Entity createEntity() {
		
		Entity border = new Border("Border"+Math.random());
		border.setPosition(position);
		border.setSize(size);
		border.setVisible(false);
		border.setPassable(false);
		
		
		return border;
	}
	
}
