package de.tu_darmstadt.informatik.eea.event;

/**
 * This event is triggered whenever the mouse is moved.
 * @author Johann Reinhard
 *
 */
public class MouseMovedEvent extends EEAInputEvent {

	public static final String ID = "KeyPressedEvent";
	
	private boolean mouseWasMoved = false;
	private int mouseX;
	private int mouseY;
	
	/**
	 * Creates a new MouseMovedEvent.
	 */
	public MouseMovedEvent(){
		super(ID);
	}
	
	@Override
	public boolean mouseMoved(int mouseX, int mouseY) {
		if(mouseX != this.mouseX && mouseY != this.mouseY){
			this.mouseX = mouseX;
			this.mouseY = mouseY;
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
