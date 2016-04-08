package de.tu_darmstadt.informatik.tanks2.factories;

import de.tu_darmstadt.informatik.eea.component.collision.BorderCollisionComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * Eine Factory zum Erzeugen der Spielfeldgrenzen.
 * @author jr
 *
 */
public class BorderFactory {
	
	private float width, height;
	
	/**
	 * Erzeugt eine Factory fuer die Begrenzungen eines Spielfeldes.
	 * @param width Die Breite des Spielfeldes
	 * @param height Die Hoehe des Spielfeldes
	 */
	public BorderFactory(float width, float height){
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Erzeugt eine Entity mit einer BorderCollisionComponent fuer eine gegebene Richtung. Die Entity wird korekt positioniert.
	 * @param border Die Richtung der Begrenzung
	 * @return Eine Entity mir BorderCollisionComponent
	 */
	public Entity createBorder(BorderCollisionComponent.Border border) {
		// Erzeuge eine Entity und fuege die BorderCollisionComponent hinzu
		Entity entity = new Entity("Border"+border.toString());
		entity.addComponent(new BorderCollisionComponent(border));
		// Setzte die korrekte Position je nach Richtung
		switch (border) {
		case RIGHT:
			entity.setPosition(width, 0);
			break;
		case TOP:
			entity.setPosition(0, height);
			break;
		default:
			entity.setPosition(0, 0);
			break;
		}
		entity.setVisible(false);
		
		return entity;
	}
	
}
