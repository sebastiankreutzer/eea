package de.tu.darmstadt.informatik.tanks2.actions;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.tanks2.interfaces.IShootAmmo;

public class ChangeShootAmmoAction extends EEAAction {
	
	private int value;
	
	public ChangeShootAmmoAction(int value){
		this.value = value;
	}
	
	@Override
	public boolean act(float delta) {
		if(IShootAmmo.class.isInstance(getTarget())){
			IShootAmmo s = (IShootAmmo) getTarget();
			s.changeShootAmmo(value);
		}
		return true;
	}

//	@Override
//	public void update(GameContainer gc, StateBasedGame sb, int delta,
//			Component event) {
//		Entity entity = event.getOwnerEntity();
//		if(IShootAmmo.class.isInstance(entity)){
//			IShootAmmo  ammo = (IShootAmmo) entity;
//			ammo.changeShootAmmo(value);
//		}
//
//	}

}
