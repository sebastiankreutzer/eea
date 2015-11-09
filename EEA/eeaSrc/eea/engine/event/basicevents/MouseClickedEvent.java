package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * This type of event will cause all associated action(s) to perform if the user
 * has clicked the <em>left mouse button</em>.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class MouseClickedEvent extends Event {

  /**
   * creates a new MouseClicked event
   */
  public MouseClickedEvent() {
    super("MouseClickedEvent");
  }

  /**
   * A MouseClickedEvent will cause the action(s) associated with this event to
   * be performed if the user has pressed the left mouse button.
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
    // check if the left mouse button is currently pressed - done.
    return gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);
  }
}
