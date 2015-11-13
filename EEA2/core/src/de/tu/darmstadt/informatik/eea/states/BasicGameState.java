package de.tu.darmstadt.informatik.eea.states;

import com.badlogic.gdx.Screen;

import de.tu.darmstadt.informatik.eea.EEAGame;

public abstract class BasicGameState implements Screen {
	
	protected final EEAGame game;
	protected final EntityManager em;
	
	public BasicGameState(EEAGame game) {
		this.game = game;
		game.addState(this);
		em = new EntityManager();
	}
	
	/**
	 * TODO
	 * @param delta
	 */
	protected abstract void update(float delta);
	
	/**
	 * TODO
	 */
	protected abstract void render();
	
	/**
	 * TODO
	 */
	protected abstract void init();
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub		
		update(delta);
		render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub		
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
