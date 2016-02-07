package de.tu_darmstadt.informatik.customActions;

import com.badlogic.gdx.scenes.scene2d.actions.RemoveAction;

import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;

public class DropBucketCollisionAction extends EEAAction{

	private Entity bucket;

	public DropBucketCollisionAction(Entity bucket) {
		this.bucket = bucket;
	}

	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if ( getTarget() == bucket ){
			getEntity().addAction(new DestroyEntityAction());
		}		
		return true;
	}

}
