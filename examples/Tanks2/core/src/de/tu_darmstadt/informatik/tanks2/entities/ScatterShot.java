package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.tanks2.maps.Serializer;

/**
 * Eine spezielle Schuss Entity die nach einer bestimmten Zeit in fuenf kleinere
 * Geschosse zerfaellt.
 * 
 * @author jr
 *
 */
public class ScatterShot extends SimpleShot {

	protected float fuseTime;

	/**
	 * Erzeugt eine neuen ScatterShot
	 * 
	 * @param id
	 *            Die ID fuer diese Entity
	 * @param damage
	 *            Der Gesamtschaden dieses Schusses
	 * @param fuseTime
	 *            Die Zeit bis der Schuss zerfaellt
	 */
	public ScatterShot(String id, int damage, float fuseTime) {
		super(id, damage);
		this.fuseTime = fuseTime;
	}

	@Override
	public String toString() {
		return "Scattershot " + Serializer.serialize(this) + " " + fuseTime;
	}

	/**
	 * Gibt die eingestellte Zeit ein, nach welcher der Schuss zerfaellt.
	 * 
	 * @return Die Zuendzeit
	 */
	public float getFuseTime() {
		return fuseTime;
	}
}
