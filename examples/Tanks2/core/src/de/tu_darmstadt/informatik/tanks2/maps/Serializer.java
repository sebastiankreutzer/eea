package de.tu_darmstadt.informatik.tanks2.maps;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;
import de.tu_darmstadt.informatik.tanks2.interfaces.ILife;
import de.tu_darmstadt.informatik.tanks2.interfaces.IMines;
import de.tu_darmstadt.informatik.tanks2.interfaces.ISpeed;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;

public class Serializer {
	
	public static String serialize(Entity entity) {
		StringBuilder sb = new StringBuilder();
		sb.append(entity.getX());
		sb.append(" ");
		sb.append(entity.getY());
		sb.append(" ");
		sb.append(entity.getRotation());
		sb.append(" ");
		sb.append(entity.getScaleX());
		if(entity instanceof IStrength) {
			sb.append(" ");
			sb.append(((IStrength) entity).getStrength());
		}
		if(entity instanceof ISpeed) {
			sb.append(" ");
			sb.append(((ISpeed) entity).getSpeed());
		}
		if(entity instanceof ILife) {
			ILife life = (ILife) entity;
			sb.append(" ");
			sb.append(life.getLife());
			sb.append(" ");
			sb.append(life.getMaxLife());
		}
		if(entity instanceof IAmmunition) {
			IAmmunition ammo = (IAmmunition) entity;
			sb.append(" ");
			sb.append(ammo.getAmmunition());
			sb.append(" ");
			sb.append(ammo.getMaxAmmunition());
		}
		if(entity instanceof IMines) {
			IMines mines = (IMines) entity;
			sb.append(" ");
			sb.append(mines.getMines());
			sb.append(" ");
			sb.append(mines.getMaxMines());
		}
		return sb.toString();
	}

}
