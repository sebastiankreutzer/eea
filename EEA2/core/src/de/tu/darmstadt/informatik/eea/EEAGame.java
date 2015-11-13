package de.tu.darmstadt.informatik.eea;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.tu.darmstadt.informatik.eea.states.BasicGameState;

public class EEAGame extends Game {
	
	 /*
	  * Add game states here
	  */
	
	private List<BasicGameState> states = new ArrayList<BasicGameState>();
	
	private Viewport viewport;
	
	public void addState(BasicGameState state) {
		states.add(state);
	}
	
	public void removeState(BasicGameState state) {
		states.remove(state);
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void create () {
		viewport = new FitViewport(800, 600);
	}
	
	public int getFramerate() {
		return Gdx.graphics.getFramesPerSecond();
	}
	
	public float getAspectRatio() {
		return viewport.getScreenWidth() / viewport.getScreenHeight();
	}

}
