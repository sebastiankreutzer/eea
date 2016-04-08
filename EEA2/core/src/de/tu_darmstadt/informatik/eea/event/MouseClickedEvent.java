package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.Input;

/**
 * This event is triggered whenever a mouse click occurs.
 * @author sebastian
 *
 */
public class MouseClickedEvent extends EEAInputEvent {

	public static final String ID = "MouseClickedEvent";

	private boolean mouseWasDown = false;
	private final int button;

	private int mouseX, mouseY;

	/**
	 * Creates new MouseClickedEvent for the left mouse button.
	 */
	public MouseClickedEvent() {
		super(ID);
		button = Input.Buttons.LEFT;
	}

	/**
	 * Creates a new MouseClickedEvent for the given button.
	 * @param button The mouse button identifier, specified in {@link Input.Buttons}.
	 */
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

	/**
	 * Returns the x-coordinate of the last click.
	 * @return 
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * Returns the y-coordinate of the last click.
	 * @return
	 */
	public int getMouseY() {
		return mouseY;
	}

}
