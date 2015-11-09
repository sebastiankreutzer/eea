package eea.engine.action.basicactions;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.interfaces.IMovement;

/**
 * A MoveBackwardAction is an action used to model a backwards move of an
 * entity.
 * 
 * Note that the movement <em>speed</em> has to be passed to this method as a
 * constructor parameter, as this will determine the target location after a
 * single call of the @{link #update(GameContainer, StateBasedGame, int,
 * Component)} method.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class MoveBackwardAction extends Movement implements IMovement {

  /**
   * Creates a new MoveBackwardAction with the concrete movement speed passed
   * in.
   * 
   * @param speed
   *          the current movement speed
   * @see eea.engine.action.basicactions.Movement
   * @see eea.engine.interfaces.IMovement
   */
  public MoveBackwardAction(float speed) {
    super(speed);
  }

  /**
   * This method determines the new position of the object, based on its current
   * position, movement speed, orientation, and time passed.
   * 
   * The displacement is determined by multiplying the speed with the time
   * passed (delta). The new (x, y) position is then determined based on the
   * orientation and the displacement.
   * 
   * @param position
   *          the current position of the underlying object
   * @param speed
   *          the current movement speed, as passed to the constructor
   * @param rotation
   *          the current orientation of the object
   * @param delta
   *          the amount of time passed, used to determine how far the object
   *          has to move; more precisely: speed (in "pixels per ns") multiplied
   *          by delta ("time as measured in ns")
   */
  @Override
  public Vector2f getNextPosition(Vector2f position, float speed,
      float rotation, int delta) {

    // retrieve the current position (x, y)
    Vector2f pos = new Vector2f(position);

    // determine the displacement: speed ("pixels/ns") multiplied by time
    // elapsed ("ns")
    float hip = speed * delta;

    // adjust the position based on the current orientation
    pos.x -= hip * java.lang.Math.sin(java.lang.Math.toRadians(rotation));
    pos.y += hip * java.lang.Math.cos(java.lang.Math.toRadians(rotation));

    // return the new position
    return pos;
  }
}
