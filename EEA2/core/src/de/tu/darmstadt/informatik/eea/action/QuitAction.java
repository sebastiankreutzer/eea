package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;

public class QuitAction extends Action {

	@Override
	public boolean act(float delta) {
		Gdx.app.exit();
		return true;
	}

}
