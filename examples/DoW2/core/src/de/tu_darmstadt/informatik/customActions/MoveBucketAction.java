package de.tu_darmstadt.informatik.customActions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu_darmstadt.informatik.eea.entity.Entity;

public class MoveBucketAction extends Action {

	private Entity background;
	private Entity bucket;

	public MoveBucketAction(Entity backgroundEntity, Entity bucket) {
		this.background = backgroundEntity;
		this.bucket = bucket;
	}

	@Override
	public boolean act(float delta) {	
		Vector2 v = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		background.getStage().screenToStageCoordinates(v);
		background.stageToLocalCoordinates(v);
		bucket.setPosition(v.x, 50, 1);
		return false;
	}

}
