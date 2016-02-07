package de.tu_darmstadt.informatik.customActions;

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
		System.out.println(getTarget().getName());
		
		return true;
	}

}
