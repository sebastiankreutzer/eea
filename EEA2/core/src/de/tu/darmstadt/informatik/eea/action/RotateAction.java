package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;

public class RotateAction extends EEAMovement {
	
	float rotation;
	
	/**
	 * Rotiert die Entity, wobei ein positiver Wert einer Rotation gegen und 
	 * ein negativer Wert im Uhrzeigersinn entspricht.
	 * @param rotation Die Richtung und Winkelgeschwindigkeit der Rotation
	 */
	public RotateAction(float rotation) {
		this.rotation = rotation;
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
