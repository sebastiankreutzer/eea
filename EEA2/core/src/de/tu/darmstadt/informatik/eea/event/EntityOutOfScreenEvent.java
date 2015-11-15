package de.tu.darmstadt.informatik.eea.event;

import com.badlogic.gdx.math.Vector2;

public class EntityOutOfScreenEvent extends EEAEvent {
	
	public static final String ID = "EntityOutOfScreenEvent";
	
	public EntityOutOfScreenEvent(){
		super(ID);
	}

	@Override
	public boolean eventTriggered(float delta) {
		Vector2 lb = new Vector2(owner.getX(), owner.getY());
		if(lb.x < 0 || lb.y < 0) {
			return true;
		}
		owner.getStage().getHeight();
		return false;
	}

}
