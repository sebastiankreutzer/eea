package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;

public class RotateAction extends EEAMovement {
	
	float rotation;
	
	public RotateAction(float degree) {
		this.rotation = degree;
	}
	
	@Override
	public boolean act(float delta) {
		getActor().rotateBy(rotation * delta);
		return true;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		return new Vector2(getActor().getX(), getActor().getY());
	}

}
