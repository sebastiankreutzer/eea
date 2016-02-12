package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * This event represents cases where the owning entity of the event is just in
 * the process of leaving (the displayable part of) the screen.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class LeavingScreenEvent extends Event {

  /**
   * creates a new LeavingScreen event instance
   */
  public LeavingScreenEvent() {
    super("LeavingScreenEvent");
  }

  /**
   * checks if the action(s) associated with this event shall be performed. This
   * is only the case if the entity owning this element is just leaving the
   * displayable part of the screen. More precisely, it occurs if the (x, y)
   * position is less than 0 or larger than the display width/height.
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
    // determine the current position of the entity owning this event
    Vector2f pos = owner.getPosition();

    // if x or y is negative, fire the event (above/to the left of the screen
    // area)
    if (pos.x < 0 || pos.y < 0) {
      return true;
    }

    // if the x or y coordinate of entity's center is larger than the screen's
    // width or height, fire the event (below/to the right of the screen area)
    if (pos.x > gc.getWidth() || pos.y > gc.getHeight()) {
      return true;
    }
    
    // otherwise, the component is still "on the screen"
    return false;
  }
}
