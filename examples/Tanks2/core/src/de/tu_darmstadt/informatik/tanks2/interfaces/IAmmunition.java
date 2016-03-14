package de.tu_darmstadt.informatik.tanks2.interfaces;

public interface IAmmunition {

	/**
	 * Setzt die maximale Anzahl an gelagerter Munition.
	 * 
	 * @param value
	 *            Die neue Lagerkapazitaet
	 */
	public void setMaxAmmunition(int value);

	/**
	 * Gibt die Anzahl der maximal lagerbaren Munition zurueck.
	 * 
	 * @return Die Lagerkapazitaer fuer Munition
	 */
	public int getMaxAmmunition();

	/**
	 * Setzt die momentane Anzahl der auf den neuen Wert. Wenn der Wert kleiner
	 * 0 oder groesser als die maximale Kapazitaet ist wird der entsprechend
	 * korrigiert.
	 * 
	 * @param value
	 *            Die neue Anzahl an Munition.
	 */
	public void setAmmunition(int value);

	/**
	 * Aendert die Anzahl der momentanen Munition. Die Anzahl der Munition wir
	 * auf Validitaet geprueft.
	 * 
	 * @param value
	 *            Die Aenderung der Munition.
	 */
	public void changeAmmunition(int value);

	/**
	 * Gibt die Anzahl der momentanen Munition zurueck.
	 * 
	 * @return Die Anzahl der Munition, ist niemals negativ
	 */
	public int getAmmunition();

	/**
	 * Gibt an ob noch Munition vorhanden ist.
	 * 
	 * @return true wenn mehr als 0 Munition vorhanden, ansonsten false
	 */
	public boolean hasAmmunition();

}