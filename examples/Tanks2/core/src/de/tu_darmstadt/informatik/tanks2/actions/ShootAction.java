package de.tu_darmstadt.informatik.tanks2.actions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.tanks2.factories.ShotFactory;
import de.tu_darmstadt.informatik.tanks2.interfaces.IAmmunition;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;
import de.tu_darmstadt.informatik.tanks2.misc.GameplayLog;

/**
 * Eine Action welche einen Schuss erzeugt und initialisiert.
 * 
 * @author jr
 *
 */
public class ShootAction extends EEAMovement {

	protected ShotFactory shotFactory;

	/**
	 * Erzeugt eine neue ShootAction.
	 * 
	 * @param shotFactory
	 *            Die ShotFactory fuer den Schuss.
	 */
	public ShootAction(ShotFactory shotFactory) {
		this(shotFactory, false);
	}

	/**
	 * Erzeugt eine neue ShootAction.
	 * 
	 * @param shotFactory
	 *            Die ShotFactory fuer den Schuss.
	 * @param debug
	 *            Der Debugmodus
	 */
	public ShootAction(ShotFactory shotFactory, boolean debug) {
		this.shotFactory = shotFactory;
	}

	@Override
	public boolean act(float delta) {
		Entity owner = getEntity();
		if (owner instanceof IAmmunition) {
			// Reduziere die Munition
			((IAmmunition) owner).changeAmmunition(-1);
			// Bestimme Schaden, Rotation und Skalierung und erzeuge den Schuss
			int strength = ((IStrength) owner).getStrength();
			float rotation = owner.getRotation();
			float scale = owner.getScaleX();
			Entity bullet = createShot(owner.getName(), strength, rotation, scale);
			// Konvertiere die Rotation und kalkuliere Sinus und Cosinus
			rotation = (float) (Math.PI * rotation / 180);
			float sin = (float) Math.sin(rotation);
			float cos = (float) Math.cos(rotation);
			// Bestimme die Groesse der Positionskorrektur
			float h = (owner.getScaledHeight() + bullet.getScaledHeight() + 2f) * 0.5f;
			// Wende die Korrektur an uns setze die neue Position
			float x = owner.getX(Align.center) - sin * h;
			float y = owner.getY(Align.center) + cos * h;
			bullet.setPosition(x, y, Align.center);

			owner.getManager().addEntity(bullet);
			if (owner.getName().equals("\"PlayerOne\"")) {
				GameplayLog.getInstance().incrementNumberOfShots(1);
			}
		}
		return true;
	}

	/**
	 * Erzeugt eine Entity in einer ShotFactory.
	 * 
	 * @param name
	 *            Der Name des Schuetzen
	 * @param damage
	 *            Der Schaden
	 * @param rotation
	 *            Die Rotation
	 * @param scale
	 *            Die Skalierung
	 * @return
	 */
	protected Entity createShot(String name, int damage, float rotation, float scale) {
		return shotFactory.createShot(0, 0, name, damage, rotation, scale);
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		return new Vector2(getActor().getX(), getActor().getY());
	}

}
