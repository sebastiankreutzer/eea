package de.tu_darmstadt.informatik.eea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import de.tu_darmstadt.informatik.eea.entity.EntityManager;

public abstract class EEAGameState implements Screen {

	protected final EEAGame game;
	protected final EntityManager em;
	protected final InputMultiplexer im;
	private Color backgroundColor = new Color(0,0,0,0);//new Color(101f / 255, 156f / 255, 239f / 255, 1.0f);

	private boolean paused = false;
	private boolean initialized = false;

	/**
	 * Creates a new game state.
	 * @param game The current game object.
	 */
	public EEAGameState(EEAGame game) {
		this.game = game;

		game.addState(this);
		em = new EntityManager(EEAGame.getGraphics().getViewport());
		im = new InputMultiplexer();
	}

	/**
	 * Updates the game.
	 * Any changes in the current game state are performed here.
	 * @param delta Elapsed time in seconds.
	 */
	protected abstract void update(float delta);

	/**
	 * Initializes the game state.
	 */
	protected abstract void init();

	@Override
	public void show() {
		Gdx.input.setInputProcessor(im);
		updateBackgroundColor();
		if (!initialized) {
			initialized = true;
			init();
		}
	}

	/**
	 * Sets the background color that is used to clear the screen.
	 * Color components should be in range 0 - 255.
	 * @param r Red component
	 * @param g Green component
	 * @param b Blue component
	 */
	public void setBackgroundColor(int r, int g, int b) {
		this.backgroundColor = new Color(r / 255f, g / 255f, b / 255f, 1.0f);
		updateBackgroundColor();
	}

	/**
	 * Updates the background color.
	 */
	protected void updateBackgroundColor() {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1.0f);
	}

	/**
	 * Updates and renders the game.
	 * @param delta Elapsed time in seconds.
	 */
	@Override
	public void render(float delta) {
		if (!paused) {
			em.update(delta);
			update(delta);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			em.renderEntities();
			EEAGame.getGraphics().drawShapes();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	/**
	 * Resets the game state.
	 */
	public void reset() {
		em.reset();
		initialized = false;
	}

	/**
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
	}

	/**
	 * Disposes this game state.
	 */
	@Override
	public void dispose() {
		em.dispose();
		game.removeState(this);
	}

}
