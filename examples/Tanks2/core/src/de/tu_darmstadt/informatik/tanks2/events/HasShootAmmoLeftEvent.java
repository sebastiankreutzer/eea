package de.tu_darmstadt.informatik.tanks2.events;

import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.tanks2.interfaces.IShootAmmo;

public class HasShootAmmoLeftEvent extends EEAEvent {

	public HasShootAmmoLeftEvent() {
		super("HasShootAmmoLeftEvent");
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		if(IShootAmmo.class.isInstance(owner)){
			return ((IShootAmmo)owner).hasShootAmmo();
		}
		return false; 
	}

}
