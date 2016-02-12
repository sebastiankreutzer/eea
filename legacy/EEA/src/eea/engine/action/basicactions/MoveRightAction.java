package eea.engine.action.basicactions;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.action.Action;

/**
 * A MoveLeftAction is an action used to model a move of an entity to the right.
 * 
 * Note that the movement <em>speed</em> has to be passed to this method as a
 * constructor parameter, as this will determine the target location after a
 * single call of the @{link #update(GameContainer, StateBasedGame, int,
 * Component)} method.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class MoveRightAction extends Movement implements Action {

  /**
   * Creates a new MoveRightAction with the concrete movement speed passed in.
   * 
   * @param speed
   *          the current movement speed
   * @see eea.engine.action.basicactions.Movement
   * @see eea.engine.interfaces.IMovement
   */
  public MoveRightAction(float speed) {
    super(speed);
  }

  /**
   * This method determines the new position of the object, based on its current
   * position, movement speed, orientation, and time passed.
   * 
   * The displacement is determined by multiplying the speed with the time
   * passed (delta). The y position stays unchanged, while the x position is
   * updated by the displacement.
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

    // update the y position by the displacement
    pos.x += speed * delta;

    // return the new position
    return pos;
  }

}
