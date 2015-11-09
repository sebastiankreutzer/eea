/**
 * This package provides basic interfaces for the <em>eea</em> framework.
 * 
 * The {@link eea.engine.interfaces.IEntityFactory} provides a single method for
 * creating a new entity that has to be implemented by all entity factories.
 * 
 * The {@link eea.engine.interfaces.IMovement} interface provides a single method
 * {@link eea.engine.interfaces.IMovement#getNextPosition(org.newdawn.slick.geom.Vector2f, float, float, int)}
 * that determines the next position for the given entity, based on 
 * its current position, movement speed, orientation angle, and time passed.
 */
package eea.engine.interfaces;