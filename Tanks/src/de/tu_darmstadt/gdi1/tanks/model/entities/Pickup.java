package de.tu_darmstadt.gdi1.tanks.model.entities;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IStreangth;
import eea.engine.entity.Entity;

public class Pickup extends Entity implements IStreangth{
	
	private int streangth;

	public Pickup(String id) {
		super(id);
		streangth = 0;
	}

	
	public String toString(){
		StringBuilder sb = new StringBuilder("Pickup ");
		sb.append(this.getID());
		sb.append(" ");
		sb.append(streangth);
		sb.append(" ");
		sb.append((int)this.getRotation());
		sb.append(" ");
		sb.append((int)(this.getScale() * 100));
		sb.append(" ");
		sb.append((int)this.getPosition().x);
		sb.append(" ");
		sb.append((int)this.getPosition().y);
		return sb.toString();
	}

	@Override
	public void changeStreangth(int value) {
		this.streangth += value;
		
	}

	@Override
	public int getStreangth() {
		return this.streangth;
	}

	@Override
	public void setStreangth(int streangth) {
		this.streangth = streangth;
		
	}

}
