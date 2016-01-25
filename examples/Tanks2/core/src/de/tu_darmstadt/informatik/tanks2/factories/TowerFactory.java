package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu_darmstadt.informatik.eea.event.EEAEvent;
import de.tu_darmstadt.informatik.eea.event.TimeEvent;
import de.tu_darmstadt.informatik.tanks2.AI.TowerAI;
import de.tu_darmstadt.informatik.tanks2.actions.ChangeShootAmmoAction;
import de.tu_darmstadt.informatik.tanks2.entities.Tower;
import temp.removeASAP.Tanks;

public class TowerFactory {
	private final boolean debug;
	private IResourcesManager resourcesManager;

	public TowerFactory(boolean debug, IResourcesManager resourcesManager) {
		this.debug = debug;
		this.resourcesManager = resourcesManager;

	}

	public Entity createEntity(float x, float y, int maxLife, int life, int maxShoots, int shoots, int strength,
			float speed, float rotation, float scaling) {

		Tower tower = new Tower("Tower" + Math.random());
		tower.setMaxLife(maxLife);
		tower.setLife(life);
		tower.setShootMaxAmmo(maxShoots);
		tower.setShootAtmmo(shoots);
		tower.setStrength(strength);
		tower.setRotation(rotation);
		tower.setScale(scaling);
		tower.setPosition(x, y);
		tower.setSpeed(speed);
		tower.setPassable(false);

		tower.addComponent(new ImageRenderComponent("flac.png", resourcesManager));
		tower.addComponent(new TowerAI(Tanks.player1, resourcesManager));

		EEAEvent mainEvent = new TimeEvent(1000, true);
		mainEvent.addAction(new ChangeShootAmmoAction(1));
		tower.addComponent(mainEvent);

		return tower;
	}

}
