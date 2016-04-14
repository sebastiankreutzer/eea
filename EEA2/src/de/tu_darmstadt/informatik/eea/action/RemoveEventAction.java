package de.tu_darmstadt.informatik.eea.action;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;

/**
 * This action removes an {@link EEAEvent} from the acting entity.
 * 
 * @author Johann Reinhard
 *
 */
public class RemoveEventAction extends EEAAction {

	protected EEAEvent event;

	/**
	 * Creates a new RemoveEventAction.
	 * 
	 * @param event
	 *            The event that should be removed from the acting entity.
	 */
	public RemoveEventAction(EEAEvent event) {
		this.event = event;
	}

	@Override
	public boolean act(float delta) {
		((Entity) getTarget()).removeComponent(event);
		return false;
	}

}
