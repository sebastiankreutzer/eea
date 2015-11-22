package de.tu.darmstadt.informatik.tanks2.entities;

import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;

public class Pickup extends Entity implements IStrength{
	
	public static enum PickUpType {HEALTH, AMMUNITION};
	
	private int streangth;

	public Pickup(PickUpType type) {
		super(typeToString(type));
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
		sb.append((int)(this.getScaleX() * 100));
		sb.append(" ");
		sb.append((int)this.getX());
		sb.append(" ");
		sb.append((int)this.getY());
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
	
	public static String typeToString(PickUpType type){
		switch (type) {
		case AMMUNITION:
			return "Ammunition PickUp";
			
		case HEALTH:
			return "Health PickUp";

		default:
			return "Unknown PickUp";
		}
	}

}
