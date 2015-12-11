package de.tu.darmstadt.informatik.eea.event;

public class TimeEvent extends EEAEvent {
	
	public static final String ID = "TimeEvent";
	
	private float time, period;
	private boolean loop, ended = false;

	/**
	 * Ein Event, welches nach einem definierten Zeitraum ausloest.
	 * @param period Der Zeitraum nach dem das Event ausloest in Sekunden.
	 * @param loop Bestimmt ob das Event wieder zurueckgesetzt wird.
	 */
	public TimeEvent(float period, boolean loop) {
		super(ID);
		this.period = period;
		this.loop = loop;
		this.time = 0;
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		time = time + delta;
		if(time >= period){
			if(loop){
				time = time - period;
				return true;
			} else if (!ended) {
				ended = true;
				return true;
			}
		}
		return false;
	}

}
