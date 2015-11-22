package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;

public class MoveAction extends EEAMovement {
	
	private float dX, dY;
	
	public MoveAction(float xDir, float yDir) {
		dX = xDir;
		dY = yDir;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		Vector2 p = new Vector2(getActor().getX(), getActor().getY());
		p.add(dX * delta, dY * delta);
		return null;
	}
	
}
