package de.tu_darmstadt.informatik.tanks2.events;

import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;

/**
 * Ein Event zum Ueberpruefen ob eine Entity die das IAmmunition Interface
 * implementiert noch Munition uebrig hat.
 * 
 * @author jr
 *
 */
public class HasAmmunitionLeftEvent extends EEAEvent {

	IAmmunition ammunition;

	/**
	 * Erzeugt eine neue HasAmmunitionLeftEvent
	 * 
	 * @param ammunition
	 *            Die zu beobachtende IAmmuntion Implementierung.
	 */
	public HasAmmunitionLeftEvent(IAmmunition ammunition) {
		super("HasShootAmmoLeftEvent");
		this.ammunition = ammunition;
	}

	@Override
	public boolean eventTriggered(float delta) {
		return ammunition.hasAmmunition();
	}

}
