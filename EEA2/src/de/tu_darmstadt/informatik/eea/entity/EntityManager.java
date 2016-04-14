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
	 * Adds given Entity to this {@link Stage} and sets this instance as EntityManager.
	 * @param e
	 */
	public void addEntity(Entity e) {
		stage.addActor(e);
		e.setManager(this);
	}
	
	/**
	 * calls addEntity foreach Entity in given list
	 * @param entities
	 */
	public void addEntities(List<Entity> entities) {
		for (Entity entity : entities) {
			addEntity(entity);
		}
	}

	/**
	 * Finds an Entity by its id. Returns the first added entity with matching id.
	 * @param id
	 * @return
	 */
	public Entity getEntity(String id) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getID().equals(id))
				return e;
		}
		return null;
	}

	/**
	 * returns all entities.
	 * @return
	 */
	public List<Entity> getAllEntities() {
		return entities;
	}

	/**
	 * checks wehter an entity exists with matching id-prefix
	 * @param prefix
	 * @return
	 */
	public boolean hasEntity(String prefix) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getID().startsWith(prefix))
				return true;
		}
		return false;
	}

	/**
	 * removes one Entity and unsets its EntityManager
	 * @param e
	 */
	public void removeEntity(Entity e) {
		entities.remove(e);
		e.setManager(null);
	}

	/**
	 * Returns one colliding Entity with given Entity. Returns null if there is no colliding entity.
	 * @param e
	 * @return
	 */
	public Entity collides(Entity e) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity other = it.next();
			if (e.collidesWith(other))
				return other;
		}
		return null;
	}

	/**
	 * Returns a list of all colliding Entities with given Entity. Returns an empty list, if there is no colliding one.
	 * @param e
	 * @return
	 */
	public List<Entity> getAllCollisions(Entity e) {
		List<Entity> collisions = new ArrayList<Entity>();
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity other = it.next();
			if (e.collidesWith(other))
				collisions.add(other);
		}
		return collisions;
	}


	/**
	 * updates all entities.
	 * @param delta
	 */
	public void update(float delta) {
		stage.act(delta);
	}

	/**
	 * renders all entities.
	 */
	public void renderEntities() {
		stage.draw();
	}

	/**
	 * resets this EntityManager. All entities will be removed. 
	 */
	public void reset() {
		stage.clear();
		while(entities.size() > 0) {
			entities.get(0).remove();
			
		}
//		while(iterator.hasNext()) {
//			Entity e = iterator.next();
//			e.remove();
//		}
		entities.clear();
	}

	public void dispose() {
		reset();
		stage.dispose();
	}

	/**
	 * Enable or disable debugging.
	 * @param b
	 */
	public void setDebug(boolean b) {
		stage.setDebugAll(b);
	}

}
