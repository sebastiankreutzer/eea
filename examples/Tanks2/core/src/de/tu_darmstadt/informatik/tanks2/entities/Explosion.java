package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.ISpeed;

/**
 * Eine Explosion ist eine Entity mit einer Zeitdauer.
 * 
 * @author jr
 *
 */
public class Explosion extends Entity implements ISpeed {

	private float duration;

	/**
	 * Erzeugt eine neue Explosion.
	 * 
	 * @param id
	 *            Die ID dieser Entity.
	 * @param duration
	 *            Die Zeitdauer der Explosion.
	 */
	public Explosion(String id, float duration) {
		super(id);
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Explosion ");
		sb.append((int) getWidth());
		sb.append(" ");
		sb.append((int) getHeight());
		sb.append(" ");
		sb.append((int) (duration));
		sb.append(" ");
		sb.append((int) this.getX());
		sb.append(" ");
		sb.append((int) this.getY());
		return sb.toString();
	}

	@Override
	public void setSpeed(float speed) {
		this.duration = speed;
	}

	@Override
	public float getSpeed() {
		return this.duration;
	}

}
