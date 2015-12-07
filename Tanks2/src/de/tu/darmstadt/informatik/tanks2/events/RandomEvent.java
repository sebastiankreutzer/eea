package de.tu.darmstadt.informatik.tanks2.events;

import de.tu.darmstadt.informatik.eea.event.EEAEvent;

public class RandomEvent extends EEAEvent {
	
	public static final String ID = "RandomEvent";
	
	private double chance;

	public RandomEvent(double chance) {
		super(ID);
		this.chance = chance / 100.0f;
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		return Math.random() <= chance;
	}

}
