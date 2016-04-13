package de.tu_darmstadt.informatik.eea;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * 
 * EEAGame serves as a base for every game. The class provides abstract methods
 * for initializing and entering the different states, which have to be
 * overridden by the inheriting class.
 * 
 * @author Sebastian Kreutzer, Johann Reinhard
 * @version 1.0
 */
public abstract class EEAGame extends Game {

	private static EEAGraphics graphics;
	private static ResourceManager resourceManager;

	private final Viewport viewport;

	protected final List<EEAGameState> states = new ArrayList<EEAGameState>();
	protected final boolean debug;

	/**
	 * Creates a new game with a given width and height.
	 * 
	 * @param width
	 *            The width of the world.
	 * @param height
	 *            The height of the world.
	 */
	public EEAGame(float width, float height, boolean debug) {
		// this.viewport = new StretchViewport(width, height);
		viewport = new FitViewport(width, height);
		this.debug = debug;
	}

	/**
	 * Creates a new game with a given width and height.
	 * 
	 * @param width
	 *            The width of the viewport.
	 * @param height
	 *            The height of the viewport.
	 */
	public EEAGame(float width, float height) {
		this(width, height, false);
	}

	/**
	 * Creates a new game with the default width and height.
	 */
	public EEAGame() {
		this(800, 600, false);
	}

	/**
	 * Adds a {@link de.tu_darmstadt.informatik.eea.EEAGameState} to the list of
	 * states.
	 * 
	 * @param state
	 *            The state to be added to the list of states.
	 */
	public void addState(EEAGameState state) {
		states.add(state);
	}

	/**
	 * Removes a {@link de.tu_darmstadt.informatik.eea.EEAGameState} from the
	 * list of states.
	 * 
	 * @param state
	 *            The state to be removed from the list of states.
	 */
	public void removeState(EEAGameState state) {
		states.remove(state);
	}

	@Override
	public void create() {
		graphics = new EEAGraphics(viewport);
		resourceManager = new ResourceManager();
		initStates();
		startGame();
	}

	@Override
	public void render() {
		super.render();
		resourceManager.update();
	}

	/**
	 * Initializes each {@link de.tu_darmstadt.informatik.eea.EEAGameState}
	 * after the game was created.
	 */
	protected abstract void initStates();

	/**
	 * Starts the game after each
	 * {@link de.tu_darmstadt.informatik.eea.EEAGameState} was initialized.
	 */
	protected abstract void startGame();

	@Override
	public void resize(int width, int height) {
		if (viewport != null)
			viewport.update(width, height, true);
		if (screen != null)
			screen.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	/**
	 * Returns true if the game is in debug mode.
	 * 
	 * @return
	 */
	public final boolean isDebug() {
		return debug;
	}

	/**
	 * Returns the static graphics object, which e.g. provides operations for
	 * coordinate transformation.
	 * 
	 * @return The EEAGraphics object.
	 */
	public static EEAGraphics getGraphics() {
		return graphics;
	}

	/**
	 * Returns the default resource manager.
	 * 
	 * @return The resource manager.
	 */
	public static IResourceManager getResourceManager() {
		return resourceManager;
	}

}
