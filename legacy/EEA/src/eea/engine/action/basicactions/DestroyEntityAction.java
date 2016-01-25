package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

/**
 * A DestroyEntityAction is used to "destroy" and entity by removing it from the
 * list of active entities in the
 * {@link eea.engine.entity.StateBasedEntityManager}. Thus, no further events
 * and associated events will be happen on a destroyed entity. It does
 * <em>not</em> animate the "destroying" itself.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class DestroyEntityAction implements Action {

  /**
   * DestroyEntityAction overrides the
   * {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   * method of the {@link eea.engine.action.Action} interface. It ensures that
   * the entity owning the event passed in will be deleted from the entity
   * manager.
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
   *          used to interpolate the current target position
   * 
   * @param event
   *          the event that the action is registered for. The owner of the
   *          event (i.e., the entity that was "destroyed") is determined and
   *          then removed from the list of entities.
   */
  @Override
  public void update(GameContainer gc, StateBasedGame sb, int delta,
      Component event) {
    // drop the owner of the event from the list of current entities.
    StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(),
        event.getOwnerEntity());
  }
}
