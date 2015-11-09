/**
 * This package provides a set of default basic actions that modify
 * objects.
 * 
 * More precisely, the following basic actions are currently supported:
 * 
 * <ul>
 * <li>Changing to the <em>initial state</em> of the game, e.g., after the user has
 * won or lost the game and or has pressed a "new game" button or key,
 * using the {@link eea.engine.action.basicactions.ChangeStateInitAction} class.</li>
 * 
 * <li><em>Destroying</em> an entity and removing it from the game display, e.g.,
 * once the player has "shot, exploded or picked up" the given entity. This uses the
 * {@link eea.engine.action.basicactions.DestroyEntityAction} class.</li>
 * 
 * <li>Moving an object based on an associated user action or internal trigger (the
 * latter is typically the case for computer-steered entities).
 * 
 * <br />The movement occurs in six possible ways: <em>up, down, left, or right</em>,
 * all of which only modify either the <em>x</em> or <em>y</em> position of the entity 
 * - but not both - and ignore its current orientation, or <em>forward / backwards</em>,
 * which modify both <em>x</em> and <em>y</em> position based on the current orientation
 * of the entity. In all cases, the movement speed and the time passed are taken into account.
 * 
 * <br />Each movement operation is implemented in a separate subclass of 
 * {@link eea.engine.action.basicactions.Movement}, e.g. 
 * {@link eea.engine.action.basicactions.MoveDownAction}.</li>
 * 
 * <li>The {@link eea.engine.action.basicactions.QuitAction} is used to tell
 * the game engine that the user has quit the game (voluntarily or otherwise).</li>
 * <li>If an entity shall no longer be informed of a certain event, use
 * {@link eea.engine.action.basicactions.RemoveEventAction}.</li>
 * 
 * <li><em>Rotations</em> are modelled using the pair of
 * {@link eea.engine.action.basicactions.RotateLeftAction} and
 * {@link eea.engine.action.basicactions.RotateRightAction} classes, depending
 * on whether the direction is in clockwise ("right") or counterclockwise ("left")
 * orientation.</li>
 * 
 * <li><em>"Teleport"</em> effects, where an entity directly assumes a fixed
 * position, are modelled using the {@link eea.engine.action.basicactions.SetEntityPositionAction}
 * class. This can for example be used by objects that leave the game area at one border
 * and shall then reappar up at the opposite border. (Old gamers may think of the "tunnels"
 * in Pac-Man).</li>
 * </ul>
 */
package eea.engine.action.basicactions;