package de.tu.darmstadt.informatik.eea.event;

public class LoopEvent extends EEAEvent {
	
	public static final String ID = "LoopEvent";
	
	public LoopEvent(){
		super(ID);
	}

	@Override
	public boolean eventTriggered(float delta) {
		return true;
	}

}
