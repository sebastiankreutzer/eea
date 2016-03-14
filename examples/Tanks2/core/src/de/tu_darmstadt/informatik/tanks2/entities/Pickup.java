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

	public static enum PickupType {
		HEALTH("Health PickUp"), AMMUNITION("Ammunition PickUp");

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
		sb.append((int) this.getRotation());
		sb.append(" ");
		sb.append((int) (this.getScaleX() * 100));
		sb.append(" ");
		sb.append((int) this.getX());
		sb.append(" ");
		sb.append((int) this.getY());
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
