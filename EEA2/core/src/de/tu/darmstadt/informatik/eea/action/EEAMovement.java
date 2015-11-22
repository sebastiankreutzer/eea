package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

public abstract class EEAMovement extends Action {
	
	private Vector2 pos;
	
	public EEAMovement() {
		pos = new Vector2();
	}
	
	public abstract Vector2 getNextPosition(float delta);

	@Override
	public boolean act(float delta) {
		pos = getNextPosition(delta);
		getActor().setPosition(pos.x, pos.y);
		return true;
	}

}
