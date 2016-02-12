package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

public class RotateAction extends EEAMovement {
	
	float rotation;
	
	public RotateAction(float rotation) {
		super(Align.bottomLeft);
		this.rotation = rotation;
	}
	
	/**
	 * Rotiert die Entity, wobei ein positiver Wert einer Rotation gegen und 
	 * ein negativer Wert im Uhrzeigersinn entspricht.
	 * @param rotation Die Richtung und Winkelgeschwindigkeit der Rotation
	 */
	public RotateAction(float rotation, int alignment) {
		super(alignment);
		this.rotation = rotation;
	}
	
	@Override
	public boolean act(float delta) {
		getActor().rotateBy(rotation * delta);
		return true;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		return new Vector2(getActor().getX(alignment), getActor().getY(alignment));
	}

}
