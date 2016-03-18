package de.tu_darmstadt.informatik.tanks2.events;

import java.util.Random;

import de.tu_darmstadt.informatik.eea.event.EEAEvent;

/**
 * Ein Zufallsevent das nach Ablauf einer zufaelligen Zeit ausloest. Diese liegt
 * zwischen der Mindest- und Maximalzeit.
 * 
 * @author jr
 *
 */
public class RandomEvent extends EEAEvent {

	public static final String ID = "RandomEvent";
	private static final Random random = new Random();

	protected float minTime, maxTime, remaining;
	protected boolean repeat;
	private boolean triggered = false;

	/**
	 * @param minTime
	 *            Mindest Zeit die bis zum ausloesen des Events vergehen muss in
	 *            ms
	 * @param maxTime
	 *            Hoechst Zeit die bis zum Ausloesen des Events vergehen muss in
	 *            ms
	 */
	/**
	 * Erzeugt eine neues RandomEvent, zufaellig zwischen der Mindest- und
	 * Maximalzeit ausloest.
	 * 
	 * @param minTime
	 *            Die Mindestzeit
	 * @param maxTime
	 *            Die Maximalzeit
	 * @param repeat
	 *            Ob das Event wiederholt ausloesen soll.
	 */
	public RandomEvent(float minTime, float maxTime, boolean repeat) {
		super(ID);
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.repeat = repeat;
		init();
	}

	/**
	 * Initialisiert die Zeit bis zum naechsten Ausloesen.
	 */
	private void init() {
		remaining = minTime + (maxTime - minTime) * random.nextFloat();
	}

	@Override
	public boolean eventTriggered(float delta) {
		if(triggered) {
			return false;
		}
		// Loese das Event aus, wenn die Zeit abgelaufen ist
		remaining -= delta;
		if (remaining < 0) {
			// Setze den Zaehler gegebenefalls zurueck
			if (repeat) {
				init();
			} else {
				triggered = true;
			}
			return true;
		}
		return false;
	}
}
