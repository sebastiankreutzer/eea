package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

public abstract class EEAMovement extends EEAAction {
	
	private Vector2 pos = new Vector2();;
	protected int alignment;
	
	public EEAMovement(int alignment) {
		this.alignment = alignment;
	}
	
	public EEAMovement() {
		this.alignment = Align.bottomLeft;
	}
	
	public abstract Vector2 getNextPosition(float delta);

	@Override
	public boolean act(float delta) {
		pos = getNextPosition(delta);
		getActor().setPosition(pos.x, pos.y, alignment);
		return true;
	}

}
