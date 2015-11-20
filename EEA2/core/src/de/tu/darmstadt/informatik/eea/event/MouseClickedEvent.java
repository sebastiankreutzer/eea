package de.tu.darmstadt.informatik.eea.event;

import com.badlogic.gdx.Input;

public class MouseClickedEvent extends EEAInputEvent {
	
	public static final String ID = "MouseClickedEvent";
	
	boolean mouseWasDown = false;

	public MouseClickedEvent() {
		super(ID);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Input.Buttons.LEFT){
			mouseWasDown = true;
		}
		// Returning true would prevent that other events handle this event too.
		return true;
	}

	@Override
	public boolean eventTriggered(float delta) {
		if(mouseWasDown) {
			mouseWasDown = false;
			return true;
		}
		return false;
	}

}
