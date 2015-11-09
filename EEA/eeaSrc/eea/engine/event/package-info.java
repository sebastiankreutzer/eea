/**
 * This package provides the generic event classes in the <em>eea</em> framework.
 * 
 * Each event can be decorated with an arbitrary number of associated actions.
 * The {@link eea.engine.event.Event#performAction(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)}
 * method is used to determine if the associated actions shall 
 * actually be executed; for example, a {@link eea.engine.event.basicevents.CollisionEvent} 
 * will only cause its actions to be executed if the entity owning the event has collided
 * with an other entity.
 * 
 * The {@link eea.engine.event.Event#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)}
 * is used to tell each associated action for the event to update itself when update is called <em>and</em>
 * {@link eea.engine.event.Event#performAction(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)}
 * has returned <em>true</em>.
 * 
 * The two subtypes {@link eea.engine.event.ANDEvent} and {@link eea.engine.event.OREvent}
 * have slightly different semantics in that they will only perform an action if all (ANDEvent)
 * actions indicate their willingness to perform the action, or if at least one (OREvent) action
 * does so.
 */
package eea.engine.event;