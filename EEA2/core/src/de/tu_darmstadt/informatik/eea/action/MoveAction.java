package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

public class MoveAction extends EEAMovement {
	
	private float dX, dY;
	
	public MoveAction(float xDir, float yDir){
		super(Align.bottomLeft);
		dX = xDir;
		dY = yDir;
	}
	

	public MoveAction(float xDir, float yDir, int alignment) {
		super(alignment);
		dX = xDir;
		dY = yDir;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		Vector2 p = new Vector2(getActor().getX(alignment), getActor().getY(alignment));
		p.add(dX * delta, dY * delta);
		return p;
	}
	
}
