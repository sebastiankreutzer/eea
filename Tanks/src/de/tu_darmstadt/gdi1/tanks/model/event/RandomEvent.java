package de.tu_darmstadt.gdi1.tanks.model.event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.event.Event;

public class RandomEvent extends Event {
	
	private double chance;

	public RandomEvent(double chance) {
		super("RandomEvent");
		this.chance = chance / 100.0f;
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		return Math.random() <= chance;
	}

}
