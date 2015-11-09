package eea.engine.action;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.Component;

/**
 * An arbitrary number of individual actions can be added to a given event. When
 * the event occurs, all actions added to the event will be performed. Example
 * actions change the state of the underlying or affected object, exploding an
 * entity, or move/rotate/position the entity.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public interface Action {

  /**
   * Any action has to implement the update method. This method updates the
   * entities based on the underlying event, as determined using
   * event.getOwner()
   * 
   * @see org.newdawn.slick.GameContainer
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
   * @param event
   *          the event that the action is registered for
   */
  public abstract void update(GameContainer gc, StateBasedGame sb, int delta,
      Component event);
}
