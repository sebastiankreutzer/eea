package de.tu_darmstadt.informatik.customActions;

import de.tu_darmstadt.informatik.dow2.GameplayState;
import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.action.SoundAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;

public class DropBucketCollisionAction extends EEAAction{

	private Entity bucket;
	private GameplayState gameplayState;
	private IResourceManager resourcesManager;

	public DropBucketCollisionAction(Entity bucket, GameplayState gameplayState, IResourceManager resourcesManager) {
		this.bucket = bucket;
		this.gameplayState = gameplayState;
		this.resourcesManager = resourcesManager;
	}

	@Override
	public boolean act(float delta) {
		if ( getTarget() == bucket ){
			SoundAction fetchDropSound = new SoundAction("SlimeSplash.mp3", resourcesManager);
			getEntity().addAction(fetchDropSound);
			getEntity().addAction(new DestroyEntityAction());
			gameplayState.setScore(gameplayState.getScore() + 1);
			gameplayState.scoreRenderComponent.setText(gameplayState.getScore()+" Drops catched");
		}

		
		return true;
	}

}
