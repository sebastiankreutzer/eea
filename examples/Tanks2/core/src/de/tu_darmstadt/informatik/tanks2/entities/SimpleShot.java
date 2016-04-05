package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;
import de.tu_darmstadt.informatik.tanks2.maps.Serializer;

/**
 * Eine Entity die einen Staerke Wert besitzt und zum parsen eines Schusses
 * dient.
 * 
 * @author jr
 *
 */
public class SimpleShot extends Entity implements IStrength {
	
	public static final String ID = "Shot";
	
	protected static int counter = 0;

	protected int damage;

	/**
	 * Erzeugt einen neuen Schuss
	 * 
	 * @param id
	 *            Die ID fuer diese Entity
	 * @param damage
	 *            Der Schaden dieses Schusses
	 */
	public SimpleShot(String id, int damage) {
		super(ID + id + counter++);
		this.damage = damage;
	}

	public String toString() {
		return ID + " " + Serializer.serialize(this);
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
