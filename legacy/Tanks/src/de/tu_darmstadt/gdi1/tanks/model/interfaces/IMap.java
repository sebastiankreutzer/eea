package de.tu_darmstadt.gdi1.tanks.model.interfaces;

import java.util.List;

import eea.engine.entity.Entity;

public interface IMap {
	
	public void addEntity(Entity entity);
	
	public void removeEntity(Entity entity);
	
	
	public String toString();
	
	public List<Entity> getEntities();
		

}
