package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * The RemoveEventAction is a straightforward action that is used to remove the
 * chosen event from the underlying entity.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */

public class RemoveEventAction implements Action {

  /**
   * RemoveEventAction overrides the
   * {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   * method of the {@link eea.engine.action.Action} interface. 
   * 
   * On performing the update, the event passed in is removed from the
   * underlying entity, so that it will no longer be notified of possible
   * instances of this event.
   * 
   * @param gc
   *          the {@link org.newdawn.slick.GameContainer} object that handles
   *          the game loop, recording of the frame rate, and managing the input
   *          system
   * 
   * @param sb
   *          the {@link org.newdawn.slick.state.StateBasedGame} that isolates
   *          different stages of the game (e.g., menu, in-game, high scores
   *          etc.) into different states so they can be easily managed and
   *          maintained.
   * 
   * @param delta
   *          the time passed in nanoseconds (ns) since the start of the event,
   *          used to interpolate the current target position
   * 
   * @param event
   *          the event that the action is registered for
   */
  @Override
  public void update(GameContainer gc, StateBasedGame sb, int delta,
      Component event) {
    // simply remove the chosen event from the entity, effectively ignoring it
    // for the remained of the game.
    event.getOwnerEntity().removeComponent(event);
  }
}
