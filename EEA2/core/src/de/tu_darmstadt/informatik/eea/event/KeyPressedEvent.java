package de.tu_darmstadt.informatik.eea.event;

public class KeyPressedEvent extends EEAInputEvent {
	
	public static final String ID = "KeyPressedEvent";
	
	private final int key;
	private boolean keyWasDown = false;
	
	/**
	 * @param key Key from the libGDX @Input.Keys
	 */
	public KeyPressedEvent(int key){
		super(ID);
		this.key = key;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(key == keycode){
			keyWasDown = true;
		}
		return false;
	}

	@Override
	public boolean eventTriggered(float delta) {
		if(keyWasDown) {
			keyWasDown = false;
			return true;
		}
		return false;
	}

}
