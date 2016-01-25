package de.tu_darmstadt.informatik.tanks2.entities;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;

public class Pickup extends Entity implements IStrength {

	public static enum PickUpType {
		HEALTH, AMMUNITION
	};

	private int strength;

	public Pickup(PickUpType type) {
		super(typeToString(type));
		strength = 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Pickup ");
		sb.append(this.getID());
		sb.append(" ");
		sb.append(strength);
		sb.append(" ");
		sb.append((int) this.getRotation());
		sb.append(" ");
		sb.append((int) (this.getScaleX() * 100));
		sb.append(" ");
		sb.append((int) this.getX());
		sb.append(" ");
		sb.append((int) this.getY());
		return sb.toString();
	}

	@Override
	public void changeStrength(int value) {
		this.strength += value;

	}

	@Override
	public int getStrength() {
		return this.strength;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;

	}

	public static String typeToString(PickUpType type) {
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
