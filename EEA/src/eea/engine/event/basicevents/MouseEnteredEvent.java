package eea.engine.event.basicevents;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

/**
 * This event represents cases where the mouse position matches the
 * area occupied by the entity owning this event
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class MouseEnteredEvent extends Event {

  /**
   * creates a new MouseEntered event
   */
  public MouseEnteredEvent() {
    super("MouseEnteredEvent");
  }

  /**
   * A MouseEnteredEvent will cause the action(s) associated with this event to
   * be performed if the mouse has entered the area containing the entity owning
   * this event. (More simply: if the mouse cursor is on the owning entity.)
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
    // retrieve the shape that represents the current state of the entity owning
    // this event
    /*
     * Vector2f position = this.getOwnerEntity().getPosition(); Vector2f size =
     * this.getOwnerEntity().getSize(); float rotation =
     * this.getOwnerEntity().getRotation();
     * 
     * Transform transform = Transform.createRotateTransform( (float)
     * Math.toRadians(rotation), position.x, position.y); Shape shape = (new
     * Rectangle(position.x - (size.x / 2), position.y - (size.y / 2), size.x,
     * size.y)).transform(transform);
     */
    Shape shape = getOwnerEntity().getShape();

    // determine the current mouse (x, y) position
    Vector2f mousePosition = new Vector2f(gc.getInput().getMouseX(), gc
        .getInput().getMouseY());

    // return if the current mouse position and the area covered by the shape
    // overlap
    return (shape.contains(mousePosition.x, mousePosition.y));
  }

}
