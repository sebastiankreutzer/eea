package de.tu_darmstadt.informatik.eea.action;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.EEAGameState;
import de.tu_darmstadt.informatik.eea.entity.EntityManager;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;

/**
 * This actions performs the transition into a new {@link EEAGameState}. Note
 * that this action will abort the execution of all remaining {@link EEAAction}
 * and {@link EEAEvent} from the remaining Entities in the current EEAGameStates
 * {@link EntityManager}.
 * 
 * @author jr
 *
 */
public class ChangeStateAction extends EEAAction {

	private final EEAGame game;
	protected EEAGameState newState;
	private boolean resetOldState;

	/**
	 * Creates a new ChangeStateAction.
	 * 
	 * @param game
	 *            The instance of the {@link EEAGame}.
	 * @param newState
	 *            The GameState to transition to.
	 * @param resetOldState
	 *            Whether the current GameState should be reset when
	 *            transitioning.
	 */
	public ChangeStateAction(EEAGame game, EEAGameState newState, boolean resetOldState) {
		this.game = game;
		this.newState = newState;
		this.resetOldState = resetOldState;
	}

	@Override
	public boolean act(float delta) {
		// Reset the current state if desired.
		if (resetOldState) {
			((EEAGameState) game.getScreen()).reset();
		}
		// Set the new state.
		game.setScreen(newState);
		return false;
	}

}
