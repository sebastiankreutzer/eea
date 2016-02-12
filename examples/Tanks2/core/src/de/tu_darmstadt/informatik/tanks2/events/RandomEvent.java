package de.tu_darmstadt.informatik.tanks2.events;

import java.util.Random;

import de.tu_darmstadt.informatik.eea.event.EEAEvent;

public class RandomEvent extends EEAEvent {
	
	public static final String ID = "RandomEvent";
	private static final Random random = new Random();
	
	private double chance;
	private float min, max, current;

	/**
	 * @param chance The chance in percent
	 */
	public RandomEvent(double chance) {
		super(ID);
		this.chance = chance/100;
		Random random = new Random();
	}
	
	/**
	 * @param minTime Mindest Zeit die bis zum ausloesen des Events vergehen muss in ms
	 * @param maxTime Hoechst Zeit die bis zum Ausloesen des Events vergehen muss in ms
	 */
	public RandomEvent(float minTime, float maxTime) {
		super(ID);
		min = minTime;
		max = maxTime;
		init();
	}

	private void init() {
		current = min + (max - min) * random.nextFloat();
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		current -= delta;
		if(current < 0) {
			init();
			return true;
		}
		return false;
	}

}
