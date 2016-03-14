package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;

/**
 * Eine Entity die einen Staerke Wert besitzt und zum parsen eines Schusses
 * dient.
 * 
 * @author jr
 *
 */
public class Shoot extends Entity implements IStrength {

	protected int damage;

	/**
	 * Erzeugt einen neuen Schuss
	 * 
	 * @param id
	 *            Die ID fuer diese Entity
	 * @param damage
	 *            Der Schaden dieses Schusses
	 */
	public Shoot(String id, int damage) {
		super(id);
		this.damage = damage;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Shot ");
		sb.append(damage);
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
		damage += value;
		if (damage < 0)
			damage = 0;
	}

	@Override
	public int getStrength() {
		return this.damage;
	}

	@Override
	public void setStrength(int streangth) {
		this.damage = streangth;
	}

}
