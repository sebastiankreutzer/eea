package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.IResourceManager;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.entities.Pickup.PickupType;
import de.tu_darmstadt.informatik.tanks2.factories.PickupFactory;

/**
 * Diese Action erzeugt ein Pickup Entity eines zufaellgien Typs an einer
 * zufaelligen Stelle.
 * 
 * @author jr
 *
 */
public class SpawnPickupAction extends EEAAction {

	private PickupType type;
	private PickupFactory factory;

	/**
	 * Erzeugt eine neue SpawnPickupAction
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager
	 */
	public SpawnPickupAction(IResourceManager resourcesManager) {
		factory = new PickupFactory(false, resourcesManager);
	}

	@Override
	public boolean act(float delta) {
		// Waehle den Typ zufaellig aus
		if (Math.random() > 0.5)
			type = PickupType.AMMUNITION;
		else
			type = PickupType.HEALTH;
		// Und erzeuge das Pickup an einer zufaelligen Position
		Entity pickup = factory.createPickup(type, 100, (float) Math.random() * 800, (float) Math.random() * 600, 0.3f);
		getEntity().getManager().addEntity(pickup);

		return true;
	}

}
