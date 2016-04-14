package de.tu_darmstadt.informatik.eea.action;

import com.badlogic.gdx.Gdx;

import de.tu_darmstadt.informatik.eea.entity.EntityManager;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;

/**
 * This action closes the application. Note that this action will abort the
 * execution of all remaining {@link EEAAction} and {@link EEAEvent} from the
 * remaining Entities in the current EEAGameStates {@link EntityManager}.
 * 
 * @author Johann Reinhard
 *
 */
public class QuitAction extends EEAAction {

	@Override
	public boolean act(float delta) {
		Gdx.app.exit();
		return false;
	}
}
