package de.tu.darmstadt.informatik.eea.event;

import com.badlogic.gdx.Input;

public class MouseClickedEvent extends EEAInputEvent {
	
	public static final String ID = "MouseClickedEvent";
	
	private boolean mouseWasDown = false;
	private final int button;

	public MouseClickedEvent() {
		super(ID);
		button = Input.Buttons.LEFT;
	}
	
	public MouseClickedEvent(int button){
		super(ID);
		this.button = button;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(this.button == button){
			mouseWasDown = true;
		}
		// Returning true would prevent that other events handle this event too.
		return false;
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
