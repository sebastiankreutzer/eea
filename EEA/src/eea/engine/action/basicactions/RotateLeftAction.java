package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.Component;

/**
 * A RotateLeftAction is an action used to model the rotation of an entity to
 * the left, more precisely: in counter-clockwise direction.
 * 
 * Note that the rotation <em>speed</em> has to be passed to this method as a
 * constructor parameter, as this will determine the target orientation after a
 * single call of the @{link #update(GameContainer, StateBasedGame, int,
 * Component)} method.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class RotateLeftAction extends Movement {

  /**
   * Creates a new RotateLeftAction with the concrete rotation speed passed in.
   * 
   * @param speed
   *          the current rotation speed
   * @see eea.engine.action.basicactions.Movement
   * @see eea.engine.interfaces.IMovement
   */
  public RotateLeftAction(float speed) {
    super(speed);
  }

  /**
   * RotateLeftAction overrides the
   * {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   * method of the {@link eea.engine.action.Action} interface.
   * 
   * More concretely, this method determines the next orientation of the
   * affected entity (as identified by
   * {@link eea.engine.component.Component#getOwnerEntity()}), based on its
   * current position, orientation, and rotation speed.
   * 
   * @see org.newdawn.slick.GameContainer
   * 
   * @param gc
   *          the GameContainer object that handles the game loop, recording of
   *          the frame rate, and managing the input system
   * 
   * @param sb
   *          the StateBasedGame that isolates different stages of the game
   *          (e.g., menu, ingame, highscores etc.) into different states so
   *          they can be easily managed and maintained.
   * 
   * @param delta
   *          the time passed in nanoseconds (ns) since the start of the event,
   *          used to interpolate the current target position where appropriate
   * 
   * @param event
   *          the event that the action is registered for
   */
  @Override
  public void update(GameContainer gc, StateBasedGame sb, int delta,
      Component event) {
    // retrieve the current rotation angle
    float rotation = event.getOwnerEntity().getRotation();

    // update the rotation angle by multiplying the "rotation speed" (measured
    // in "degrees per ns") by the time passed (measured in ns). Since this a rotation
    // to the left, decrease the angle accordingly
    rotation -= speed * delta;
    
    // ensure that the rotation is always in [0, 359.9999]
    rotation = (rotation + 360) % 360;

    // update the entity's orientation to the new value
    event.getOwnerEntity().setRotation(rotation);
  }

  /**
   * This method determines the new position of the object, based on its current
   * position, movement speed, orientation, and time passed.
   * 
   * <em>Since rotations happen based on the center of the shape, they do not change the 
   * position of the entity. Thus, this method will always return the position
   * passed in as the "new position".</em>
   * 
   * @param position
   *          the current position of the underlying object
   * @param speed
   *          the current rotation speed, as passed to the constructor
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
    return position;
  }
}
