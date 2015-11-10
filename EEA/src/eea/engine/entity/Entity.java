package eea.engine.entity;

import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.Component;
import eea.engine.component.RenderComponent;

/**
 * An Entity is a game element that can be shown on the game display.
 * 
 * @author Alexander Jandousek, Timo B&auml;hr
 * @version 1.0
 */
public class Entity {

  /**
   * the list of events that shall be informed of each update
   */
  private CopyOnWriteArrayList<Component> events;

  /**
   * the ID (acting also as the name) of this entity
   */
  private String                          id;

  /**
   * Whether this entity can be passed by other entities or not (e.g., a wall is
   * cannot be passed)
   */
  private boolean                         passable;

  /**
   * the position of this entity
   */
  private Vector2f                        position;

  /**
   * the current orientation of the entity in degrees
   */
  private float                           rotation;

  /**
   * the scaling factor for this entity (1 = normal size)
   */
  private float                           scale;

  /**
   * the size of this entity
   */
  private Vector2f                        size;

  /**
   * Whether this entity is visible to the player or not
   */
  private boolean                         visible;

  /**
   * the associated {@link eea.engine.component.render.RenderComponent} for
   * visualizing this entity (if it is visible)
   */
  private RenderComponent                 renderComponent;

  /**
   * Creates a new entity with the given ID.
   * 
   * The entity will be initialed as follows:
   * 
   * <ul>
   * <li>the position of the entity is (0, 0)</li>
   * <li>the scale factor is 1.0 (normal scale)</li>
   * <li>the default orientation is 0 (not rotated)</li>
   * <li>the entity is <em>visible</em></li>
   * <li>other elements can <em>pass</em> this object without a collision</li>
   * <li>there are no events to be informed if something "happens" to this
   * object, i.e., a call to the
   * {@link #update(GameContainer, StateBasedGame, int)} method will not have
   * any effect.</li>
   * </ul>
   * 
   * To reach a different behavior, use the "set*" methods of this class!
   * 
   * @param entityID
   *          the ID of this entity
   */
  public Entity(String entityID) {
    id = entityID;

    // initialize events to an empty list
    events = new CopyOnWriteArrayList<Component>();

    // initialize all other values to the default behavior
    position = new Vector2f(0, 0);
    scale = 1;
    rotation = 0;
    visible = true;
    passable = true;
  }

  /**
   * Adds a new event to this entity. If the event occurs, all associate actions
   * for this event will be evaluated.
   * 
   * @param event
   *          the concrete event to be added to this entity
   */
  public void addComponent(Component event) {
    // directly skip the rest if the event passed in is null
    if (event == null)
      return;

    // if event is a RenderComponent, then store this in the
    // renderComponent attribute
    if (event instanceof RenderComponent) // (RenderComponent.class.isInstance(event))
      renderComponent = (RenderComponent) event;

    // assign the associations: the entity owning "event" is "this";
    // "event" is a new event for this entity
    event.setOwnerEntity(this);
    events.add(event);
  }

  /**
   * Do not use this method; it has been deprecated. Use
   * {@link #collides(Entity)} instead.
   * 
   * @see #collides(Entity)
   * 
   * @param otherEntity
   *          the entity to be checked for a collision
   * 
   * @return true if a collission has occurred, else false
   */
  @Deprecated
  public boolean colides(Entity otherEntity) {
    return collides(otherEntity);
  }

  /**
   * check if the current entity collides with <em>otherEntity</em> The check is
   * performed by examining if the shapes intersect.
   * 
   * Note that collide(Entity) will always return <em>false</em> if either (or
   * both) of the two following conditions are met:
   * 
   * <ul>
   * <li>the current entity and the parameter have the same ID, as determined
   * using a <em>case-sensitive</em> comparison of the output of the
   * {@link #getID()} method for both objects</li>
   * <li>the system checks if an entity X collides with itself (as a special
   * case of the first condition)</li>
   * </ul>
   * 
   * This means that if your application assigns the same ID to multiple
   * objects, these object <em>cannot collide</em>. This may make sense (for
   * example) for shots that cannot "hit" or "cancel out" each other.
   * 
   * @param otherEntity
   *          a entity that can possible collide with this entity
   * @return true if the two objects have collided.
   */
  public boolean collides(Entity otherEntity) {
    // ensure that otherEntity is not null and that id is set,
    // as well as that both entities have a different ID
    if (otherEntity == null || (id != null && id.equals(otherEntity.getID())))
      return false;
    // if (this.id.equals(otherEntity.getID()))

    /*
     * Rectangle r1 = new Rectangle(position.x-getSize().x/2.0f,
     * position.y-getSize().y/2.0f, getSize().x, getSize().y); Rectangle r2 =
     * new Rectangle(OtherEntity.getPosition().x-OtherEntity.getSize().x/2.0f,
     * OtherEntity.getPosition().y-OtherEntity.getSize().y/2.0f,
     * OtherEntity.getSize().x, OtherEntity.getSize().y);
     * 
     * return r1.intersects(r2);
     */

    // return true if the shapes intersect
    return getShape().intersects(otherEntity.getShape());
  }

  /**
   * returns first the component (if any) with the given ID from the list of
   * associated components for this entity
   * 
   * @param id
   *          the name of the component we are looking for
   * @return the first component in the list of associated components that has
   *         ID id, or null if there is no such component
   */
  public Component getEvent(String id) {
    if (id == null)
      return null;
    // loop over all components
    for (Component comp : events) {
      // once the first component with the target ID is found, return it
      if (id.equalsIgnoreCase(comp.getId()))
        return comp;
    }

    // nothing found - return null
    return null;
  }

  /**
   * returns the ID of this entity
   * 
   * @return the ID of this entity
   */
  public String getID() {
    return id;
  }

  /**
   * returns the current position of this entity
   * 
   * @return the current position of this entity as a
   *         {@link org.newdawn.slick.geom.Vector2f}
   */
  public Vector2f getPosition() {
    return position;
  }

  /**
   * returns the current rotation angle for this entity
   * 
   * @return the current rotation angle for this entity as a float value, where
   *         0.0 indicates no rotation
   */
  public float getRotation() {
    return rotation;
  }

  /**
   * returns the current scaling factor for this entity
   * 
   * @return the current scaling factor for this entity as a float value, where
   *         1.0 indicates the normal scaling factor
   */
  public float getScale() {
    return scale;
  }

  /**
   * returns the {@link org.newdawn.slick.geom.Shape} for this object
   * 
   * @return a shape representing this element
   */
  public Shape getShape() {

    // create a new rectangle around the center of the shape (position
    // is the center) - thus, need to adapt (x, y) first to get the
    // "outer points".
    Rectangle rec = new Rectangle(position.x - getSize().x / 2.0f, position.y
        - getSize().y / 2.0f, getSize().x, getSize().y);

    // rotate the shape as used for the actual object
    Shape shape = rec.transform(Transform.createRotateTransform(
        (float) Math.toRadians(rotation), position.x, position.y));

    // return the result
    return shape;
  }

  /**
   * returns the size of this object, reusing a previously assigned size using
   * {@link #setSize(Vector2f)} if assigned. This method will never return
   * <code>null</code>.
   * 
   * @return a {@link org.newdawn.slick.geom.Vector2f} representing the size of
   *         this entity
   */
  public Vector2f getSize() {
    // if an explicit size was assigned, return this
    if (size != null)
      return size;

    // if the rendering component does not exist,
    // return a zero (but not null) size
    if (renderComponent == null)
      return new Vector2f(0, 0);

    // return the size as determined by the rendering component
    return renderComponent.getSize();
  }

  /**
   * Returns whether this entity can be passed by other entities
   * 
   * @return true if other entities can pass this entity
   */
  public boolean isPassable() {
    return passable;
  }

  /**
   * returns whether this entity is visible
   * 
   * @return true if the entity is visible, else false
   */
  public boolean isVisible() {
    return visible;
  }

  /**
   * removes a given component from this event, if it was included. Does nothing
   * if componentToRemove was not contained in the list of components.
   * 
   * @param componentToRemove
   *          the component to remove from the list of components for this
   *          entity
   */
  public void removeComponent(Component componentToRemove) {
    events.remove(componentToRemove);
  }

  /**
   * removes a given component from this event based on its ID, if it was
   * previously registered as a component. Does nothing if componentIDToRemove
   * was not contained in the list of components.
   * 
   * @param componentIDToRemove
   *          the ID of the component to remove from the list of components for
   *          this entity
   */
  public void removeComponent(String componentIDToRemove) {
    if (componentIDToRemove == null)
      return;

    // loop over all components
    for (int i = 0; i < events.size(); i++) {
      // if the component at position i has the correct ID, remove it
      if (componentIDToRemove.equalsIgnoreCase(events.get(i).getId()))
        events.remove(i);
    }
  }

  /**
   * Invokes the
   * {@link eea.engine.component.RenderComponent#render(GameContainer, StateBasedGame, Graphics)}
   * method on the rendering component for this entity, provided there is one
   * and that the current entity is visible (which by default it is).
   * 
   * Note that the rendering component has to be added using
   * {@link #addComponent(Component)} first!
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
   * @param graphicsContext
   *          the graphics context necessary for painting ("rendering") the
   *          component on the game container display
   */
  public void render(GameContainer gc, StateBasedGame sb,
      Graphics graphicsContext) {

    // act if the rendering component exists and the entity is visible
    if (renderComponent != null && isVisible()) {
      renderComponent.render(gc, sb, graphicsContext);
    }
  }

  /**
   * Sets whether other entities can pass this entity without "hitting" it and
   * thus causing a collision
   * 
   * @param canBePassed
   *          whether other entities can pass this entity without a collision
   */
  public void setPassable(boolean canBePassed) {
    passable = canBePassed;
  }

  /**
   * Sets a new position for this entity
   * 
   * @param newPosition
   *          the new position for this entity, coded as a
   *          {@link org.newdawn.slick.geom.Vector2f}
   */
  public void setPosition(Vector2f newPosition) {
    position = newPosition;
  }

  /**
   * Sets a new rotation angle for this entity
   * 
   * @param newAngle
   *          the new rotation angle for this entity, where 0.0 is the default
   *          rotation angle (no rotation)
   */
  public void setRotation(float newAngle) {
    rotation = newAngle;
  }

  /**
   * Sets a new scaling factor for this entity
   * 
   * @param scaleFactor
   *          the new scaling factor for this entity, where 1.0 is the normal
   *          scaling factor. Values less than 1 shrink the entity, while values
   *          greater than 1 let it grow.
   */
  public void setScale(float scaleFactor) {
    scale = scaleFactor;
  }

  /**
   * sets the size for this element using a 2D floating value vector
   * 
   * @param newSize
   *          the size as a {@link org.newdawn.slick.geom.Vector2f} instance
   */
  public void setSize(Vector2f newSize) {
    size = newSize;
  }

  /**
   * sets whether this entity is visible or not
   * 
   * @param isVisible
   *          if true, the entity is visible, else it is invisible
   */
  public void setVisible(boolean isVisible) {
    visible = isVisible;
  }

  /**
   * Invokes the
   * {@link eea.engine.component.Component#update(GameContainer, StateBasedGame, int)}
   * method on all components previously added to this component using
   * {@link #addComponent(Component)}
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
  public void update(GameContainer gc, StateBasedGame sb, int delta) {
    // loop over all components and ask them to update themselves
    for (Component event : events) {
      if (event != null)
        // ask the current event to update itself
        event.update(gc, sb, delta);
    }
  }
}
