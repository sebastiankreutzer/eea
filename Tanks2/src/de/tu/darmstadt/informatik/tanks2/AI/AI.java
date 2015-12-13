package de.tu.darmstadt.informatik.tanks2.AI;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.action.RotateAction;
import de.tu.darmstadt.informatik.eea.entity.EEAComponent;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.entities.Tank;
import de.tu.darmstadt.informatik.tanks2.interfaces.IArtificialIntelligence;
import de.tu.darmstadt.informatik.tanks2.interfaces.IShootAmmo;
import de.tu.darmstadt.informatik.tanks2.interfaces.ISpeed;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;
import temp.removeASAP.Tanks;

public abstract class AI extends EEAComponent implements IArtificialIntelligence {
	
	protected String nameTarget;
	protected Entity target;

	protected float speed = 0, strength = 0;

	public AI(String componentID, String target) {
		super(componentID);
		this.nameTarget = target;
	}
	
	@Override
	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
		if(owner instanceof ISpeed) speed = ((ISpeed) owner).getSpeed();
		if(owner instanceof IStrength) strength = ((IStrength) owner).getStrength();
	}

	@Override
	public void update(float delta) {
		Action a = getNextAction();
		a.setActor(owner);
		a.act(delta);
	}
	
	@Override
	public Action getNextAction() {
		if(target == null && !findTarget()) return null;
		return calculateNextMove();
	}
	
	protected abstract EEAAction calculateNextMove();
	
	protected boolean findTarget(){
		target = owner.getManager().getEntity(Tanks.player1);
		if(target != null) return true;
		return false;
	}
	
	protected RotateAction determineRotateAction(float deltaRotation){
		if(deltaRotation < 180) return new RotateAction(-speed);
		else return new RotateAction(speed);		
	}
	
	protected float calculateDeltaRotation() {
		float rotationToTarget = (float) Math.toDegrees(
				Math.atan2(owner.getY() - target.getY(), owner.getX() - target.getX())
				) + 90;
		return ((owner.getRotation() - rotationToTarget) % 360 + 360) % 360;
	}

}
