package eea.engine.interfaces;

import eea.engine.entity.Entity;

/**
 * Basic interface for all entity factories, providing only the 
 * {@link #createEntity()} method that each entity factor must provide.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public interface IEntityFactory {
	
  /**
   * This method is used to create a new Entity ("factory method")
   * 
   * @return the newly create entity
   */
	public Entity createEntity();
}
