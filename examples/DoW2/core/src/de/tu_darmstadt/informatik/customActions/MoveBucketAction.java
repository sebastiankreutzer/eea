package de.tu_darmstadt.informatik.customActions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.event.IMouseStatus;

public class MoveBucketAction extends EEAAction {
	private IMouseStatus mouseStatus;
	
	public MoveBucketAction(IMouseStatus mouseStatus) {
		this.mouseStatus = mouseStatus;
	}

	@Override
	public boolean act(float delta) {	
		getEntity().setPosition(mouseStatus.getMouseX(), 50, 1);
		return true;
	}

}
