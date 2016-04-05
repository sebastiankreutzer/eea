package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.tanks2.interfaces.IMines;
import de.tu_darmstadt.informatik.tanks2.maps.Serializer;

/**
 * Ein Tank erweitert einen Tower um die Minen Funktionalitaet.
 * 
 * @author jr
 *
 */
public class Tank extends Tower implements  IMines {

	private int minesMaxAmmo; // maximal moegliche Anzahl an Minen
	private int minesAmmo; // aktuell verfuegbare Minen

	/**
	 * Erzeugt einen Tank mit allen spezifischen Feldern gleich 0.
	 * 
	 * @param id
	 *            Die ID dieses Tanks
	 * @param x
	 *            Die x Position
	 * @param y
	 *            Die y Position
	 * @param rotation
	 *            Die Rotation
	 * @param scale
	 *            Die Skalierung
	 */
	public Tank(String id, float x, float y, float rotation, float scale) {
		super(id);
		setPosition(x, y);
		setRotation(rotation);
		setScale(scale);
		
		minesAmmo = 0;
		minesMaxAmmo = 0;
	}

	public String toString() {
		return "Tank " + this.getID() + " " + Serializer.serialize(this);
	}

	@Override
	public void changeMinesAmmo(int value) {
		setMinesAmmo(minesAmmo + value);
	}

	@Override
	public void setMinesAmmo(int value) {
		if (value < 0) {
			minesAmmo = 0;
		} else if (value > minesMaxAmmo) {
			minesAmmo = minesMaxAmmo;
		} else {
			minesAmmo = value;
		}
	}

	@Override
	public boolean hasMinesLeft() {
		return minesAmmo > 0;
	}

	@Override
	public void setMaxMines(int value) {
		this.minesMaxAmmo = value;

	}

	@Override
	public int getMines() {
		return minesAmmo;
	}

	@Override
	public int getMaxMines() {
		return minesMaxAmmo;
	}

}
