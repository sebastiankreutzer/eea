package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class RenderComponent extends Component{

	public RenderComponent(String componentID) {
		super(componentID);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void render(Batch batch);
}
