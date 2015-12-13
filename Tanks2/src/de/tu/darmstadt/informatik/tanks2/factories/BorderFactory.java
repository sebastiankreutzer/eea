package de.tu.darmstadt.informatik.tanks2.factories;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public class BorderFactory {
	
	private float x, y, width, height;
	
	
	public BorderFactory(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Entity createEntity() {
		
		Entity border = new Entity("Border"+Math.random());
		border.setPosition(x, y);
		border.setSize(width, height);
		border.setVisible(false);
		border.setPassable(false);
		
		return border;
	}
	
}
