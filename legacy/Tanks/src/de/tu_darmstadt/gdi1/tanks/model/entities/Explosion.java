package de.tu_darmstadt.gdi1.tanks.model.entities;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.ISpeed;
import eea.engine.entity.Entity;

public class Explosion extends Entity implements ISpeed {

	private float width;
	private float height;
	private float speed;
	
	public Explosion(String id, float width , float height, float speed) {
		super(id);
		this.width = width;
		this.height = height;
		this.speed = speed;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Explosion ");
		sb.append((int)width);
		sb.append(" ");
		sb.append((int)height);
		sb.append(" ");
		sb.append((int)(speed * 100));
		sb.append(" ");
		sb.append((int)this.getPosition().x);
		sb.append(" ");
		sb.append((int)this.getPosition().y);
		return sb.toString();
	}

	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	@Override
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

}
