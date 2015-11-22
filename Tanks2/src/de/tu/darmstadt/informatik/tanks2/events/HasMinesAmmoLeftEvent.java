package de.tu.darmstadt.informatik.tanks2.events;

import de.tu.darmstadt.informatik.eea.event.EEAEvent;
import de.tu.darmstadt.informatik.tanks2.interfaces.IMinesAmmo;

public class HasMinesAmmoLeftEvent extends EEAEvent {

	public HasMinesAmmoLeftEvent() {
		super("HasMinesAmmoLeftEvent");
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		if(IMinesAmmo.class.isInstance(owner)){
			return ((IMinesAmmo)owner).hasMinesLeft();
		}
		return false; 
	}

}
