package de.tu_darmstadt.gdi1.tanks.model.entities;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IStreangth;
import eea.engine.entity.Entity;

public class Mine extends Entity implements IStreangth {
	
	private int strength;

	public Mine(String id, int streangth) {
		super(id);
		this.strength = streangth;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Mine ");
		sb.append(strength);
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
