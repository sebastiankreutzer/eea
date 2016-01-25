package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;

public class MoveRelativeAction extends EEAMovement {
	
	float deltaOrtho, deltaLinear;
	
	public MoveRelativeAction(float linear, float ortho) {
		deltaOrtho = ortho;
		deltaLinear = linear;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		double rotation = Math.toRadians(getActor().getRotation());
		
		float cos = (float) Math.cos(rotation);
		float sin = (float) Math.sin(rotation);
		
		return new Vector2(
				(deltaOrtho * cos - deltaLinear * sin) * delta + getActor().getX(),
				(deltaOrtho * sin + deltaLinear * cos) * delta + getActor().getY()
				);
	}

}
