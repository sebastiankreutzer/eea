package de.tu_darmstadt.informatik.tanks2.actions;

import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.factories.MineFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;

public class SpawnMineAction extends EEAAction {

	private boolean debug;
	private MineFactory mineFactory;

	public SpawnMineAction(MineFactory mineFactory, boolean debug) {
		this.debug = debug;
		this.mineFactory = mineFactory;
	}

	@Override
	public boolean act(float delta) {
		Entity owner = getEntity();
		if (owner instanceof IStrength) {
			int strength = ((IStrength) owner).getStrength() * 2;
			Entity entity = mineFactory.createEntity(owner.getX(), owner.getY(), owner.getScaleX() * 2, strength);

			owner.getManager().addEntity(entity);
		}
		return true;
	}

}
