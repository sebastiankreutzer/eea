package de.tu.darmstadt.informatik.eea.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import de.tu.darmstadt.informatik.eea.EEAGame;

public abstract class EEAGameState implements Screen {
	
	protected final EEAGame game;
	protected final EntityManager em;
	protected final InputMultiplexer im;
	private Color backgroundColor = new Color(101f/255, 156f/255, 239f/255, 1.0f);
	
	private boolean paused = false;
	private boolean initialized = false;
	
	public EEAGameState(EEAGame game) {
		this.game = game;
		
		game.addState(this);
		em = new EntityManager(game.getViewport());
		im = new InputMultiplexer();
	}
	
	/**
	 * @param delta
	 */
	protected abstract void update(float delta);
	
	protected abstract void init();
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(im);
		updateBackgroundColor();
		if (!initialized) {
			init();
			initialized = true;
		}
	}
	
	public void setBackgroundColor(int r, int g, int b){
		this.backgroundColor = new Color(r/255f, g/255f, b/255f, 1.0f);
	}
	
	public void updateBackgroundColor(){
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1.0f);
	}

	@Override
	public void render(float delta) {
		if (!paused) {
			em.update(delta);
			update(delta);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			em.renderEntities();
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}
	
	public void reset() {
		em.reset();
	}

	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;	
	}

	@Override
	public void hide() {
		//em.reset();	
	}

	@Override
	public void dispose() {
		em.dispose();
		game.removeState(this);
	}

}
