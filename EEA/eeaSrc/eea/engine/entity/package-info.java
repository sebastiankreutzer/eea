/**
 * This package provides the two base classes for entities.
 * 
 * Each entity has some physical properties (location, size,
 * orientation angle, scaling factor and the information whether
 * it can be "passed" by other entities without a collision.
 * 
 * Additionally, an entity can be "decorated" with an arbitrary
 * number of components, one of which should be responsible
 * for rendering the object. This can happen using a static image
 * using {@link eea.engine.component.render.ImageRenderComponent}
 * or by using a sequence of "animated" images using
 * {@link eea.engine.component.render.AnimationRenderComponent}.
 * 
 * To update an entity, the {@link eea.engine.entity.Entity#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)}
 * method is used.
 * 
 * The {@link eea.engine.entity.StateBasedEntityManager} manages the
 * entities available in a given game state, including collision detection.
 */
package eea.engine.entity;