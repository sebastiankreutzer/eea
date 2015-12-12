package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class RenderComponent extends EEAComponent{

	public RenderComponent(String componentID) {
		super(componentID);
	}

	@Override
	public void update(float delta) {
		
	}
	
	public abstract void render(Batch batch);
	
}
