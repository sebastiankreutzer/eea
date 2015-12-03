package de.tu.darmstadt.informatik.eea.states;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public class EntityManager {

	/**
	 * Scene graph which updates and renders the entities.
	 */
	private Stage stage;

	/**
	 * Keeps track of all entities in the stage. 
	 * This is somewhat redundant but prevents casting of the libGDX {@link Actor}s every time an entity needs to be accessed.
	 */
	private List<Entity> entities;

	public EntityManager(Viewport viewport) {
		stage = new Stage(viewport);
		entities = new ArrayList<Entity>();
	}

	public void addEntity(Entity e) {
		entities.add(e);
		stage.addActor(e);
		e.setManager(this);
	}

	public Entity getEntity(String name) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getID().equals(name))
				return e;
		}
		return null;
	}
	
	public boolean hasEntity(String prefix) {
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()) {
			Entity e = it.next();
			if (e.getID().startsWith(prefix))
				return true;
		}
		return false;
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
		e.remove();
		e.setManager(null);
	}

	public Entity collides(Entity e) {
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()) {
			Entity other = it.next();
			if (e.collides(other))
				return other;
		}
		return null;
	}
	
	public List<Entity> getAllCollisions(Entity e) {
		List<Entity> collisions = new ArrayList<Entity>();
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()) {
			Entity other = it.next();
			if (e.collides(other))
				collisions.add(other);
		}
		return collisions;
	}

	public void update(float delta) {
		stage.act(delta);
	}

	public void renderEntities() {
		stage.draw();
	}

	public void reset() {
		for (Entity e : entities) {
			e.remove();
		}
		stage.clear();
		entities.clear();
	}
	
	public void dispose() {
		reset();
		stage.dispose();
	}

	public void setDebug(boolean b) {
		stage.setDebugAll(b);
	}

}
