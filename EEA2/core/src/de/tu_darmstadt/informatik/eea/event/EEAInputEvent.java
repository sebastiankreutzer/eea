package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public abstract class EEAInputEvent extends EEAEvent implements InputProcessor {

	public EEAInputEvent(String componentID) {
		super(componentID);
	}
	
	public void onAddComponent() {
		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.addProcessor(this);
	};
	
	public boolean touchDownViewSpace(int screenX, int screenY, int pointer, int button){
		return false;
	}
	
	public boolean touchUpViewSpace(int screenX, int screenY, int pointer, int button){
		return false;
	}
	
	public boolean touchDraggedViewSpace(int viewSpaceX, int viewSpaceY, int pointer) {
		return false;
	}
	
	public boolean mouseMovedViewSpace(int viewSpaceX, int viewSpaceY) {
		return false;
	}
	
	@Override
	public void onRemoveComponent() {
		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.removeProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public final boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int viewSpaceX = CoordinateHelper.convertToViewSpaceX(screenX);
		int viewSpaceY = CoordinateHelper.convertToViewSpaceY(screenY);
		return touchDownViewSpace(viewSpaceX, viewSpaceY, pointer, button);
	}

	
	

	@Override
	public final boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int viewSpaceX = CoordinateHelper.convertToViewSpaceX(screenX);
		int viewSpaceY = CoordinateHelper.convertToViewSpaceY(screenY);
		return touchUpViewSpace(viewSpaceX, viewSpaceY, pointer, button);
	}

	@Override
	public final boolean touchDragged(int screenX, int screenY, int pointer) {
		int viewSpaceX = CoordinateHelper.convertToViewSpaceX(screenX);
		int viewSpaceY = CoordinateHelper.convertToViewSpaceY(screenY);
		return touchDraggedViewSpace(viewSpaceX, viewSpaceY, pointer);
	}

	@Override
	public final boolean mouseMoved(int screenX, int screenY) {
		int viewSpaceX = CoordinateHelper.convertToViewSpaceX(screenX);
		int viewSpaceY = CoordinateHelper.convertToViewSpaceY(screenY);
		return mouseMovedViewSpace(viewSpaceX, viewSpaceY);
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
