package de.tu_darmstadt.gdi1.tanks.model.entities;

import eea.engine.entity.Entity;

public class Border extends Entity {

	public Border(String id) {
		super(id);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Border ");
		sb.append((int)this.getPosition().x);
		sb.append(" ");
		sb.append((int)this.getPosition().y);
		sb.append(" ");
		sb.append((int)this.getSize().x);
		sb.append(" ");
		sb.append((int)this.getSize().y);
		return sb.toString();
	}

}
