package de.tu_darmstadt.informatik.eea.event.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import de.tu_darmstadt.informatik.eea.EEAGame;

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
		Vector2 stagePosition = EEAGame.getGraphics().toStageCoordinates(screenX, screenY);
		return parent.touchDown((int) stagePosition.x, (int) stagePosition.y, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 stagePosition = EEAGame.getGraphics().toStageCoordinates(screenX, screenY);
		return parent.touchUp((int) stagePosition.x, (int) stagePosition.y, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 stagePosition = EEAGame.getGraphics().toStageCoordinates(screenX, screenY);
		return parent.touchDragged((int) stagePosition.x, (int) stagePosition.y, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector2 stagePosition = EEAGame.getGraphics().toStageCoordinates(screenX, screenY);
		return parent.mouseMoved((int) stagePosition.x, (int) stagePosition.y);
	}

	@Override
	public boolean scrolled(int amount) {
		return parent.scrolled(amount);
	}

}
