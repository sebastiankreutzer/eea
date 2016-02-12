package de.tu_darmstadt.gdi1.tanks.tests.adapter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;

import de.tu_darmstadt.gdi1.tanks.model.GameplayLog;
import de.tu_darmstadt.gdi1.tanks.model.entities.Pickup;
import de.tu_darmstadt.gdi1.tanks.model.entities.ScatterShoot;
import de.tu_darmstadt.gdi1.tanks.model.entities.Tank;
import de.tu_darmstadt.gdi1.tanks.model.entities.Tower;
import de.tu_darmstadt.gdi1.tanks.model.map.Map;
import de.tu_darmstadt.gdi1.tanks.ui.Tanks;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class TanksTestAdapterExtended2 extends TanksTestAdapterExtended1 {

	/**
	 * Use this constructor to set up everything you need.
	 */
	public TanksTestAdapterExtended2() {
		super();
	}
	
	/* *************************************************** 
	 * **************** Tupel Scattershot ****************
	 * *************************************************** */

	/**
	 * Scattershot ist die Bezeichnung einen Streuschuss mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Streuschuesse muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Der erste Streuschuss ist an der Position 0. 
	 * 
	 * @param position : Streuschuss-Nummer
	 * @return Zeit des Streuschusses mit der uebergebenen Streuschuss-Nummer,
	 * nachdem sich der Schuss in mehrere aufteilt
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getScattershotTime(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<ScatterShoot> scattershots = new ArrayList<ScatterShoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Scattershot")) {
				scattershots.add((ScatterShoot) entity);
			}
		}
		
		if (position < 0 || position > scattershots.size() - 1)
			return -1;
		
		return (int) scattershots.get(position).getTime();
	}
	
	/**
	 * Scattershot ist die Bezeichnung einen Streuschuss mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Streuschuesse muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Der erste Streuschuss ist an der Position 0. 
	 * 
	 * @param position : Streuschuss-Nummer
	 * @return Staerke des Streuschusses mit der uebergebenen Streuschuss-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getScattershotStrength(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<ScatterShoot> scattershots = new ArrayList<ScatterShoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Scattershot")) {
				scattershots.add((ScatterShoot) entity);
			}
		}
		
		if (position < 0 || position > scattershots.size() - 1)
			return -1;
		
		return scattershots.get(position).getStreangth();
	}
	
	/**
	 * Scattershot ist die Bezeichnung einen Streuschuss mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Streuschuesse muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Der erste Streuschuss ist an der Position 0. 
	 * 
	 * @param position : Streuschuss-Nummer
	 * @return Rotation in Grad des Streuschusses mit der uebergebenen Streuschuss-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getScattershotRotation(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<ScatterShoot> scattershots = new ArrayList<ScatterShoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Scattershot")) {
				scattershots.add((ScatterShoot) entity);
			}
		}
		
		if (position < 0 || position > scattershots.size() - 1)
			return -1;
		
		return (int) scattershots.get(position).getRotation();
	}
	
	/**
	 * Scattershot ist die Bezeichnung einen Streuschuss mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Streuschuesse muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Der erste Streuschuss ist an der Position 0. 
	 * 
	 * @param position : Streuschuss-Nummer
	 * @return Skalierung des Streuschusses mit der uebergebenen Streuschuss-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getScattershotScale(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<ScatterShoot> scattershots = new ArrayList<ScatterShoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Scattershot")) {
				scattershots.add((ScatterShoot) entity);
			}
		}
		
		if (position < 0 || position > scattershots.size() - 1)
			return -1;
		
		return (int) (scattershots.get(position).getScale() * 100);
	}
	
	/**
	 * Scattershot ist die Bezeichnung einen Streuschuss mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Streuschuesse muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Der erste Streuschuss ist an der Position 0. 
	 * 
	 * @param position : Streuschuss-Nummer
	 * @return Aktuelle x-Koordinate des Streuschusses mit der uebergebenen Streuschuss-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getScattershotXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<ScatterShoot> scattershots = new ArrayList<ScatterShoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Scattershot")) {
				scattershots.add((ScatterShoot) entity);
			}
		}
		
		if (position < 0 || position > scattershots.size() - 1)
			return -1;
		
		return (int) scattershots.get(position).getPosition().x;
	}
	
	/**
	 * Scattershot ist die Bezeichnung einen Streuschuss mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Streuschuesse muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Der erste Streuschuss ist an der Position 0. 
	 * 
	 * @param position : Streuschuss-Nummer
	 * @return Aktuelle y-Koordinate des Streuschusses mit der uebergebenen Streuschuss-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getScattershotYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<ScatterShoot> scattershots = new ArrayList<ScatterShoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Scattershot")) {
				scattershots.add((ScatterShoot) entity);
			}
		}
		
		if (position < 0 || position > scattershots.size() - 1)
			return -1;
		
		return (int) scattershots.get(position).getPosition().y;
	}

	/**
	 * @return Anzahl der aktuell sich im Spiel befindenden Streuschuesse
	 */
	public int getScattershotCount() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Scattershot")) {
				count++;
			}
		}
		return count;
	}
	
	/* *************************************************** 
	 * ******************* Tupel Tower *******************
	 * *************************************************** */
	
	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Aktuelle x-Koordinate des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerMaximumLife(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return towers.get(position).getMaxLife();
	}
	
	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Aktuelle Lebenspunkte des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerActualLife(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return towers.get(position).getActualLife();
	}
	
	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Maximale Anzahl an Schuessen des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerMaximumShotAmmo(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return towers.get(position).getMaxShootAmmo();
	}
	
	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Aktuelle Anzahl an Schuessen des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerActualShotAmmo(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return towers.get(position).getActualShootAmmo();
	}
	
	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Staerke eines Schuesses des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerStrength(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return towers.get(position).getStreangth();
	}
	
	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Geschwindigkeit, mit der Schueese des Abwehrgeschuetzes 
	 * mit der uebergebenen Geschuetz-Nummer abgefeuert werden,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerSpeed(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return (int) (towers.get(position).getSpeed() * 100);
	}
	
	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Aktuelle Rotation in Grad des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerRotation(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return (int) towers.get(position).getRotation();
	}
	
	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Aktuelle Skalierung des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerScale(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return (int) (towers.get(position).getScale() * 100);
	}

	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Aktuelle x-Position des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return (int) towers.get(position).getPosition().x;
	}

	/**
	 * Tower ist die Bezeichnung ein Abwehrgeschuetz mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Abwehrgeschuetze muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Abwehrgeschuetz hat die Geschuetz-Nummer 0. 
	 * 
	 * @param position : Geschuetz-Nummer
	 * @return Aktuelle y-Position des Abwehrgeschuetzes mit der uebergebenen Geschuetz-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getTowerYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> towers = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				towers.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > towers.size() - 1)
			return -1;
		
		return (int) towers.get(position).getPosition().y;
	}

	/**
	 * @return Anzahl sich im Spiel befindender Abwehrgeschuetze
	 */
	public int getTowerCount() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tower")) {
				count++;
			}
		}
		return count;
	}
	
	/* *************************************************** 
	 * ****************** Tupel Pickup *******************
	 * *************************************************** */
	
	/**
	 * Pickup ist die Bezeichnung ein Gesundheits- oder Munitionspack mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Pickups muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Pickup hat die Pickup-Nummer 0. 
	 * 
	 * @param position : Pickup-Nummer
	 * @return Typ des Pickups mit der uebergebenen Pickup-Nummer,
	 * es gibt Gesundheits- und Munitions-Packs;
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public String getPickupType(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Pickup> pickups = new ArrayList<Pickup>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Pickup")) {
				pickups.add((Pickup) entity);
			}
		}
		
		if (position < 0 || position > pickups.size() - 1)
			return null;
		
		String pickupType = pickups.get(position).getID();
		
		return pickupType.substring(1, pickupType.length() -1);
	}
	
	/**
	 * Pickup ist die Bezeichnung ein Lebens- oder Munitionspack mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Pickups muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Pickup hat die Pickup-Nummer 0. 
	 * 
	 * @param position : Pickup-Nummer
	 * @return Staerke des Pickups mit der uebergebenen Pickup-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getPickupStrength(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		List<Pickup> pickups = new ArrayList<Pickup>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Pickup")) {
				pickups.add((Pickup) entity);
			}
		}
		
		if (position < 0 || position > pickups.size() - 1)
			return -1;
		
		return pickups.get(position).getStreangth();
	}
	
	/**
	 * Pickup ist die Bezeichnung ein Lebens- oder Munitionspack mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Pickups muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Pickup hat die Pickup-Nummer 0. 
	 * 
	 * @param position : Pickup-Nummer
	 * @return Aktuelle Rotation in Grad des Pickups mit der uebergebenen Pickup-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getPickupRotation(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Pickup> pickups = new ArrayList<Pickup>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Pickup")) {
				pickups.add((Pickup) entity);
			}
		}
		
		if (position < 0 || position > pickups.size() - 1)
			return -1;
		
		return (int) pickups.get(position).getRotation();
	}
	
	/**
	 * Pickup ist die Bezeichnung ein Lebens- oder Munitionspack mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Pickups muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Pickup hat die Pickup-Nummer 0. 
	 * 
	 * @param position : Pickup-Nummer
	 * @return Aktuelle Skalierung des Pickups mit der uebergebenen Pickup-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getPickupScale(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Pickup> pickups = new ArrayList<Pickup>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Pickup")) {
				pickups.add((Pickup) entity);
			}
		}
		
		if (position < 0 || position > pickups.size() - 1)
			return -1;
		
		return (int) (pickups.get(position).getScale() * 100);
	}
	
	/**
	 * Pickup ist die Bezeichnung ein Lebens- oder Munitionspack mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Pickups muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Pickup hat die Pickup-Nummer 0. 
	 * 
	 * @param position : Pickup-Nummer
	 * @return Aktuelle x-Position des Pickups mit der uebergebenen Pickup-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getPickupXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		List<Pickup> pickups = new ArrayList<Pickup>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Pickup")) {
				pickups.add((Pickup) entity);
			}
		}
		
		if (position < 0 || position > pickups.size() - 1)
			return -1;
		
		return (int) pickups.get(position).getPosition().x;
	}
	
	/**
	 * Pickup ist die Bezeichnung ein Lebens- oder Munitionspack mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Pickups muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. Das erste Pickup hat die Pickup-Nummer 0. 
	 * 
	 * @param position : Pickup-Nummer
	 * @return Aktuelle y-Position des Pickups mit der uebergebenen Pickup-Nummer,
	 * wenn die Nummer ungegueltig ist, dann wird -1 zurueckgegeben.
	 */
	public int getPickupYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		List<Pickup> pickups = new ArrayList<Pickup>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Pickup")) {
				pickups.add((Pickup) entity);
			}
		}
		
		if (position < 0 || position > pickups.size() - 1)
			return -1;
		
		return (int) pickups.get(position).getPosition().y;
	}

	/**
	 * @return Anzahl der sich aktuell im Spiel befindenden Pickups
	 */
	public int getPickupCount() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Pickup")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * @return Gibt das Zeitlimit dieser Karte zurueck,
	 * angegeben in dem Tupel Map in der Kartendatei.
	 */
	
	/**
	 * Returns the time limit of the map.
	 * This value is specified in the map file.
	 * @return
	 */
	public int getTimeLimit() {
		if (tanks == null) return -1;
		return (int) GameplayLog.getInstance().getTimeLimit();
	}
	
	/**
	 * Returns the maximum amount of shots the player starts with.
	 * This value is specified in the map file.
	 * @return
	 */
	public int getLimitedAmmoAmount() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		Tank tank = null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerOne")) {
				tank = (Tank) entity;
				break;
			}
		}
		
		if (tank == null)
			return -1;
		
		return tank.getMaxShootAmmo();
	}
	
	/**
	 * Return the actual amount of shot the player currently has.
	 * This value should change when the player shoots.
	 * This value is specified in the map file. 
	 * @return
	 */
	public int getAmmoLeft() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (tanks != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Tanks.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		Tank tank = null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerOne")) {
				tank = (Tank) entity;
				break;
			}
		}
		
		if (tank == null)
			return -1;
		
		return tank.getActualShootAmmo();
	}
	
	/* *************************************************** 
	 * ********************** Input **********************
	 * *************************************************** */
	
	/**
	 * This Method should emulate the pressing of the l key.
	 * This should fire a scattershot.
	 */
	public void handleKeyPressL() {
		handleKeyPressed(0, Input.KEY_L);
	}
	
}
