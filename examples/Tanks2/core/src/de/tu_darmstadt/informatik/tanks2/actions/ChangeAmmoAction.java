package de.tu_darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMines;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;

/**
 * Eine Action zum Aendern, insbesondere Nachladen, von Munition und Minen.
 * 
 * @author jr
 *
 */
public class ChangeAmmoAction extends EEAAction {

	private int amount;

	/**
	 * Erzeugt eine neue ChangeAmmoAction welche fuer {@link IAmmunition}
	 * Implementationen die Munition und fuer {@link IMines} die Anzahl der
	 * Minen des Ziels veraendert. Die Minen werden dabei nur um den halben Wert
	 * veraender.
	 * 
	 * @param amount
	 *            Die Anzahl der Munition beziehungsweise Minen.
	 */
	public ChangeAmmoAction(int amount) {
		this.amount = amount;
	}

	@Override
	public boolean act(float delta) {
		// Aendere die Munition wenn moeglich
		if (target instanceof IAmmunition) {
			((IAmmunition) target).changeAmmunition(amount);
		}
		// Aendere die Anzahl der Minen wenn moeglich
		if (target instanceof IMines) {
			((IMines) target).changeMinesAmmo(amount / 2);
		}

		return true;
	}

}
