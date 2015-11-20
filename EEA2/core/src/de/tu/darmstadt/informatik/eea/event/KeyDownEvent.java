package de.tu.darmstadt.informatik.eea.event;

import com.badlogic.gdx.Gdx;

public class KeyDownEvent extends EEAInputEvent {
	
	public static final String ID = "MouseClickedEvent";
	
	private final int keycode;

	public KeyDownEvent(int keycode) {
		super(ID);
		this.keycode = keycode;
	}

	@Override
	public boolean eventTriggered(float delta) {
		return Gdx.input.isKeyPressed(keycode);
	}

}
