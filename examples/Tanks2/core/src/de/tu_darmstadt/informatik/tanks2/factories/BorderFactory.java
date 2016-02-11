package de.tu_darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.utils.Align;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.component.collision.BorderCollisionComponent;

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
		// TODO Fix parsing?
		// border.addComponent(new BorderCollisionComponent(Align.bottom));
		
		return border;
	}
	
}
