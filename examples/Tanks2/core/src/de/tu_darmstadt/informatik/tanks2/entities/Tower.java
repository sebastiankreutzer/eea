package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.ILife;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;
import de.tu_darmstadt.informatik.tanks2.interfaces.ISpeed;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;

/**
 * Ein Tower erweitert eine Entity um Funktionalitaeten wie Lebenspunkte,
 * Munition und andere Attribute.
 * 
 * @author jr
 *
 */
public class Tower extends Entity implements ILife, IAmmunition, IStrength, ISpeed {

	protected int maxLife;
	protected int life;

	protected int ammunitionCapacity;
	protected int ammunition;

	protected int strength;
	protected float speed;

	/**
	 * Erzeugt einen Tower mit allen spezifischen Feldern gleich 0.
	 * 
	 * @param id
	 *            Die ID dieses Towers
	 */
	public Tower(String id) {
		super(id);
		maxLife = 0;
		life = 0;
		ammunitionCapacity = 0;
		ammunition = 0;
		strength = 0;
		speed = 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Tower ");
		sb.append(this.maxLife);
		sb.append(" ");
		sb.append(this.life);
		sb.append(" ");
		sb.append(this.ammunitionCapacity);
		sb.append(" ");
		sb.append(this.ammunition);
		sb.append(" ");
		sb.append(this.strength);
		sb.append(" ");
		sb.append((int) speed);
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
	public void setMaxLife(int value) {
		this.maxLife = value;
	}

	@Override
	public int getMaxLife() {
		return maxLife;
	}

	@Override
	public void setLife(int value) {
		// Beschraenke den Wert auf 0 bis maximale Lebenspunkte
		if (value < 0) {
			life = 0;
		} else if (value > maxLife) {
			life = maxLife;
		} else {
			life = value;
		}
	}

	@Override
	public void changeLife(int value) {
		setLife(life + value);
	}

	@Override
	public int getActualLife() {
		return life;
	}

	@Override
	public boolean hasLifeLeft() {
		return life > 0;
	}

	@Override
	public void setMaxAmmunition(int value) {
		ammunitionCapacity = value;
	}

	@Override
	public int getMaxAmmunition() {
		return ammunitionCapacity;
	}

	@Override
	public void setAmmunition(int value) {
		if (value < 0) {
			ammunition = 0;
		} else if (value > ammunitionCapacity) {
			ammunition = ammunitionCapacity;
		} else {
			ammunition = value;
		}
	}

	@Override
	public void changeAmmunition(int value) {
		setAmmunition(ammunition + value);
	}

	@Override
	public int getAmmunition() {
		return ammunition;
	}

	@Override
	public boolean hasAmmunition() {
		return ammunition > 0;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public void changeStrength(int value) {
		strength += value;
		if (strength < 0) {
			strength = 0;
		}
	}

	@Override
	public int getStrength() {
		return strength;
	}

	@Override
	public void setSpeed(float speed) {
		this.speed = speed;

	}

	@Override
	public float getSpeed() {
		return speed;
	}

}
