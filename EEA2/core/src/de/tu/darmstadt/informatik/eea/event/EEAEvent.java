package de.tu.darmstadt.informatik.eea.event;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.entity.EEAComponent;
import de.tu.darmstadt.informatik.eea.entity.Entity;

/**
 * An event can be used to trigger a defined list of
 * {@link de.tu.darmstadt.informatik.EEAAction} when the conditions specified by
 * the event itself are met. EEAEvents have one @{link
 * de.tu.darmstadt.informatik.Entity} registered as the owner, this entity will
 * usually be affected by the registered actions.
 * 
 * @author Sebastian Kreutzer, Johann Reinhard
 * @version 2.0
 */
public abstract class EEAEvent extends EEAComponent {

	protected ArrayList<Action> actions;

	/**
	 * Creates a new EEAEvent.
	 * 
	 * @param componentID
	 *            The ID that specifies this event.
	 */
	public EEAEvent(String componentID) {
		super(componentID);
		actions = new ArrayList<Action>();
	}

	/**
	 * Adds an action to this event and sets its actor to the owner of this
	 * event.
	 * 
	 * @param action
	 *            The action that should be added to the list of actions.
	 */
	public void addAction(Action action) {
		actions.add(action);
		action.setActor(owner);
	}

	public void setAction(int index, Action action) {
		if (actions.isEmpty()) {
			addAction(action);
		} else {
			actions.set(index, action);
			action.setActor(owner);
		}
	}

	/**
	 * Removes a specific action from this event.
	 * 
	 * @param action
	 *            The action that should be removed from the list of actions.
	 */
	public void removeAction(Action action) {
		actions.remove(action);
	}

	/**
	 * Removes all Actions associated to this event.
	 */
	public void clearActions() {
		actions.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.tu.darmstadt.informatik.eea.entity.Component#setOwnerEntity(de.tu.
	 * darmstadt.informatik.eea.entity.Entity)
	 */
	public void setOwnerEntity(Entity e) {
		super.setOwnerEntity(e);
		for (Action action : actions) {
			action.setActor(e);
		}
	}

	/**
	 * Checks whether the events actions should be triggered.
	 * 
	 * @param delta
	 *            The elapsed time in seconds. //TODO verify
	 * @return true if the specific conditions of the event are met, otherwise
	 *         false.
	 */
	public abstract boolean eventTriggered(float delta);

	public boolean update(float delta) {
		if (eventTriggered(delta)) {
			for (Action action : actions) {
				if (!action.act(delta))
					return false;
			}
		}
		return true;
	}

}
