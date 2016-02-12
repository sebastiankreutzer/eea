package de.tu_darmstadt.gdi1.tanks.model.event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tu_darmstadt.gdi1.tanks.model.StopWatch;

import eea.engine.event.Event;

public class TimeEvent extends Event {
	
	private StopWatch stopWatch;
	private long frequenzy;
	private boolean loop;

	public TimeEvent(long frequenzy, boolean loop) {
		super("TimeEvent");
		this.frequenzy = frequenzy;
		this.loop = loop;
		stopWatch = new StopWatch();
		stopWatch.start();
	}

	@Override
	protected boolean performAction(GameContainer gc, StateBasedGame sb,
			int delta) {
		if(stopWatch.getElapsedTime() >= frequenzy){
			stopWatch.reset();
			if(loop) stopWatch.start();
			return true;
		} else return false;
	}

}
