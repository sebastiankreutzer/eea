package de.tu.darmstadt.informatik.sampleGame;

import com.badlogic.gdx.graphics.Texture;

import de.tu.darmstadt.informatik.eea.EEAGame;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.states.BasicGameState;

public class MainMenuState extends BasicGameState {

	public MainMenuState(EEAGame game) {
		super(game);
		init();
	}

	private void init() {
		Entity background = new Entity("menu");
		background.setPosition(400, 300);	// Startposition des Hintergrunds
    	background.addComponent(new ImageRenderComponent(new Texture("menu.png"))); // Bildkomponente
	}

	@Override
	protected void update(float delta) {				
	}
}
