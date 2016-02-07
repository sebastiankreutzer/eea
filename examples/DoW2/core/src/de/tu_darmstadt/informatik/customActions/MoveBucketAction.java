package de.tu_darmstadt.informatik.customActions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;

public class MoveBucketAction extends EEAAction {

	private Entity background;
	public MoveBucketAction(Entity backgroundEntity) {
		this.background = backgroundEntity;
	}

	@Override
	public boolean act(float delta) {	
		Vector2 v = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		background.getStage().screenToStageCoordinates(v);
		background.stageToLocalCoordinates(v);
		getEntity().setPosition(v.x, 50, 1);
		return true;
	}

}
