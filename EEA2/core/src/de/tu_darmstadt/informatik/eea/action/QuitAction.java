package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.Gdx;

public class QuitAction extends EEAAction {

	@Override
	public boolean act(float delta) {
		Gdx.app.exit();
		return true;
	}

}
