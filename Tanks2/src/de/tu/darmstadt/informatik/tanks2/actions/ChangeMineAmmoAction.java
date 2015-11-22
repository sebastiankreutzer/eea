package de.tu.darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.tu.darmstadt.informatik.tanks2.interfaces.IMinesAmmo;

public class ChangeMineAmmoAction extends Action {
	
	private int value;

	public ChangeMineAmmoAction(int value){
		this.value = value;
	}
	
	@Override
	public boolean act(float delta) {
		if(IMinesAmmo.class.isInstance(getTarget())){
			IMinesAmmo m = (IMinesAmmo) getTarget();
			m.changeMinesAmmo(value);
		}
		return true;
	}

//	@Override
//	public void update(GameContainer gc, StateBasedGame sb, int delta,
//			Component event) {
//		Entity entity = event.getOwnerEntity();
//		if(IMinesAmmo.class.isInstance(entity)){
//			IMinesAmmo  ammo = (IMinesAmmo) entity;
//			ammo.changeMinesAmmo(value);
//		}
//
//	}

}
