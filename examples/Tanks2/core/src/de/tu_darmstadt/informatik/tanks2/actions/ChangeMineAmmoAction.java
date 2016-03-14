package de.tu_darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMinesAmmo;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;

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
		if (target instanceof IMinesAmmo) {
			((IMinesAmmo) target).changeMinesAmmo(amount / 2);
		}

		return true;
	}
}
