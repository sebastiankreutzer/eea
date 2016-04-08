package de.tu_darmstadt.informatik.eea.action;

import de.tu_darmstadt.informatik.eea.entity.EntityManager;

/**
 * This action destroys its acting entity, that is, it removes it from the
 * {@link EntityManager} and clears its fields.
 * 
 * @author jr
 *
 */
public class DestroyEntityAction extends EEAAction {

	@Override
	public boolean act(float delta) {
		getEntity().remove();
		return false;
	}

}
