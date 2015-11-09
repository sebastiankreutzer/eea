package eea.engine.action.basicactions;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.action.Action;

/**
 * A MoveDownAction is an action used to model a downwards move of an entity.
 * 
 * Note that the movement <em>speed</em> has to be passed to this method as a
 * constructor parameter, as this will determine the target location after a
 * single call of the @{link #update(GameContainer, StateBasedGame, int,
 * Component)} method.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class MoveDownAction extends Movement implements Action {

  /**
   * Creates a new MoveDownAction with the concrete movement speed passed
   * in.
   * 
   * @param speed
   *          the current movement speed
   * @see eea.engine.action.basicactions.Movement
   * @see eea.engine.interfaces.IMovement
   */
  public MoveDownAction(float speed) {
    super(speed);
  }


  /**
   * This method determines the new position of the object, based on its current
   * position, movement speed, orientation, and time passed.
   * 
   * The displacement is determined by multiplying the speed with the time
   * passed (delta). The x position stays unchanged, while the y position is
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
    pos.y += speed * delta;
    
    // return the new position
    return pos;
  }

}
