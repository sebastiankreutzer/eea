package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.interfaces.IMovement;

/**
 * Movement models the movement of an entity based on the current speed
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public abstract class Movement implements Action, IMovement {

  /**
   * the current speed, needed to calculate the distance travelled based on how
   * much time has elapsed
   */
  protected float speed;

  /**
   * Creates a new Movement with the concrete movement speed passed in.
   * 
   * @param currentSpeed
   *          the current movement speed
   * @see eea.engine.interfaces.IMovement
   */
  public Movement(float currentSpeed) {
    speed = currentSpeed;
  }

  /**
   * Movement overrides the
   * {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   * method of the {@link eea.engine.action.Action} interface.
   * 
   * More concretely, this method determines the next (x, y) position of the
   * affected entity (as identified by
   * {@link eea.engine.component.Component#getOwnerEntity()}), based on its
   * current position, orientation, and movement speed.
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

    // determine the target position, based on the current position, movement
    // speed, orientation of the object, and time passed
    Vector2f position = getNextPosition(event.getOwnerEntity().getPosition(),
        speed, event.getOwnerEntity().getRotation(), delta);// event.getOwnerEntity().getPosition();

    // let the object assume the new position
    event.getOwnerEntity().setPosition(position);
  }
}
