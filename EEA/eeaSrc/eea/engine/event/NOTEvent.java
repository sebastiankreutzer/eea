package eea.engine.event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;

/**
 * Responsible for the negation of an {@link Event}. I.e. a wrapper class for
 * {@link Event}s. This class evaluates the negation of the
 * {@link #performAction(GameContainer, StateBasedGame, int)} method in its own
 * respective method.
 * 
 * @version 1/1/2014
 * 
 * @author Thomas Hesse
 */
public class NOTEvent extends Event {

  private Event event;

  /**
   * Create a new {@link NOTEvent} which will negate the given {@link Event}.
   * 
   * @param event
   *            - the event to negate
   */
  public NOTEvent(Event event) {
    super("NOTEvent");
    this.event = event;
  }

  /**
   * Negates the outcome of the given {@link Event}. <BR />
   * <BR />
   * 
   * {@inheritDoc}
   */
  @Override
  protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
    return !(this.event.performAction(gc, sb, delta));
  }

  /**
   * Assign the owning entity for this {@link Event}.
   * 
   * @param owner
   *            - the owner for this event
   */
  public void setOwnerEntity(Entity owner) {
    super.setOwnerEntity(owner);
    this.event.setOwnerEntity(owner);
  }

}
