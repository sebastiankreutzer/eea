package de.tu_darmstadt.gdi1.tanks.model.entities;


import de.tu_darmstadt.gdi1.tanks.model.interfaces.IStreangth;
import eea.engine.entity.Entity;

public class Shoot extends Entity implements IStreangth {
	
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
		sb.append((int)(this.getScale()*100));
		sb.append(" ");
		sb.append((int)this.getPosition().x);
		sb.append(" ");
		sb.append((int) this.getPosition().y);
		return sb.toString();
		
	}

	@Override
	public void changeStreangth(int value) {
		strength += value;
		if(strength < 0) strength = 0;
	}

	@Override
	public int getStreangth() {
		return this.strength;
	}

	@Override
	public void setStreangth(int streangth) {
		this.strength = streangth;
	}

}
