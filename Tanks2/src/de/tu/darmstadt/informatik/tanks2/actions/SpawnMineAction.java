package de.tu.darmstadt.informatik.tanks2.actions;

import de.tu.darmstadt.informatik.eea.IResourcesManager;
import de.tu.darmstadt.informatik.eea.action.EEAAction;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.factories.MineFactory;
import de.tu.darmstadt.informatik.tanks2.interfaces.IStrength;

public class SpawnMineAction extends EEAAction {

	private int strength;
	private MineFactory mineFactory;

	public SpawnMineAction(IResourcesManager resourcesManager) {
		strength = 0;
		mineFactory = new MineFactory(false, resourcesManager);
	}

	@Override
	public boolean act(float delta) {
		if (getActor() instanceof IStrength) {
			strength = ((IStrength) getActor()).getStrength() * 2;
		}

		Entity entity = mineFactory.createEntity(getActor().getX(), getActor().getY(), getActor().getScaleX() * 2,
				strength);

		getEntity().getManager().addEntity(entity);
		return true;
	}

}
