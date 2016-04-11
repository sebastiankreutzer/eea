package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.Gdx;

/**
 * This event is triggered when the key with the specified key code is pressed.
 * The actions are executed on every update as long as the key is down. To
 * trigger only once, use
 * {@link de.tu_darmstadt.informatik.eea.event.KeyPressedEvent} instead.
 * 
 * @author Johann Reinhard
 *
 */
public class KeyDownEvent extends EEAInputEvent {

	public static final String ID = "MouseClickedEvent";

	private final int keycode;

	/**
	 * Creates a new KeyDownEvent.
	 * 
	 * @param keycode
	 *            Key identifier from {@link Input.Keys}.
	 */
	public KeyDownEvent(int keycode) {
		super(ID);
		this.keycode = keycode;
	}

	@Override
	public boolean eventTriggered(float delta) {
		return Gdx.input.isKeyPressed(keycode);
	}

}
