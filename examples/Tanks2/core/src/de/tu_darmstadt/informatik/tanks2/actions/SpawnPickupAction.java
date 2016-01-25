package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.entities.Pickup.PickUpType;
import de.tu_darmstadt.informatik.tanks2.factories.PickupFactory;

public class SpawnPickupAction extends EEAAction {

	private PickUpType type;
	private PickupFactory factory;

	public SpawnPickupAction(IResourcesManager resourcesManager) {
		factory = new PickupFactory(false, resourcesManager);
	}

	@Override
	public boolean act(float delta) {
		if (Math.random() > 0.5)
			type = PickUpType.AMMUNITION;
		else
			type = PickUpType.HEALTH;

		Entity pickup = factory.createEntity(type, 100, (float) Math.random() * 800, (float) Math.random() * 600, 0.3f);
		getEntity().getManager().addEntity(pickup);

		return true;
	}

}
