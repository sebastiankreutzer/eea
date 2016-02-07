package de.tu_darmstadt.informatik.eea.event;

public class MouseMovedEvent extends EEAInputEvent {
	public static final String ID = "KeyPressedEvent";
	private boolean mouseWasMoved = false;
	private int screenX;
	private int screenY;
	
	/**
	 * @param key Key from the libGDX @Input.Keys
	 */
	public MouseMovedEvent(){
		super(ID);
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(screenX != this.screenX && screenY != this.screenY){
			this.screenX = screenX;
			this.screenY = screenY;
			mouseWasMoved = true;
		}
		return false;
	}

	@Override
	public boolean eventTriggered(float delta) {
		if(mouseWasMoved) {
			mouseWasMoved = false;
			return true;
		}
		return false;
	}
}
