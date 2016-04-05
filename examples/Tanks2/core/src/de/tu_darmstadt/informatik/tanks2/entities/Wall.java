package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.ILife;

/**
 * Ein Wall stellt eine stationaere Entity mit Lebenspunkten dar.
 * 
 * @author jr
 *
 */
public class Wall extends Entity implements ILife {

	private int life;
	private int maxLife;

	/**
	 * Erzeugt eine neue Wall Entity ohne Lebenspunkte.
	 * 
	 * @param id
	 */
	public Wall(String id) {
		super(id);
		life = 0;
		maxLife = 0;
	}

	@Override
	public void setMaxLife(int value) {
		this.maxLife = value;

	}

	@Override
	public int getMaxLife() {
		return maxLife;
	}

	@Override
	public void setLife(int value) {
		this.life = value;
		if (life < 0) {
			life = 0;
		} else if (life > maxLife) {
			this.life = maxLife;
		}
	}

	@Override
	public void changeLife(int value) {
		setLife(life + value);

	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public boolean hasLifeLeft() {
		return life > 0;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Wall ");
		sb.append((int) getX());
		sb.append(" ");
		sb.append((int) getY());
		sb.append(" ");
		sb.append(this.maxLife);
		sb.append(" ");
		sb.append(this.maxLife);
		sb.append(" ");
		sb.append((int) this.getRotation());
		sb.append(" ");
		sb.append(getScaleX());
		return sb.toString();
	}

}
