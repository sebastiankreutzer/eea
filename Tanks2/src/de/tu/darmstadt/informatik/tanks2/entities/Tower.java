package de.tu.darmstadt.informatik.tanks2.entities;

import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.interfaces.ILife;
import de.tu.darmstadt.informatik.tanks2.interfaces.IShootAmmo;
import de.tu.darmstadt.informatik.tanks2.interfaces.ISpeed;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;

public class Tower extends Entity implements ILife, IShootAmmo, IStrength, ISpeed {
	
	private int maxLife;
	private int life;
	
	private int MaxShoots;
	private int shoots;
	
	private int streangth;
	private float speed;

	public Tower(String id) {
		super(id);
		maxLife = 0;
		life = 0;
		MaxShoots = 0;
		shoots = 0;
		streangth = 0;
		speed = 0;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Tower ");
		sb.append(this.maxLife);
		sb.append(" ");
		sb.append(this.life);
		sb.append(" ");
		sb.append(this.MaxShoots);
		sb.append(" ");
		sb.append(this.shoots);
		sb.append(" ");
		sb.append(this.streangth);
		sb.append(" ");
		sb.append((int)(this.speed*100));
		sb.append(" ");
		sb.append((int)this.getRotation());
		sb.append(" ");
		sb.append((int)(this.getScaleX() * 100));
		sb.append(" ");
		sb.append((int)this.getX());
		sb.append(" ");
		sb.append((int)this.getY());
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
	public int getActualLife() {
		return life;
	}

	@Override
	public boolean hasLifeLeft() {
		return life > 0;
	}

	@Override
	public int getMaxLife() {
		return maxLife;
	}

	@Override
	public void setShootMaxAmmo(int value) {
		MaxShoots = value;
	}

	@Override
	public void changeShootAmmo(int value) {
		shoots += value;
		if(shoots < 0) shoots = 0;
		else if (shoots > MaxShoots) shoots = MaxShoots;
		
	}

	@Override
	public void setShootAtmmo(int value) {
		if(value > MaxShoots)  shoots = MaxShoots;
		else shoots = value;
		
	}

	@Override
	public boolean hasShootAmmo() {
		return shoots > 0;
	}

	@Override
	public int getMaxShootAmmo() {
		return MaxShoots;
	}

	@Override
	public int getActualShootAmmo() {
		return shoots;
	}

	@Override
	public void changeStreangth(int value) {
		streangth += value;
		if(streangth < 0) streangth = 0;
		
	}

	@Override
	public int getStreangth() {
		return streangth;
	}

	@Override
	public void setStreangth(int streangth) {
		this.streangth = streangth;
		
	}

	@Override
	public void setLife(int value) {
		if(value > maxLife) this.life = maxLife;
		else this.life = value;
		
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
