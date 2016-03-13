package de.tu_darmstadt.informatik.tanks2.interfaces;

import java.util.List;

import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * Ein Interface fuer die Basisfunktionalitaeten einer Map.
 * @author jr
 *
 */
public interface IMap {
	
	/**
	 * Fuegt der Map eine Entity hinzu
	 * @param entity
	 * Die Entity
	 */
	public void addEntity(Entity entity);
	
	/**
	 * Entfernt eine Entity
	 * @param entity
	 * Die Entity
	 */
	public void removeEntity(Entity entity);
	
	/**
	 * Gibt eine Liste aller Entities dieser Map zurueck
	 * @return
	 * Die Liste aller Entities
	 */
	public List<Entity> getEntities();

}
