package de.tu.darmstadt.informatik.eea;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.tu.darmstadt.informatik.eea.states.EEAGameState;

public abstract class EEAGame extends Game {

	public EEAGraphics graphics;
	
	private List<EEAGameState> states = new ArrayList<EEAGameState>();
	private Viewport viewport;
	
	public EEAGame(float width, float height) {
		this.viewport = new FitViewport(width, height);
	}
	
	public EEAGame() {
		this(800, 600);
	}
	
	public void addState(EEAGameState state) {
		states.add(state);
	}
	
	public void removeState(EEAGameState state) {
		states.remove(state);
	}
	
	@Override
	public void create(){
		graphics = new EEAGraphics();
		initStates();
		startGame();
	}
	
	protected abstract void initStates();
	
	protected abstract void startGame();
	
	@Override
	public void resize (int width, int height) {
		if (viewport != null) viewport.update(width, height, true);
		if (screen != null) screen.resize(width, height);
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
	public int getFramerate() {
		return Gdx.graphics.getFramesPerSecond();
	}
	
	public float getAspectRatio() {
		return viewport.getScreenWidth() / viewport.getScreenHeight();
	}

}
