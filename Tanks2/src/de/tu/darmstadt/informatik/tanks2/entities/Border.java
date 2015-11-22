package de.tu.darmstadt.informatik.tanks2.entities;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public class Border extends Entity {

	public Border(String id) {
		super(id);
	}
	
	public String toString(){
		// TODO Check what coordinate system is returned.
		StringBuilder sb = new StringBuilder("Border ");
		sb.append((int)this.getX());
		sb.append(" ");
		sb.append((int)this.getY());
		sb.append(" ");
		sb.append((int)this.getWidth());
		sb.append(" ");
		sb.append((int)this.getHeight());
		return sb.toString();
	}

}
