package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * This event represents the user has just released ("pressed") a key on the
 * keyboard
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class KeyPressedEvent extends Event {

  /**
   * the keycodes that were pressed
   */
  private final Integer[] keyList;

  /**
   * creates a new KeyPressed event that listens for the associated key codes
   * 
   * @param key
   *          the keys to listen for
   */
  public KeyPressedEvent(Integer... key) {
    super("KeyPressedEvent");
    keyList = key;
  }

  /**
   * checks if the action(s) associated with this event shall be performed. This
   * is only the case if (at least) one of the keys in the list of key codes
   * passed to the constructor has just been released ("pressed" - as opposed to
   * "is held down = down").
   * 
   * See also {@link eea.engine.event.basicevents.KeyDownEvent} for events that
   * focus on keys that are currently held down ("down"), but have not yet been
   * released.
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
    // check if all of the expected and monitored keys is currently "down"
    for (Integer k : keyList) {
      if (!gc.getInput().isKeyPressed(k))
        return false;
    }

    return true;
  }

}
