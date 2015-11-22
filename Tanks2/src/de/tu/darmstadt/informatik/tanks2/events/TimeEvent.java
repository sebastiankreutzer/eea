package de.tu.darmstadt.informatik.tanks2.events;

import de.tu.darmstadt.informatik.eea.event.EEAEvent;

public class TimeEvent extends EEAEvent {
	
	public static final String ID = "TimeEvent";
	
	private long time;
	private long period;
	private boolean loop, ended;

	public TimeEvent(long period, boolean loop) {
		super(ID);
		this.period = period;
		this.loop = loop;
		this.time = 0;
		this.ended = false;
	}
	
	@Override
	public boolean eventTriggered(float delta) {		
		time = time + (long) delta;
		if(time >= period){
			if(loop){
				time = time - period;
				return true;
			} else if (!ended){
				ended = true;
				return true;
			}
		}
		return false;
	}

}
