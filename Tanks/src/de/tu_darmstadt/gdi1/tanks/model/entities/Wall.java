package de.tu_darmstadt.gdi1.tanks.model.entities;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.ILife;
import eea.engine.entity.Entity;

public class Wall extends Entity implements ILife{
	public Wall(String id) {
		super(id);
		life = 0;
		maxLife = 0;
	}

	private int life;
	private int maxLife;

	public int getActualLife() {
		return life;
	}

	public void setLife(int life) {
		if(life > maxLife) this.life = maxLife;
		else this.life = life;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("Wall ");
		sb.append(this.maxLife);
		sb.append(" ");
		sb.append(this.maxLife);
		sb.append(" ");
		sb.append((int)this.getRotation());
		sb.append(" ");
		sb.append((int)(this.getScale()*100));
		sb.append(" ");
		sb.append((int)getPosition().x);
		sb.append(" ");
		sb.append((int)getPosition().y);
		return sb.toString();
	}

	@Override
	public void setMaxLife(int value) {
		this.maxLife = value;
		
	}

	@Override
	public void changeLife(int life) {
		this.life += life;
		if(this.life < 0) this.life = 0;
		else if(this.life > maxLife) this.life = maxLife;
		
	}

	@Override
	public boolean hasLifeLeft() {
		return life > 0;
	}

	@Override
	public int getMaxLife() {
		return maxLife;
	}

}
