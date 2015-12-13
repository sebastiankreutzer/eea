package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;

public abstract class EEAMovement extends EEAAction {
	
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
