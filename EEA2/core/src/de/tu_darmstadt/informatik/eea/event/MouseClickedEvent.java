package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.Input;

public class MouseClickedEvent extends EEAInputEvent {

	public static final String ID = "MouseClickedEvent";

	private boolean mouseWasDown = false;
	private final int button;

	private int mouseX, mouseY;

	public MouseClickedEvent() {
		super(ID);
		button = Input.Buttons.LEFT;
	}

	public MouseClickedEvent(int button) {
		super(ID);
		this.button = button;
	}

	@Override
	public boolean touchDown(int mouseX, int mouseY, int pointer, int button) {
		if (this.button == button) {
			mouseWasDown = true;
			this.mouseX = mouseX;
			this.mouseY = mouseY;
		}
		// Returning true would prevent that other events handle this event too.
		return false;
	}

	@Override
	public boolean eventTriggered(float delta) {
		if (mouseWasDown) {
			mouseWasDown = false;
			return true;
		}
		return false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

}
