package de.tu.darmstadt.informatik.eea.states;

import java.awt.Color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import de.tu.darmstadt.informatik.eea.EEAGame;

public abstract class BasicGameState implements Screen {
	
	protected final EEAGame game;
	protected final EntityManager em;
	
	public BasicGameState(EEAGame game) {
		this.game = game;
		game.addState(this);
		em = new EntityManager(game.getViewport());
	}
	
	/**
	 * @param delta
	 */
	protected abstract void update(float delta);
	
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		em.update(delta);
		update(delta);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		em.renderEntities();
	}

	@Override
	public void resize(int width, int height) {
		game.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void dispose() {
		game.removeState(this);
	}

}
