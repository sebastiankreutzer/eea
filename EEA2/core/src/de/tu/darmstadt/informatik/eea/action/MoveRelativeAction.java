package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;

public class MoveRelativeAction extends EEAMovement {
	
	float dC, dO;
	
	public MoveRelativeAction(float colinear, float orthogonal) {
		dC = colinear;
		dO = orthogonal;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		double rotation = Math.toRadians(getActor().getRotation());
		
		float cos = (float) Math.cos(rotation);
		float sin = (float) Math.sin(rotation);
		
		return new Vector2(
				(dC * cos - dO * sin) * delta + getActor().getX(),
				(dC * sin + dO * cos) * delta + getActor().getY()
				);
	}

}
