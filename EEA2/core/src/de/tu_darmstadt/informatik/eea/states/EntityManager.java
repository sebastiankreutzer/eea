package de.tu_darmstadt.informatik.eea.states;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.CoordinateHelper;

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
		CoordinateHelper.mainStage = stage;
	}

	public void addEntity(Entity e) {
		stage.addActor(e);
		e.setManager(this);
	}
	
	public void addEntities(List<Entity> entities) {
		for (Entity entity : entities) {
			addEntity(entity);
		}
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

	public List<Entity> getAllEntities() {
		return entities;
	}

	public boolean hasEntity(String prefix) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			if (e.getID().startsWith(prefix))
				return true;
		}
		return false;
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
		e.setManager(null);
	}

	public Entity collides(Entity e) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity other = it.next();
			if (e.collidesWith(other))
				return other;
		}
		return null;
	}

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
	 * Utility function. Converts mouse coordinates from screen to stage space.
	 * @return The converted vector
	 */
	public Vector2 mouseToStageCoordinates() {
		return toStageCoordinates(new Vector2(Gdx.input.getX(),
				Gdx.input.getY()));
	}
	
	/**
	 * Utility function. Converts the given coordinates to stage space.
	 * @param pos A vector in screen space
	 * @return The converted vector
	 */
	public Vector2 toStageCoordinates(Vector2 pos) {
		return stage.screenToStageCoordinates(pos);
	}
	
	/**
	 * Utility function. Converts the given coordinates to stage space.
	 * @param x 
	 * @param y
	 * @return The converted vector
	 */
	public Vector2 toStageCoordinates(float x, float y) {
		return toStageCoordinates(new Vector2(x, y));
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
