package de.tu.darmstadt.informatik.tanks2.actions;

import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.tanks2.interfaces.IMinesAmmo;
import de.tu.darmstadt.informatik.tanks2.interfaces.IShootAmmo;

public class ChangeAmmoAction extends EEAAction {
	
	private int strength;
	
	public ChangeAmmoAction(int strength){
		this.strength = strength;
	}

	@Override
	public boolean act(float delta) {
		
		if(IShootAmmo.class.isInstance(getTarget()) && IMinesAmmo.class.isInstance(getTarget())){
			IShootAmmo s = (IShootAmmo) getTarget();
			s.changeShootAmmo(strength);
			
			IMinesAmmo m = (IMinesAmmo) getTarget();
			m.changeMinesAmmo((int) (strength/2));
		}
		
		return true;
	}

}
