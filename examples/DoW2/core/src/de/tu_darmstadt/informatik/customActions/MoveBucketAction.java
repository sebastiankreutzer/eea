package de.tu_darmstadt.informatik.customActions;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.action.EEAAction;

public class MoveBucketAction extends EEAAction {
	
	public MoveBucketAction() {
	}

	@Override
	public boolean act(float delta) {
		float x = EEAGame.getGraphics().getCursorPosition().x;
		getEntity().setPosition(x, 50, 1);
		return true;
	}

}
