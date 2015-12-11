package de.tu.darmstadt.informatik.eea.event;

/**
 * This event does always trigger the associated actions and acts as an infinite true loop.
 * @author Tim Borowski, Sebastian Kreutzer, Johann Reinhard, Alexander Jandousek, Timo B&auml;hr
 * @version 2.0
 */
public class LoopEvent extends EEAEvent {
	
	public static final String ID = "LoopEvent";
	
	/**
	 * Creates a new LoopEvent.
	 */
	public LoopEvent(){
		super(ID);
	}

	/* (non-Javadoc)
	 * @see de.tu.darmstadt.informatik.eea.event.EEAEvent#eventTriggered(float)
	 */
	@Override
	public boolean eventTriggered(float delta) {
		return true;
	}

}
