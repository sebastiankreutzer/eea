package de.tu_darmstadt.informatik.tanks2.events;

import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMines;

/**
 * Ein Event zum Ueberpruefen ob eine Entity die das IMines Interface implementiert noch Minen hat.
 * @author jr
 *
 */
public class HasMinesLeftEvent extends EEAEvent {
	
	IMines mines;

	/**
	 * Erstellt eine neues HasMinesLeftEvent.
	 * @param mines Die zu beobachtende IMines Entity.
	 */
	public HasMinesLeftEvent(IMines mines) {
		super("HasMinesLeftEvent");
		this.mines = mines;
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		return mines.hasMinesLeft();
	}

}
