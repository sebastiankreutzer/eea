package de.tu.darmstadt.informatik.eea.action;


public class DestroyEntityAction extends EEAAction {

	@Override
	public boolean act(float delta) {
		return getEntity().remove();
	}

}
