package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.Gdx;

public class LoopInputEvent extends EEAInputEvent implements IMouseStatus{

	private static final String ID = "LoopInputEvent";
	private int mouseX;
	private int mouseY;

	public LoopInputEvent() {
		super(ID);
	}

		
	@Override
	public int getMouseX() {
		return mouseX;
	}

	@Override
	public int getMouseY() {
		return mouseY;
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		this.mouseX = CoordinateHelper.convertToViewSpaceX(x);
		this.mouseY = CoordinateHelper.convertToViewSpaceY(y);
		return true;
	}

}
