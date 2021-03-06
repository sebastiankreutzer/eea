package de.tu_darmstadt.informatik.eea.event.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;

/**
 * This event is triggered whenever the mouse enters the owning entity.
 *
 * @author Johann Reinhard
 *
 */
public class MouseEnteredEvent extends EEAEvent {
	
	public static final String ID = "MouseEnteredEvent";

	/**
	 * Creates a new MouseEnteredEvent.
	 */
	public MouseEnteredEvent() {
		super(ID);
	}

	@Override
	public boolean eventTriggered(float delta) {
		
		Entity e = getOwnerEntity();
		
		Vector2 v = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		e.getStage().screenToStageCoordinates(v);
		e.stageToLocalCoordinates(v);
		
		if( e.hit(v.x, v.y, true) == e) return true;
		return false;
	}

}
