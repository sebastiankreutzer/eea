package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class MouseClickedEvent extends EEAInputEvent implements IMouseStatus {

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
		System.out.println("ScreenSpace ("+mouseX+"|"+mouseY+")");
		
		
		Camera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());		
		Vector3 touch = new Vector3(0,0,0);
		touch.set(mouseX , mouseY, 0);
		Vector3 world = camera.unproject(touch, -Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
         
		System.out.println("WorldSpace ("+world.x+"|"+world.y+")");
         
		
		if (this.button == button) {
			mouseWasDown = true;
			this.mouseX = (int) world.x;
			this.mouseY = (int) world.y;
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

	@Override
	public int getMouseX() {
		return mouseX;
	}

	@Override
	public int getMouseY() {
		return mouseY;
	}

}
