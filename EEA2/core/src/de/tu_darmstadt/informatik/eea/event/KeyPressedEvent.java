package de.tu_darmstadt.informatik.eea.event;

/**
 * This event is triggered when the key with the specified key code is pressed.
 * Note that this event triggers only once, event the key is held down for
 * multiple updates. If you want the actions to fire continuously, use
 * {@link de.tu_darmstadt.informatik.eea.event.KeyDownEvent} instead.
 * 
 * @author Johann Reinhard
 *
 */
public class KeyPressedEvent extends EEAInputEvent {

	public static final String ID = "KeyPressedEvent";

	private final int key;
	private boolean keyWasDown = false;

	/**
	 * Creates a new KeyPressedEvent.
	 * 
	 * @param key
	 *            Key identifier from {@link Input.Keys}.
	 */
	public KeyPressedEvent(int key) {
		super(ID);
		this.key = key;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (key == keycode) {
			keyWasDown = true;
		}
		return false;
	}

	@Override
	public boolean eventTriggered(float delta) {
		if (keyWasDown) {
			keyWasDown = false;
			return true;
		}
		return false;
	}

}
