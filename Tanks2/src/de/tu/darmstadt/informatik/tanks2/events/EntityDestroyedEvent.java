package de.tu.darmstadt.informatik.tanks2.events;

import de.tu.darmstadt.informatik.eea.event.EEAEvent;

public class EntityDestroyedEvent extends EEAEvent {
	
	private String entityID;

	public EntityDestroyedEvent(String entityID) {
		super("PlayerDestroyedEvent");
		this.entityID = entityID;
	}
	
	@Override
	public boolean eventTriggered(float delta) {
		// TODO I dont event know what this does. Should I set it to null explicitly?
		//if(entity == null) return true;
		return false;
	}

//	@Override
//	protected boolean performAction(GameContainer gc, StateBasedGame sb,
//			int delta) {
//		return !StateBasedEntityManager.getInstance().hasEntity(sb.getCurrentStateID(), entityID);
//	}

}
