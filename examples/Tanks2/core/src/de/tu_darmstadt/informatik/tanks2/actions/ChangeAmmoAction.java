package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMinesAmmo;
import de.tu_darmstadt.informatik.tanks2.interfaces.IShootAmmo;

public class ChangeAmmoAction extends EEAAction {
	
	private int strength;
	
	public ChangeAmmoAction(int strength){
		this.strength = strength;
	}

	@Override
	public boolean act(float delta) {
		
		if(getTarget() instanceof IShootAmmo) {
			((IShootAmmo) getTarget()).changeShootAmmo(strength);
		}
		
		if(getTarget() instanceof IMinesAmmo) {
			((IMinesAmmo) getTarget()).changeMinesAmmo(strength/2);
		}
		
		return true;
	}

}
