package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * A ChangeStateAction is an action used to change the game state, e.g. if the
 * user has paused the game
 * 
 * Example applications for this type of action include situations where the
 * user has paused the game or has activated menu operations that will also
 * pause the game
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class ChangeStateAction implements Action {

  /**
   * the target state when this action is performed
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
  public ChangeStateAction(int newState) {
    state = newState;
  }

  /**
   * ChangeStateAction overrides the
   * {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   * method of the {@link eea.engine.action.Action} interface. It ensures that
   * the game enters the new state passed to the constructor and, if the game
   * was paused, resumes the game play.
   * 
   * More concretely, on receiving an update request, the following actions will
   * be taken to put the game state in the "other state":
   * 
   * <ol>
   * <li>The game state is changed to the target state, as passed to the action
   * constructor. This is done using
   * {@link org.newdawn.slick.state.StateBasedGame#enterState(int)}.</li>
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
    // change to the target state
    sb.enterState(state);
    
    // if the game was pause, resume game play
    if (gc.isPaused())
      gc.resume();
  }

}
