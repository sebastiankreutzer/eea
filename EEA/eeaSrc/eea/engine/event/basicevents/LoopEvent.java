package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * This special type of event ensures that the action(s) associated with the
 * event will <em>always</em> occur, thus providing an "endless loop":
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class LoopEvent extends Event {

  /**
   * creates a new loop event.
   */
  public LoopEvent() {
    super("LoopEvent");
  }

  /**
   * A LoopEvent makes sure that the action(s) associated with this event are
   * always performed by always returning "true". (Think of an infinite loop!)
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
    return true;
  }

}
