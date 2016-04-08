package de.tu_darmstadt.informatik.tanks2.AI;

import de.tu_darmstadt.informatik.eea.action.EEAMovement;
import de.tu_darmstadt.informatik.eea.action.RotateAction;
import de.tu_darmstadt.informatik.eea.component.EEAComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;
import de.tu_darmstadt.informatik.eea.event.MovementDoesNotCollideEvent;
import de.tu_darmstadt.informatik.tanks2.interfaces.ISpeed;
import de.tu_darmstadt.informatik.tanks2.interfaces.IStrength;

/**
 * Eine abstrakte AI mit einer per ID String definierten Ziel.
 * 
 * @author jr
 *
 */
public abstract class AI extends EEAComponent {

	protected boolean debug;

	protected String targetName;
	protected Entity target;

	private MovementDoesNotCollideEvent event;
	protected float speed = 0, strength = 0;

	/**
	 * Erzeugt eine neue AI.
	 * 
	 * @param componentID
	 *            Die ComponentID dieser AI
	 * @param target
	 *            Das Ziel dieser AI
	 * @param debug
	 *            Der Debugmodus
	 */
	public AI(String componentID, String target, boolean debug) {
		super(componentID);
		this.targetName = target;
		this.debug = debug;
		// Erstelle ein Event zur Kollisionsdetektierung
		event = new MovementDoesNotCollideEvent(null);
	}

	@Override
	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
		// Bestimme die Geschwindigkeit und Staerke der Besitzer Entity
		if (owner instanceof ISpeed)
			speed = ((ISpeed) owner).getSpeed();
		if (owner instanceof IStrength)
			strength = ((IStrength) owner).getStrength();
		// Registriere das Event zur Kollisionsdetektierung
		owner.addComponent(event);
	}

	@Override
	public boolean update(float delta) {
		// Bestimme den naechsten Schritt, falls ein Ziel vorliegt
		if (target != null || findTarget()) {
			event.setMovement(calculateNextMove());
		}
		return true;
	}

	/**
	 * Bestimme die naechste Aktion welche die AI ausfuehrt.
	 * 
	 * @return Eine {@link EEAMovement} Aktion
	 */
	protected abstract EEAMovement calculateNextMove();

	/**
	 * Sucht im registrierten EntityManager nach einer Entity mit dem Namen des
	 * Zielobjektes. Setzt diese als das Ziel dieser AI.
	 * 
	 * @return true falls eine Entity gefunden wurde, andernfalls false
	 */
	protected boolean findTarget() {
		target = owner.getManager().getEntity(targetName);
		if (target != null)
			return true;
		return false;
	}

	/**
	 * Gibt eine RotateAction zurueck, die diese Entity auf das Ziel ausrichtet.
	 * 
	 * @param deltaRotation
	 *            Die Differenz zwischen momentaner und gewuenschter Rotation.
	 * @return Eine {@link RotateAction}
	 */
	protected RotateAction determineRotateAction(float deltaRotation) {
		if (deltaRotation < 180)
			return new RotateAction(-speed);
		else
			return new RotateAction(speed);
	}

	/**
	 * Bestimmt die Differenz zwischen der aktuellen Rotation und der Richtung
	 * Ziel.
	 * 
	 * @return Die Rotation zum Ziel zwischen 0 und 360 Grad.
	 */
	protected float calculateDeltaRotation() {
		// Bestimme x und y Differenzen
		float deltaX = owner.getX() - target.getX();
		float deltaY = owner.getY() - target.getY();
		// Bestimme die Rotation aus dem ArcusTangens und korrigiere fuer 0 Grad
		float rotationToTarget = (float) Math.toDegrees(Math.atan2(deltaY, deltaX)) + 90;
		// Beschraenke die Rueckgabe auf 0 bis 360 Grad
		return ((owner.getRotation() - rotationToTarget) % 360 + 360) % 360;
	}

}
