package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.StateBasedEntityManager;

/**
 * A ChangeStateInitAction is an action used to change the game to its initial
 * state, e.g. if the user has restarted the game
 * 
 * Example applications for this type of action include situations where the
 * user has won (or lost) the game or level, or has pressed a "new game" or
 * "restart" button or key.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class ChangeStateInitAction implements Action {

  /**
   * The internal state
   */
  private final int state;

  /**
   * Creates a new ChangeStateAction for the new initial state
   * 
   * @param newState
   *          the ID of the state to be assumed when this action is evaluated
   *          using the
   *          {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   *          method.
   */
  public ChangeStateInitAction(int newState) {
    state = newState;
  }

  /**
   * ChangeStateInitAction overrides the
   * {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   * method of the {@link eea.engine.action.Action} interface. It ensures that
   * the game enters the new state passed to the constructor and, if the game
   * was paused, resumes the game play.
   * 
   * More concretely, on receiving an update request, the following actions will
   * be taken to put the game state in a "clear state":
   * 
   * <ol>
   * <li>All entities are cleared from the target state,
   * "to get an empty playing field".</li>
   * <li>The list of recorded pressed "normal" keys is cleared.</li>
   * <li>The list of recorded pressed "control" keys is cleared.</li>
   * <li>The {@link org.newdawn.slick.GameContainer} is re-initialized.</li>
   * <li>If the game was paused, it is resumed.</li>
   * </ol>
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
    // enter the new "init" state
    sb.enterState(state);

    // clear the play area by removing all entities from the state
    StateBasedEntityManager.getInstance().clearEntitiesFromState(state);

    // try to clean up all recorded keys and then re-init the game
    try {
      gc.getInput().clearKeyPressedRecord();
      gc.getInput().clearControlPressedRecord();
      gc.getInput().clearMousePressedRecord();
//      gc.reinit();
      sb.init(gc);
    } catch (SlickException e) {
      e.printStackTrace();
    }

    // if the game was paused, resume it
    if (gc.isPaused())
      gc.resume();
  }
}
