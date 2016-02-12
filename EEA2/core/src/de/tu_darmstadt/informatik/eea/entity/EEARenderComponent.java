package de.tu_darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class EEARenderComponent extends EEAComponent{

	public EEARenderComponent(String componentID) {
		super(componentID);
	}

	@Override
	public boolean update(float delta) {
		return true;
	}
	
	public abstract void render(Batch batch);
	
}
