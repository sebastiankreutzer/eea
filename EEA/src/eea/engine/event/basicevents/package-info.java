/**
 * This package provides a set of basic events for use in 
 * the <em>eea</em> framework.
 * 
 * More precisely, the following events are provided, all of which
 * override the inherited {@link eea.engine.event.Event#performAction(GameContainer, StateBasedGame, int)}
 * method of class {@link eea.engine.event.Event}, and base their decision
 * on the entity owning the concrete event
 * 
 * <ul>
 * <li>{@link eea.engine.event.basicevents.CollisionEvent} checks if 
 * the owning entity has collided with a different, non-passable entity</li>
 * <li>{@link eea.engine.event.basicevents.KeyDownEvent} acts if the
 * key(s} passed in are currently pushed ("down")</li>
 * <li>{@link eea.engine.event.basicevents.KeyPressedEvent} acts if the
 * key(s) passed in have just been released ("pushed")</li>
 * <li>{@link eea.engine.event.basicevents.LeavingScreenEvent} acts if the
 * owning entity is off the visible screen area
 * <li>{@link eea.engine.event.basicevents.LoopEvent} will loop indefinitely</li>
 * <li>{@link eea.engine.event.basicevents.MouseClickedEvent} will act if the
 * <em>left</em> mouse button is just being pressed</li>
 * <li>{@link eea.engine.event.basicevents.MouseEnteredEvent} acts if the mouse
 * cursor is on top of the entity owning the event</li>
 * <li>{@link eea.engine.event.basicevents.MovementDoesNotCollideEvent} acts only
 * if the given movement will <em>not</em> lead to a collision.</li>
 * </ul>
 */
package eea.engine.event.basicevents;