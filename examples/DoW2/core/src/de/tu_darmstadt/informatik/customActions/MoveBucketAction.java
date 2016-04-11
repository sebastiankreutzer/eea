package de.tu_darmstadt.informatik.customActions;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.action.EEAAction;

public class MoveBucketAction extends EEAAction {
	
	public MoveBucketAction() {
	}

	/**
	 * Sorgt dafür, dass der Bucket an der Maus kleben bleibt.
	 * Dazu wird die Mausposition abgefragt und die Entity, die dieser Action zugeordnet ist dementsprechend verschoben.
	 * In diesem Fall ist die zugeordnete Entity der Bucket.
	 */
	@Override
	public boolean act(float delta) {
		// hole die x-Koordinate der Maus.
		float x = EEAGame.getGraphics().getCursorPosition().x;
		
		// getEntity() gibt das Bucket, da diese Action nur dem Bucket hinzugefügt wordne ist
		getEntity().setPosition(x, 50, 1);
		
		return true;
	}

}
