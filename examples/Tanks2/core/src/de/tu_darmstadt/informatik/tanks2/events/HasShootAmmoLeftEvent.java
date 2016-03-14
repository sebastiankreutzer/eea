package de.tu_darmstadt.informatik.tanks2.events;

import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;

public class HasShootAmmoLeftEvent extends EEAEvent {

	public HasShootAmmoLeftEvent() {
		super("HasShootAmmoLeftEvent");
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		if(IAmmunition.class.isInstance(owner)){
			return ((IAmmunition)owner).hasAmmunition();
		}
		return false; 
	}

}
