package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.scenes.scene2d.Action;

public class MoveAction extends Action {
	
	private float dX, dY;
	
	public MoveAction(float xDir, float yDir) {
		dX = xDir;
		dY = yDir;
	}

	@Override
	public boolean act(float delta) {
		target.moveBy(dX * delta, dY * delta);
		return true;
	}
	
}
