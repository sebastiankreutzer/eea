package de.tu_darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;

import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.factories.ExplosionFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.ILife;

/**
 * Eine HitAction reduziert die Lebenspunkte eines ILife Ziels, erstellt eine
 * Explosion, erzeugt eine DestroyAction fuer denn Besizter dieser Action und
 * erzeugt gegebenenfalls eine DestroyAction fuer das Ziel.
 * 
 * @author jr
 *
 */
public class HitAction extends EEAAction {

	private int strength;
	private ExplosionFactory explosionFactory;
	private boolean debug;

	/**
	 * Erstellt eine neue HitAction.
	 * 
	 * @param strength
	 *            Die Staerke der HitAction.
	 * @param explosionFactory
	 *            Eine ExplosionFactory zum erstellen der Explosionen.
	 */
	public HitAction(int strength, ExplosionFactory explosionFactory) {
		this(strength, explosionFactory, false);
	}

	/**
	 * Erstellt eine neue HitAction.
	 * 
	 * @param strenght
	 *            Die Staerke der HitAction.
	 * @param explosionFactory
	 *            Eine ExplosionFactory zum erstellen der Explosionen.
	 * @param debug
	 *            Der debug Status.
	 */
	public HitAction(int strenght, ExplosionFactory explosionFactory, boolean debug) {
		this.strength = strenght;
		this.explosionFactory = explosionFactory;
		this.debug = debug;
	}

	@Override
	public boolean act(float delta) {
		// Bestimme das Ziel dieser HitAction
		Actor target = this.getTarget();

		// Wenn das Ziel ILife implementiert
		if (ILife.class.isInstance(target)) {
			ILife life = (ILife) target;
			// Reduziere die Lebenspunkte des Ziels
			life.changeLife(-strength);

			// Erstelle eine Explosion und fuege sie dem EntityManager hinzu
			Entity explosion = explosionFactory.createExplosion(target.getX(), target.getY(), 1f,
					target.getWidth() * target.getScaleX(), target.getHeight() * target.getScaleY(), debug);
			getEntity().getManager().addEntity(explosion);

			// Wenn das Ziel keine Lebenspunkte mehr hat
			if (!life.hasLifeLeft()) {
				// Zerstoere das Ziel
				target.addAction(new DestroyEntityAction());
			}

			// Zerstoere den Besitzer, in der Regel ein Geschoss, nach der
			// Kollision
			getEntity().addAction(new DestroyEntityAction());
		}
		return true;
	}
}
