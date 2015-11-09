package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * SetEntityPositionAction models the explicit placement of an entity to a given
 * spot. It can act as a "beam me to this position" effect, e.g., when an object
 * moves off to the left and shall reappear at the right border of the game
 * area.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class SetEntityPositionAction implements Action {

  /**
   * The internal target position
   */
  private Vector2f pos;

  /**
   * Creates a new SetEntityPositionAction with the target position passed in.
   * 
   * @param targetPos
   *          the target position for the entity to assume when
   *          {@link #update(GameContainer, StateBasedGame, int, Component)} is
   *          invoked.
   * @see eea.engine.interfaces.IMovement
   */
  public SetEntityPositionAction(Vector2f targetPos) {
    pos = targetPos;
  }

  /**
   * SetEntityPositionAction overrides the
   * {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   * method of the {@link eea.engine.action.Action} interface.
   * 
   * More concretely, this method simply sets the new position of the 
   * affected entity (as identified by
   * {@link eea.engine.component.Component#getOwnerEntity()}) to the target
   * position, as passed in to the constructor.
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
    
    // simply assign the stored target position
    event.getOwnerEntity().setPosition(new Vector2f(pos));
  }
}
