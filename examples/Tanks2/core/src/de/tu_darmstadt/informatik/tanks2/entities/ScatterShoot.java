package de.tu_darmstadt.informatik.tanks2.entities;

/**
 * Eine spezielle Schuss Entity die nach einer bestimmten Zeit in fuenf kleinere
 * Geschosse zerfaellt.
 * 
 * @author jr
 *
 */
public class ScatterShoot extends Shoot {

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
	public ScatterShoot(String id, int damage, float fuseTime) {
		super(id, damage);
		this.fuseTime = fuseTime;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Scattershot ");
		sb.append(fuseTime);
		sb.append(super.toString().substring(4));
		return sb.toString();

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
