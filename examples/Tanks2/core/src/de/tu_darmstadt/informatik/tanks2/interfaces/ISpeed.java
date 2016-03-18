package de.tu_darmstadt.informatik.tanks2.interfaces;

/**
 * Ein Interface fuer Geschwindigkeits Funktionalitaet.
 * 
 * @author jr
 *
 */
public interface ISpeed {

	/**
	 * Setzt die Geschwindigkeit auf einen neuen Wert.
	 * 
	 * @param value
	 *            Der neue Wert
	 */
	public void setSpeed(float value);

	/**
	 * Gibt den Wert der Geschwindigkeit zurueck.
	 * 
	 * @return Die Geschwindigkeit
	 */
	public float getSpeed();

}
