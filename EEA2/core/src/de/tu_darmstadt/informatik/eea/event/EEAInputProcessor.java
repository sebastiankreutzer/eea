package de.tu_darmstadt.informatik.eea.event;

import com.badlogic.gdx.InputProcessor;

public class EEAInputProcessor implements InputProcessor {
	
	EEAInputEvent parent;
	
	public EEAInputProcessor(EEAInputEvent parent) {
		this.parent = parent;
	}

	@Override
	public boolean keyDown(int keycode) {
		return parent.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		return parent.keyUp(keycode);
	}

	@Override
	public boolean keyTyped(char character) {
		return parent.keyTyped(character);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = CoordinateHelper.convertToViewSpaceX(screenX);
		screenY = CoordinateHelper.convertToViewSpaceY(screenY);
		return parent.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = CoordinateHelper.convertToViewSpaceX(screenX);
		screenY = CoordinateHelper.convertToViewSpaceY(screenY);
		return parent.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		screenX = CoordinateHelper.convertToViewSpaceX(screenX);
		screenY = CoordinateHelper.convertToViewSpaceY(screenY);
		return parent.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		screenX = CoordinateHelper.convertToViewSpaceX(screenX);
		screenY = CoordinateHelper.convertToViewSpaceY(screenY);
		return parent.mouseMoved(screenX, screenY);
	}

	@Override
	public boolean scrolled(int amount) {
		return parent.scrolled(amount);
	}

}
