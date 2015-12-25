package de.tu.darmstadt.informatik.eea.states;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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
	private Color backgroundColor = new Color(101f / 255, 156f / 255, 239f / 255, 1.0f);

	private boolean paused = false;
	private boolean initialized = false;

	Writer writer = null;

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
		if (game.isDebug()) {
			try {
				writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(this.getClass().toString() + ".txt"), "utf-8"));
			} catch (IOException ex) {
				// report
			}
		}

		Gdx.input.setInputProcessor(im);
		updateBackgroundColor();
		if (!initialized) {
			init();
			initialized = true;
		}
	}

	public void setBackgroundColor(int r, int g, int b) {
		this.backgroundColor = new Color(r / 255f, g / 255f, b / 255f, 1.0f);
	}

	public void updateBackgroundColor() {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1.0f);
	}

	@Override
	public void render(float delta) {
		if (!paused) {
			long currentTime = System.currentTimeMillis();

			em.update(delta);
			long entitiesUpdateDelta = System.currentTimeMillis() - currentTime;
			update(delta);
			long stateUpdateDelta = System.currentTimeMillis() - currentTime - entitiesUpdateDelta;
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			em.renderEntities();
			long renderUpdateDelta = System.currentTimeMillis() - currentTime - entitiesUpdateDelta - stateUpdateDelta;

			if (game.isDebug()) {
				try {
					writer.write("Perf: Render cycle " + delta + '\t' + entitiesUpdateDelta + '\t' + stateUpdateDelta
							+ '\t' + renderUpdateDelta + '\n');
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	public void reset() {
		em.reset();
	}

	/*
	 * (non-Javadoc)
	 * 
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
		// em.reset();
		try {
			writer.close();
		} catch (Exception ex) {
			/* ignore */}
	}

	@Override
	public void dispose() {
		em.dispose();
		game.removeState(this);
	}

}
