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
		
		// TODO Avoid doing too much casting
//		if(CollisionEvent.class.isInstance(event)){
//			CollisionEvent colider = (CollisionEvent) event;
//			Entity entity = colider.getCollidedEntity();
//			if(IShootAmmo.class.isInstance(entity) && IMinesAmmo.class.isInstance(entity)){
//				IShootAmmo shoot = (IShootAmmo) entity;
//				shoot.changeShootAmmo(strength);
//				
//				IMinesAmmo mines = (IMinesAmmo) entity;
//				mines.changeMinesAmmo((int)(strength/2));
//				
//				// TO DO Remove entity here or use a deleteEntityAction.
//				//StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), colider.getOwnerEntity());
//			}
//		}
//		return true;
	}

}
