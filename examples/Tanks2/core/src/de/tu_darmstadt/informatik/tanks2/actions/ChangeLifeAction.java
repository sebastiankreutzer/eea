package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.tanks2.interfaces.ILife;

/**
 * Eine Action welche die Lebenspunkte eines Ziels aendert.
 * 
 * @author jr
 *
 */
public class ChangeLifeAction extends EEAAction {

	private int amount;

	/**
	 * Erzeugt eine neue ChangeLifeAction welche die Lebenspunkte des Ziels
	 * veraendert.
	 * 
	 * @param amount
	 *            Die Aenderung der Lebenspunkte.
	 */
	public ChangeLifeAction(int amount) {
		this.amount = amount;
	}

	@Override
	public boolean act(float delta) {
		if (getTarget() instanceof ILife) {
			((ILife) getTarget()).changeLife(amount);
		}
		return true;
	}
}
