package eea.engine.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The StateBasedEntityManager is responsible for updating entities based on the
 * current state of the game, as well as detecting collisions based on the
 * current game state.
 * 
 * Note that the programmer can <em>not</em> create a new instance of this
 * object. Instead, retrieve the current instance using the static method
 * {@link eea.engine.entity.StateBasedEntityManager#getInstance()}.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class StateBasedEntityManager {

  /**
   * the concrete entity manager used
   */
  private static final StateBasedEntityManager entityManager = new StateBasedEntityManager();

  /**
   * the mapping between the state (ID, type int) and the list of entities
   * contained in the state
   */
  private Map<Integer, List<Entity>>           entities;

  /**
   * returns the instance of the StateBasedEntityManager to be used
   * 
   * @return the instance of the StateBasedEntityManager to be used
   */
  public static StateBasedEntityManager getInstance() {
    return entityManager;
  }

  /**
   * Creates a new StateBasedEntityManager with an empty data set
   */
  private StateBasedEntityManager() {
    entities = new HashMap<Integer, List<Entity>>();
  }

  /**
   * adds the given entity to the selected state
   * 
   * @param state
   *          the state to which the entity shall be added
   * @param entity
   *          the entity to add
   */
  public void addEntity(int state, Entity entity) {
    entities.get(Integer.valueOf(state)).add(entity);
  }

  /**
   * adds a new state with the given state ID
   * 
   * @param stateID
   *          the ID for the new state
   */
  public void addState(int stateID) {
    entities.put(stateID, new CopyOnWriteArrayList<Entity>());
  }

  /**
   * clears the entities from all states
   */
  public void clearAllStates() {
    entities = new HashMap<Integer, List<Entity>>();
  }

  /**
   * clears all entities from the state passed in
   * 
   * @param stateID
   *          the ID of the state from which all entities shall be removed
   */
  public void clearEntitiesFromState(int stateID) {
    entities.get(Integer.valueOf(stateID)).clear();
  }

  /**
   * This method has been deprecated and is only provided for backward
   * compatibility. Use {@link #collides(int, Entity)} instead.
   * 
   * @see #collides(int, Entity)
   * 
   * @param gameState
   *          the target state of the game to examine
   * @param entity
   *          the entity to check for a possible collision
   * 
   * @return true if the current entity has collided with the parameter
   */
  @Deprecated
  public Entity colides(int gameState, Entity entity) {
    return collides(gameState, entity);
  }

  /**
   * determines the first entity with which the entity passed in currently
   * collides in the given gameState.
   * 
   * @param gameState
   *          the target state of the game to examine
   * @param entity
   *          the entity to check for a possible collision, e.g., the player
   *          figure or a bullet
   * 
   * @return true if "entity" has collided with an entity in state "gameState".
   *         Note that objects with the same ID can never "collide".
   */
  public Entity collides(int gameState, Entity entity) {
    // retrieve all entities from the list of entities
    List<Entity> candidates = entities.get(Integer.valueOf(gameState));
    // if there is such a state with entities...
    if (candidates == null || entity == null)
      return null; // cannot have a collision with "nothing"

    // ...then iterate over them...
    Iterator<Entity> iterator = candidates.iterator();
    while (iterator.hasNext()) {
      // ... and check each if they collide with "entity"
      Entity tempEntity = iterator.next();
      if (entity.collides(tempEntity))
        return tempEntity;

    }
    // no collision found, thus return null
    return null;
  }

  /**
   * returns a list of all entities with ID <em>stateID</em>
   * 
   * @param stateID
   *          the ID for which all associated entities are requested
   * 
   * @return a list of all entities (may be null) with the given ID
   */
  public List<Entity> getEntitiesByState(int stateID) {
    return entities.get(Integer.valueOf(stateID));
  }

  /**
   * returns the first entity with the given name for the selected state, if
   * such an entity exists. Otherwise, returns null.
   * 
   * @param state
   *          the state to be examined for the entity
   * @param name
   *          the name (ID) of the entity to look for
   * 
   * @return the first entity in state <code>state</code> that has the ID
   *         <code>name</code>, or <code>null</code> if there is no such element
   */
  public Entity getEntity(int state, String name) {
    List<Entity> stepEntities = entities.get(Integer.valueOf(state));
    if (stepEntities == null || name == null)
      return null; // stop here, cannot be successful!

    // retrieve the iterator for the selected state
    Iterator<Entity> iterator = stepEntities.iterator();

    // while there are more entities...
    while (iterator.hasNext()) {
      Entity entity = iterator.next();
      // if found, return it
      if (name.equals(entity.getID()))
        return entity;
    }

    // nothing found, return null
    return null;
  }

  /**
   * check if there is at least one entity in state <code>state</code> with a
   * name that starts with <code>prefix</code>
   * 
   * @param state
   *          the state to examine
   * @param prefix
   *          the prefix with which a given entity must start
   * 
   * @return true if there is at least one entity with the chosen ID prefix in
   *         the selected state
   */
  public boolean hasEntity(int state, String prefix) {
    List<Entity> chosen = entities.get(Integer.valueOf(state));
    if (prefix == null || chosen == null)
      return false; // cannot be successful!

    Iterator<Entity> iterator = chosen.iterator();
    // iterate over all candidates...
    while (iterator.hasNext()) {
      Entity entity = iterator.next();
      if (entity.getID().startsWith(prefix))
        // and return true once the first match is found
        return true;
    }

    // nothing found
    return false;
  }

  /**
   * removes the concrete entity from the selected state
   * 
   * @param state
   *          the chosen state
   * @param entity
   *          the entity to remove from the state
   */
  public void removeEntity(int state, Entity entity) {
    entities.get(Integer.valueOf(state)).remove(entity);
  }

  /**
   * causes all entities in the current game state to render themselves
   * 
   * Invokes the
   * {@link eea.engine.entity.Entity#render(GameContainer, StateBasedGame, Graphics)}
   * method on all entities in the current game state
   * 
   * @param gc
   *          the {@link org.newdawn.slick.GameContainer} object that handles
   *          the game loop, recording of the frame rate, and managing the input
   *          system
   * @param sb
   *          the {@link org.newdawn.slick.state.StateBasedGame} that isolates
   *          different stages of the game (e.g., menu, ingame, highscores etc.)
   *          into different states so they can be easily managed and
   *          maintained. This is queried for the current state ID using
   *          {@link org.newdawn.slick.state.StateBasedGame#getCurrentStateID()}
   *          .
   * @param graphicsContext
   *          the graphics context necessary for painting ("rendering") the
   *          component on the game container display
   */
  public void renderEntities(GameContainer gc, StateBasedGame sb,
      Graphics graphicsContext) {
    // retrieve all entities from the current game state
    List<Entity> candidates = entities.get(Integer.valueOf(sb
        .getCurrentStateID()));
    // if there is such a state with entities...
    if (candidates != null) {
      // ...then iterate over them...
      Iterator<Entity> iterator = candidates.iterator();
      while (iterator.hasNext()) {
        // ... and ask them to render themselves
        iterator.next().render(gc, sb, graphicsContext);
      }
    }
  }

  /**
   * assigns all targetEntities to the state associated with <em>stateID</em>
   * 
   * @param stateID
   *          the ID of a state
   * @param targetEntities
   *          the list of entities to be placed in the state referenced by
   *          <em>stateID</em>
   */
  public void setEntityListByState(int stateID, List<Entity> targetEntities) {
    entities.put(Integer.valueOf(stateID), targetEntities);
  }

  /**
   * updates all entities in the current game state
   * 
   * Invokes the
   * {@link eea.engine.entity.Entity#update(GameContainer, StateBasedGame, int)}
   * method on all entities in the current game state
   * 
   * @param gc
   *          the {@link org.newdawn.slick.GameContainer} object that handles
   *          the game loop, recording of the frame rate, and managing the input
   *          system
   * @param sb
   *          the {@link org.newdawn.slick.state.StateBasedGame} that isolates
   *          different stages of the game (e.g., menu, ingame, highscores etc.)
   *          into different states so they can be easily managed and
   *          maintained. This is queried for the current state ID using
   *          {@link org.newdawn.slick.state.StateBasedGame#getCurrentStateID()}
   *          .
   * @param delta
   *          the time passed in nanoseconds (ns) since the start of the event,
   *          used to interpolate the current target position
   */
  public void updateEntities(GameContainer gc, StateBasedGame sb, int delta) {
    // retrieve all entities from the current game state
    List<Entity> candidates = entities.get(Integer.valueOf(sb
        .getCurrentStateID()));
    // if there is such a state with entities...
    if (candidates != null) {
      // ...then iterate over them...
      Iterator<Entity> iterator = candidates.iterator();
      while (iterator.hasNext()) {
        // .. and them them to update themselves
        iterator.next().update(gc, sb, delta);
      }
    }
  }
}
