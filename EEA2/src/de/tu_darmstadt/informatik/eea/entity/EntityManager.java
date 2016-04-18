package de.tu_darmstadt.informatik.eea.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EntityManager {

	/**
	 * Scene graph which updates and renders the entities.
	 */
	private Stage stage;

	/**
	 * Keeps track of all entities in the stage. This is somewhat redundant but
	 * prevents casting of the libGDX {@link Actor}s every time an entity needs
	 * to be accessed.
	 */
	private List<Entity> entities;

	/**
	 * Creates a new empty EntityManager.
	 * 
	 * @param viewport
	 *            The viewport that defines the size of the world.
	 */
	public EntityManager(Viewport viewport) {
		stage = new Stage(viewport) {
			@Override
			public void addActor(Actor actor) {
				super.addActor(actor);
				entities.add((Entity) actor);
			}
		};
		entities = new ArrayList<Entity>();
	}

	/**
	 * Adds given Entity to this {@link Stage} and sets this as the entities
	 * EntityManager.
	 * 
	 * @param entity
	 *            The entity to manage.
	 */
	public void addEntity(Entity entity) {
		stage.addActor(entity);
		entity.setManager(this);
	}

	/**
	 * Calls addEntity for each Entity in given list.
	 * 
	 * @param entities
	 *            A list of entities.
	 */
	public void addEntities(List<Entity> entities) {
		for (Entity entity : entities) {
			addEntity(entity);
		}
	}

	/**
	 * Finds an Entity by its id. Returns the first added entity with matching
	 * id or null if there is no entity with this name.
	 * 
	 * @param id
	 *            The id/name of the searched entity.
	 * @return An entity with this name or null.
	 */
	public Entity getEntity(String id) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getName().equals(id))
				return e;
		}
		return null;
	}

	/**
	 * @return A list containing all entities managed by this EntityManager.
	 */
	public List<Entity> getAllEntities() {
		return entities;
	}

	/**
	 * Checks whether an entity exists with matching ID prefix.
	 * 
	 * @param prefix
	 *            The prefix the entities name should begin with.
	 * @return True if any entity managed by this manager begins with the
	 *         prefix, otherwise false.
	 */
	public boolean hasEntity(String prefix) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getName().startsWith(prefix))
				return true;
		}
		return false;
	}

	/**
	 * Removes an Entity from the list of entities and sets its EntityManager to
	 * null.
	 * 
	 * @param entity
	 *            The entity to remove from this manager.
	 */
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		entity.setManager(null);
	}

	/**
	 * Checks whether the entity collides with any other entity managed by this
	 * manager. Returns the first entity that triggers a collision or null if
	 * there is no colliding entity.
	 * 
	 * @param entity
	 *            The entity that may collide with other entities.
	 * @return The first entity it collides with, or null if no collision was
	 *         detected.
	 */
	public Entity collides(Entity entity) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity other = it.next();
			if (entity.collidesWith(other))
				return other;
		}
		return null;
	}

	/**
	 * Returns a list of all colliding Entities with a given Entity. Returns an
	 * empty list, if there was no collision detected.
	 * 
	 * @param entity
	 *            The entity that may collide with other entities.
	 * @return A list that contains all entities it collides with, may be empty.
	 */
	public List<Entity> getAllCollisions(Entity entity) {
		List<Entity> collisions = new ArrayList<Entity>();
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity other = it.next();
			if (entity.collidesWith(other))
				collisions.add(other);
		}
		return collisions;
	}

	/**
	 * Updates all entities.
	 * 
	 * @param delta
	 *            The elapsed time since the last call in seconds.
	 */
	public void update(float delta) {
		stage.act(delta);
	}

	/**
	 * Renders all entities.
	 */
	public void renderEntities() {
		stage.draw();
	}

	/**
	 * Resets this EntityManager. All entities will be removed.
	 */
	public void reset() {
		stage.clear();
		while (entities.size() > 0) {
			entities.get(0).remove();

		}
		entities.clear();
	}

	public void dispose() {
		reset();
		stage.dispose();
	}

	/**
	 * Enable or disable debugging.
	 * 
	 * @param enabled
	 *            Whether debugging is enabled.
	 */
	public void setDebug(boolean enabled) {
		stage.setDebugAll(enabled);
	}

}
