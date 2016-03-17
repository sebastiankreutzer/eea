package de.tu_darmstadt.informatik.tanks2.interfaces;

/**
 * Ein Interface fuer Entities mit Lebenspunkten.
 * 
 * @author jr
 *
 */
public interface ILife {

	/**
	 * Setzte die Anzahl der maximale verfuegbaren Lebenspunkte.
	 * 
	 * @param value
	 *            Die Anzahl der maximalen Lebenspunkte.
	 */
	public void setMaxLife(int value);
	
	/**
	 * Gibt die maximal vorhandenen Lebenspunkte zurueck.
	 * 
	 * @return die maximalen Lebenspunkte.
	 */
	public int getMaxLife();

	/**
	 * Setzt die Anzahl der momentanen Lebenspunkte. Der Wert wird auf
	 * Validitaet ueberprueft.
	 * 
	 * @param value
	 *            Die Anzahl der Lebenspunkte.
	 */
	public void setLife(int value);

	/**
	 * Aendert die Anzahl der Lebenspunkte, ueberprueft das Ergebnis auf
	 * Validitaet.
	 * 
	 * @param value
	 *            Die Aenderung der Lebenspunkte.
	 */
	public void changeLife(int value);

	/**
	 * Gibt die momentane Anzahl der Lebenspunkte zurueck. Der Wert ist nicht
	 * kleiner als 0 und nicht groesser als die Anzahl der maximalen
	 * Lebenspunkte.
	 * 
	 * @return Die momentane Anzahl der Lebenspunkte.
	 */
	public int getLife();

	/**
	 * Gibt an ob die Anzahl der Lebenspunkte groesser 0 ist.
	 * 
	 * @return true wenn noch Lebenspunkte vorhandens sind, ansonsten false
	 */
	public boolean hasLifeLeft();

}
