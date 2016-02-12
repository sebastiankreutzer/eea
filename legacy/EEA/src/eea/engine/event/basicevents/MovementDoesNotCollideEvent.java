package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.interfaces.IMovement;

/**
 * This event represents cases where the movement of the entity owning the
 * entity does not collide with any other entity
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class MovementDoesNotCollideEvent extends Event {

  /**
   * the current movement speed
   */
  private float     speed;

  /**
   * the current movement instance needed to determine a possible collision
   */
  private IMovement move;

  /**
   * creates a new MovementDoesNotCollide event based on the current speed and
   * the representation of the movement.
   * 
   * @param currentSpeed
   *          the current movement speed
   * @param movement
   *          the current {@link eea.engine.interfaces.IMovement} instance
   *          representing the movement
   */
  public MovementDoesNotCollideEvent(float currentSpeed, IMovement movement) {
    super("MoveDoesntCollideEvent");
    speed = currentSpeed;
    move = movement;
  }

  /**
   * A MovementDoesNotCollideEvent will cause the action(s) associated with this
   * event to be performed if the movement does not cause a collision with
   * another entity.
   * 
   * @param gc
   *          the GameContainer object that handles the game loop, recording of
   *          the frame rate, and managing the input system
   * @param sb
   *          the StateBasedGame that isolates different stages of the game
   *          (e.g., menu, ingame, highscores etc.) into different states so
   *          they can be easily managed and maintained.
   * @param delta
   *          the time passed in nanoseconds (ns) since the start of the event,
   *          used to interpolate the current target position
   * 
   * @return true if the action(s) associated with this event shall be
   *         performed, else false
   */
  @Override
  protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {

    // note: we should NOT modify the position of the owning entity
    // here, since this is the job of the method
    // Event.update(GameContainer, StateBasedGame, int)
    // therefore, we create a "clone" here.
    
    // retrieve the next position to assume based on the movement and speed
    Vector2f position = move.getNextPosition(owner.getPosition(), speed,
        owner.getRotation(), delta);

    // create a new entity with the owner entity's ID,
    Entity entity = new Entity(this.getOwnerEntity().getID());
    // adjust its position to the determined position,
    entity.setPosition(position);
    // and make its size match the size of the owner entity
    entity.setSize(new Vector2f(this.getOwnerEntity().getSize()));

    // now, determine if there is a collision between this
    // "clone of the owning entity" and another object
    Entity collider = StateBasedEntityManager.getInstance().collides(
        sb.getCurrentStateID(), entity);

    // if this is not the case, or the colliding entity can be passed
    // without an effect, indicate that the movement can be performed.
    if (collider == null) {
      return true; // no collision => go ahead
    } else {
      return (collider.isPassable()); // collision "relevant" or not?
    }
  }
}
