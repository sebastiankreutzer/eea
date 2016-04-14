package de.tu_darmstadt.informatik.eea.event.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import de.tu_darmstadt.informatik.eea.event.EEAEvent;

/**
 * 
 * This class serves as a base for all input events.
 * It provides methods for handling mouse and keyboard events.
 * 
 * @author Johann Reinhard
 *
 */
public abstract class EEAInputEvent extends EEAEvent {
	
	protected EEAInputProcessor inputProcessor;

	public EEAInputEvent(String componentID) {
		super(componentID);
		inputProcessor = new EEAInputProcessor(this);
	}
	
	public void onAddComponent() {
		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.addProcessor(inputProcessor);
	};
	
	public void onRemoveComponent() {
		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.removeProcessor(inputProcessor);
	}
	
	public boolean keyDown(int keycode) {
		return false;
	}
	
	public boolean keyUp(int keycode) {
		return false;
	}
	
	public boolean keyTyped(char character) {
		return false;
	}
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	public boolean scrolled(int amount) {
		return false;
	}

}
