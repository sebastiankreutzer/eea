package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;

public class Shoot extends Entity implements IStrength {
	
	protected int strength;

	public Shoot(String id, int strength) {
		super(id);
		this.strength = strength;
		
	}

	public String toString(){
		StringBuilder sb = new StringBuilder("Shot ");
		sb.append(strength);
		sb.append(" ");
		sb.append((int)this.getRotation());
		sb.append(" ");
		sb.append((int)(this.getScaleX()*100));
		sb.append(" ");
		sb.append((int)this.getX());
		sb.append(" ");
		sb.append((int) this.getY());
		return sb.toString();
		
	}

	@Override
	public void changeStrength(int value) {
		strength += value;
		if(strength < 0) strength = 0;
	}

	@Override
	public int getStrength() {
		return this.strength;
	}

	@Override
	public void setStrength(int streangth) {
		this.strength = streangth;
	}

}
