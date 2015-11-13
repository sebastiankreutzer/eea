package de.tu.darmstadt.informatik.eea.entity;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor {

	private final String id;

	private List<Component> components;
	private RenderComponent renderComponent;
	
	
	public Entity(String id) {
		this.id = id;
	}
	
	public void addComponent(Component c) {
		components.add(c);
		
		if (c instanceof RenderComponent)
			this.renderComponent = (RenderComponent) c;
		
		c.setOwnerEntity(this);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (renderComponent !=  null)
			renderComponent.render(batch);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		for (Component c : components) {
			c.update(delta);
		}
	}
	
	public String getID() {
		return id;
	}
	
	

}
