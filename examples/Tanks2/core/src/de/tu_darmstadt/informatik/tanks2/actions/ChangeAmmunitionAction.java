package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.tanks2.interfaces.IShootAmmo;

/**
 * Ein Action zum Aendern den Munition eines IShootAmmo Objektes.
 * 
 * @author jr
 *
 */
public class ChangeAmmunitionAction extends EEAAction {

	private int amount;

	/**
	 * Erzeugt eine neue ChangeAmmoAction welche die Munition des Ziels
	 * veraendert.
	 * 
	 * @param amount
	 *            Die Aenderung der Munition
	 */
	public ChangeAmmunitionAction(int amount) {
		this.amount = amount;
	}

	@Override
	public boolean act(float delta) {
		// Aendere die Munition wenn moeglich
		if (target instanceof IShootAmmo) {
			((IShootAmmo) target).changeShootAmmo(amount);
		}

		return true;
	}
}
