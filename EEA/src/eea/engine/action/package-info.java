/**
 * This package provides the default interface for actions.
 * 
 * Actions are broadly similar to GUI events in that multiple
 * actions can be added to a given event. When an event occurs,
 * the associated actions - zero, one, or multiple actions -
 * are informed about this and 
 * their {@link eea.engine.action.Action#update(GameContainer, StateBasedGame, int, Component)}
 * method is invoked. This method is then responsible for updating the game and/or entity
 * state accordingly, for example, by moving or rotating the chosen element.
 * 
 * Note that the entity that has caused an event, or is affected by it (e.g.,
 * by "being shot"), can be queried using the 
 * {@link eea.engine.component.Component#getOwnerEntity()} method.
 * 
 * For more information on the available actions, please see the
 * {@link eea.engine.action.basicactions} package.
 */
package eea.engine.action;