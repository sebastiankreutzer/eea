package de.tu_darmstadt.informatik.tanks2.interfaces;

/**
 * Ein Interface fuer Staerke Funktionalitaet.
 * 
 * @author jr
 *
 */
public interface IStrength {

	/**
	 * Setzte die Staerke
	 * 
	 * @param value
	 *            Der neue Staerke
	 */
	public void setStrength(int value);

	/**
	 * Aendert den Staerkewet
	 * 
	 * @param value
	 *            Die Aenderung
	 */
	public void changeStrength(int value);

	/**
	 * Gibt den Staerkewert zurueck
	 * 
	 * @return Den Staerkewert
	 */
	public int getStrength();

}
