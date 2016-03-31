package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMines;

/**
 * Ein Action zum Aendern der Anzahl der Minen eines IMineAmmo Objektes.
 * 
 * @author jr
 *
 */
public class ChangeMineAmmoAction extends EEAAction {

	private int amount;

	/**
	 * Erzeugt eine neue ChangeAmmoAction welche die Anzahl der Minen des Ziels
	 * veraendert.
	 * 
	 * @param amount
	 *            Die Aenderung der Anzahl der Minen
	 */
	public ChangeMineAmmoAction(int amount) {
		this.amount = amount;
	}

	@Override
	public boolean act(float delta) {
		// Aendere die Anzahl der Minen wenn moeglich
		if (target instanceof IMines) {
			((IMines) target).changeMinesAmmo(amount / 2);
		}

		return true;
	}
}
