package de.tu_darmstadt.informatik.eea.action;


public class DestroyEntityAction extends EEAAction {

	@Override
	public boolean act(float delta) {
		getEntity().remove();
		return false;
	}

}
