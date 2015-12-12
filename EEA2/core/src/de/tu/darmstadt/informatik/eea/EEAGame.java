package de.tu.darmstadt.informatik.eea;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.tu.darmstadt.informatik.eea.states.EEAGameState;

/**
 *\\TODO Description
 * @author Sebastian Kreutzer, Johann Reinhard
 * @version 1.0
 */
public abstract class EEAGame extends Game {

	public EEAGraphics graphics;
	
	private List<EEAGameState> states = new ArrayList<EEAGameState>();
	private Viewport viewport;
	
	/**
	 * Creates a new game with a given width and height.
	 * @param width The width of the viewport.
	 * @param height The height of the viewport.
	 */
	public EEAGame(float width, float height) {
		this.viewport = new FitViewport(width, height);
	}
	
	/**
	 * Creates a new game with the default width and height.
	 */
	public EEAGame() {
		this(800, 600);
	}
	
	/**
	 * Adds a {@link de.tu.darmstadt.informatik.eea.states.EEAGameState} to the list of states.
	 * @param state The state to be added to the list of states.
	 */
	public void addState(EEAGameState state) {
		states.add(state);
	}
	
	/**
	 * Removes a {@link de.tu.darmstadt.informatik.eea.states.EEAGameState} from the list of states.
	 * @param state The state to be removed from the list of states.
	 */
	public void removeState(EEAGameState state) {
		states.remove(state);
	}
	
	@Override
	public void create(){
		graphics = new EEAGraphics();
		initStates();
		startGame();
	}
	
	/**
	 * Initializes each {@link de.tu.darmstadt.informatik.eea.states.EEAGameState} after the game was created.
	 */
	protected abstract void initStates();
	
	/**
	 * Starts the game after each {@link de.tu.darmstadt.informatik.eea.states.EEAGameState} was initialized.
	 */
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
	
	/**
	 * Returns the frames per second (FPS) of this game.
	 * @return FPS
	 */
	public int getFramerate() {
		return Gdx.graphics.getFramesPerSecond();
	}
	
	public float getAspectRatio() {
		return viewport.getScreenWidth() / viewport.getScreenHeight();
	}

}
