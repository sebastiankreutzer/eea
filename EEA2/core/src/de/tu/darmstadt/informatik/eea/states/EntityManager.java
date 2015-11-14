package de.tu.darmstadt.informatik.eea.states;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.tu.darmstadt.informatik.eea.entity.Entity;

public class EntityManager {
	
	private Stage stage;
	
	public EntityManager(Viewport viewport) {
		stage = new Stage(viewport);
	}
	
	
	public void addEntity(Entity e) {
		stage.addActor(e);
	}

	public Entity getEntity(String id) {
		for (Actor a : stage.getActors()) {
			if (a instanceof Entity) {
				Entity e = (Entity)a;
				if (e.getID().equals(id))
					return e;
			}
		}
		return null;
	}
	
	public void removeEntity(Entity e) {
		e.remove();
	}
	
	public Entity collides(Entity e) {
		for (Actor a : stage.getActors()) {
			if (a instanceof Entity) {
				Entity other = (Entity)a;
				// TODO Check for collision
			}
		}
		return null;
	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
	public void renderEntities() {
		stage.draw();
	}


	public void dispose() {
		for (Actor a : stage.getActors()) {
			if (a instanceof Entity) {
				((Entity) a).dispose();
			}
		}
	}

}
