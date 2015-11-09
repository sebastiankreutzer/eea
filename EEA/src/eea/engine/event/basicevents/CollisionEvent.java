package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;

/**
 * This event checks if the entity owning the event has collided with a
 * different entity
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class CollisionEvent extends Event {

  /**
   * the entity with which the owning entity for this event has collided
   */
  private Entity collidedEntity;

  /**
   * Creates a new collision event.
   */
  public CollisionEvent() {
    super("CollisionEvent");
  }

  /**
   * checks if the action(s) associated with this event shall be performed. In
   * this case, the action(s) are only performed if an collision between the
   * entity that own this event object and a different entity has taken place.
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
    // determine the first entity that has collided with the owner of the event
    Entity entity = StateBasedEntityManager.getInstance().collides(
        sb.getCurrentStateID(), owner);

    // if there is such an entity, store a reference and indicate the
    // willingness
    // to perform the action(s)
    if (entity != null) {
      collidedEntity = entity;
      return true;
    }

    // else, nothing is to be done
    return false;
  }

  /**
   * this method has been deprecated and is kept only for backward
   * compatibility. Please use {@link #getCollidedEntity()} instead.
   * 
   * @return the entity with which a collision took place, if any.
   */
  @Deprecated
  public Entity getColidedEntity() {
    return getCollidedEntity();
  }

  /**
   * returns the entity with which the entity owning this event has collided
   * 
   * @return the entity with which the entity owning this event has collided
   */
  public Entity getCollidedEntity() {
    return collidedEntity;
  }

}
