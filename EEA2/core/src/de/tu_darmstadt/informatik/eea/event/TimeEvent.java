package de.tu_darmstadt.informatik.eea.event;

/**
 * This event is triggered after a predefined time interval.
 * It can be configurated to either activate once, or loop indefinitely.
 * 
 * @author Johann Reinhard
 *
 */
public class TimeEvent extends EEAEvent {
	
	public static final String ID = "TimeEvent";
	
	private float time, period;
	private boolean loop, ended = false;

	/**
	 * Creates a new TimeEvent.
	 * @param period The time interval in seconds.
	 * @param loop If true, the event is reset after completion.
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
