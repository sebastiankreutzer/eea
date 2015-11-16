package de.tu.darmstadt.informatik.eea.event;

import com.badlogic.gdx.Gdx;

public class KeyPressedEvent extends EEAEvent {
	
	public static final String ID = "KeyPressedEvent";
	
	private final int key;
	
	/**
	 * @param key Key from the libGDX @Input.Keys
	 */
	public KeyPressedEvent(int key){
		super(ID);
		this.key = key;
	}

	@Override
	public boolean eventTriggered(float delta) {
		return Gdx.input.isKeyPressed(key);
	}

}
