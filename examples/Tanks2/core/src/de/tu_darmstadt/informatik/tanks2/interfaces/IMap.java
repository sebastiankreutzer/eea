package de.tu_darmstadt.informatik.tanks2.interfaces;

import java.util.List;

import de.tu_darmstadt.informatik.eea.entity.Entity;

public interface IMap {
	
	public void addEntity(Entity entity);
	
	public void removeEntity(Entity entity);
	
	
	public String toString();
	
	public List<Entity> getEntities();
		

}
