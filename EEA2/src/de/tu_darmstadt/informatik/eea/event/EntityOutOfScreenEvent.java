package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.math.Vector2;

/**
 * This event can be used to detect when an Entity leaves the screen.
 *
 * @author Johann Reinhard
 *
 */
public class EntityOutOfScreenEvent extends EEAEvent {
	
	public static final String ID = "EntityOutOfScreenEvent";
	
	/**
	 * Creates a new EntityOutOfScreenEvent.
	 */
	public EntityOutOfScreenEvent(){
		super(ID);
	}

	@Override
	public boolean eventTriggered(float delta) {
		Vector2 lb = new Vector2(owner.getX(), owner.getY());
		if(lb.x < 0 || lb.y < 0 || lb.x > owner.getStage().getWidth() || lb.y > owner.getStage().getHeight()) {
			return true;
		}
		return false;
	}

}
