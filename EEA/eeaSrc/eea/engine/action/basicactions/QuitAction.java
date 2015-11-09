package eea.engine.action.basicactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;

/**
 * A QuitAction is a simple but important action that is used inform the game
 * that the user has quit (finished/left) the game
 * 
 * This is typically the case when the user has pressed a certain button or key,
 * e.g. 'Q'.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class QuitAction implements Action {

  /**
   * QuitAction overrides the
   * {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
   * method of the {@link eea.engine.action.Action} interface.
   * 
   * For this very simple action, the {@link org.newdawn.slick.GameContainer} is
   * told that the user has quit the game, starting a clean shutdown of the
   * game.
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

    // Tell the GameContainer to exit the game by starting a clean
    // break-down of all components.
    gc.exit();
  }
}
