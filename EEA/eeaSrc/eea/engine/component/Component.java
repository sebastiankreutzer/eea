package eea.engine.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;

/**
 * A <em>Component</em> is an abstract element that has a ID and an owner.
 * Components can be updated using the
 * {@link #update(GameContainer, StateBasedGame, int)} method.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public abstract class Component {

  /**
   * the internal ID of this component
   */
  protected String id;

  /**
   * the internal owner entity of this component
   */
  protected Entity owner;

  /**
   * Creates a new component with the given ID
   * 
   * @param componentID
   *          the ID of the component
   */
  public Component(String componentID) {
    id = componentID;
  }

  /**
   * returns the ID of this component
   * 
   * @return the ID of this component
   */
  public String getId() {
    return id;
  }

  /**
   * returns the entity that "owns" this component
   * 
   * @return the entity that "own" this component and is thus associated with
   *         it, as well as affected by events on the component.
   */
  public Entity getOwnerEntity() {
    return owner;
  }

  /**
   * Assigns the entity that "owns" this component
   * 
   * @param owningEntity
   *          the entity that "owns" the component, (which is associated with
   *          the component and thus affected by events thereon)
   */
  public void setOwnerEntity(Entity owningEntity) {
    owner = owningEntity;
  }

  /**
   * All components have to provide the update method. This method updates the
   * component based on GameContainer and the StateBasedGame instance that
   * represents the current game state
   * 
   * @param gc
   *          the {@link org.newdawn.slick.GameContainer} object that handles
   *          the game loop, recording of the frame rate, and managing the input
   *          system
   * @param sb
   *          the {@link org.newdawn.slick.state.StateBasedGame} that isolates
   *          different stages of the game (e.g., menu, ingame, highscores etc.)
   *          into different states so they can be easily managed and
   *          maintained.
   * @param delta
   *          the time passed in nanoseconds (ns) since the start of the event,
   *          used to interpolate the current target position
   */
  public abstract void update(GameContainer gc, StateBasedGame sb, int delta);
}
