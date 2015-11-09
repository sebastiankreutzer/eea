package eea.engine.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A RenderComponent is an abstract component that is used as a base for all
 * game entities that can be rendered (shown on the game screen).
 * 
 * Note that it is not necessary to add "update" actions to this component, as
 * the "refresh" operation will already be performed on using the
 * RenderComponent.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public abstract class RenderComponent extends Component {

  /**
   * Creates a new RenderComponent with the given ID
   * 
   * @param id
   *          the target ID of the new RenderComponent
   */
  public RenderComponent(String id) {
    super(id);
  }

  /**
   * Returns the size of the component as a 2D floating point vector
   * 
   * @return the size of the RenderComponent as a 2D floating point vector using
   *         Slick's @{link org.newdawn.slick.geom.Vector2f}
   */
  public abstract Vector2f getSize();

  /**
   * The abstract method for rendering the current component based on the
   * GameContainer, the current state of the game, and the Graphics context.
   * 
   * @param gc
   *          the GameContainer object that handles the game loop, recording of
   *          the frame rate, and managing the input system
   * @param sb
   *          the StateBasedGame that isolates different stages of the game
   *          (e.g., menu, ingame, highscores etc.) into different states so
   *          they can be easily managed and maintained.
   * @param graphicsContext
   *          the graphics context necessary for painting ("rendering") the
   *          component on the game container display
   */
  public abstract void render(GameContainer gc, StateBasedGame sb,
      Graphics graphicsContext);
}
