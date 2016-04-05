package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;

/**
 * Eine spezielle Entity die entweder die Lebenspunkte oder Munition aendert.
 * 
 * @author jr
 *
 */
public class Pickup extends Entity implements IStrength {

	/**
	 * Bestimmt den Type eines Pickups.
	 * @author jr
	 *
	 */
	public static enum PickupType {
		HEALTH("Health"), AMMUNITION("Ammunition");

		private String pickupname;

		PickupType(String name) {
			pickupname = name;
		}

		public String toString() {
			return pickupname;
		}
	};

	private int strength;

	/**
	 * Erzeugt eine neue Pickup Entity.
	 * 
	 * @param type
	 *            Der {@link PickupType} der neuen Entity.
	 */
	public Pickup(PickupType type) {
		super(type.toString());
		strength = 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Pickup ");
		sb.append(this.getID());
		sb.append(" ");
		sb.append(strength);
		sb.append(" ");
		sb.append(getScaleX());
		sb.append(" ");
		sb.append(getX());
		sb.append(" ");
		sb.append(getY());
		return sb.toString();
	}

	@Override
	public void changeStrength(int value) {
		this.strength += value;

	}

	@Override
	public int getStrength() {
		return this.strength;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;

	}
}
