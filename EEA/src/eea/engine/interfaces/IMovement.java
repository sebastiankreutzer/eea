package eea.engine.interfaces;

import org.newdawn.slick.geom.Vector2f;

/**
 * The IMovemement interface provides a method for determining the next position of
 * an object. The new position is determined based on the following information:
 * 
 * <ul>
 * <li>The object's <em>current</em> position</li>
 * <li>the movement speed of the entity, measured in "pixels per ns"</li>
 * <li>the current orientation of the object, measured in degrees</li>
 * <li>the time passed, measured in ns</li>
 * </ul>
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public interface IMovement {
  /**
   * This method determines the new position of the object, based on its current
   * position, movement speed, orientation, and time passed.
   * 
   * The displacement is determined by multiplying the speed with the time
   * passed (delta). The the new (x, y) position is then determined based on the
   * orientation and the displacement.
   * 
   * @param position
   *          the current position of the underlying object
   * @param speed
   *          the current movement speed, as passed to the constructor
   * @param rotation
   *          the current orientation of the object, as measured in degrees
   * @param delta
   *          the amount of time passed, used to determine how far the object
   *          has to move; more precisely: speed (in "pixels per ns") multiplied
   *          by delta ("time as measured in ns")
   */
  public Vector2f getNextPosition(final Vector2f position, float speed,
      float rotation, int delta);
}
