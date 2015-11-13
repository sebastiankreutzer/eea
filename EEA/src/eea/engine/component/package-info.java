/**
 * This package provides the abstract classes for components.
 * 
 * Components are separated into general components and
 * {@link eea.engine.component.RenderComponent}s that represent
 * entities with an ID that are rendered (displayed) on the game
 * display.
 * 
 * Each component has an ID and an underlying game object 
 * (of type {@link eea.engine.entity.Entity}) associated with
 * the component. {@link eea.engine.component.RenderComponent}s
 * additionally provide methods for returning their current 
 * size and for rendering them on the screen.
 * 
 * For more information on the renderable components, please see the
 * {@link eea.engine.component.render} package.
 */
package eea.engine.component;