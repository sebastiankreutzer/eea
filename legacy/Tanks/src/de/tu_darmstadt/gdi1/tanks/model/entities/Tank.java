package de.tu_darmstadt.gdi1.tanks.model.entities;

import de.tu_darmstadt.gdi1.tanks.model.interfaces.IMinesAmmo;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.IShootAmmo;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.ILife;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.ISpeed;
import de.tu_darmstadt.gdi1.tanks.model.interfaces.IStreangth;
import eea.engine.entity.Entity;

public class Tank extends Entity implements ILife, IStreangth, IShootAmmo, IMinesAmmo, ISpeed{
	
								// Name (gegeben ueber Entity)
	
	
	private int maxLife;		// Leben zu Beginn des Spiels
	private int life;			// aktuelles Leben
	
	private int shootMaxAmmo;	// maximal moegliche Anzahl an Schuessen
	private int shootAmmo;		// aktuell verfuegbare Schuesse
	
	private int minesMaxAmmo;	// maximal moegliche Anzahl an Minen
	private int minesAmmo;		// aktuell verfuegbare Minen
	
	private int strength;		// Schussstaerke
	
								// aktuelle Drehung in Grad
								// Skalierung
	
								// Position (x-Koordinate)
								// Position (y-Koordinate)
	
	private float speed;
	

	public int getActualLife() {
		return life;
	}


	public void setLife(int life) {
		if(life > maxLife) this.life = maxLife;
		else this.life = life;
	}


	




	public Tank(String id) {
		super(id);
		life = 0;
		maxLife = 0;
		strength = 0;
		shootAmmo = 0;
		shootMaxAmmo = 0;
		minesAmmo = 0;
		minesMaxAmmo = 0;
		speed = 0;
		
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("Tank ");
		sb.append(this.getID());
		sb.append(" ");
		sb.append(this.maxLife);
		sb.append(" ");
		sb.append(this.life);
		sb.append(" ");
		sb.append(this.shootMaxAmmo);
		sb.append(" ");
		sb.append(this.shootAmmo);
		sb.append(" ");
		sb.append(this.minesMaxAmmo);
		sb.append(" ");
		sb.append(this.minesAmmo);
		sb.append(" ");
		sb.append(this.strength);
		sb.append(" ");
		sb.append((int)(this.getSpeed()*100));
		sb.append(" ");
		sb.append((int)this.getRotation());
		sb.append(" ");
		sb.append((int)(this.getScale() *100));
		sb.append(" ");
		sb.append((int)this.getPosition().x);
		sb.append(" ");
		sb.append((int)this.getPosition().y);
		
		return sb.toString();
		
	}



	@Override
	public void changeStreangth(int value) {
		strength += value;
		if(strength < 0) strength = 0;
		
	}


	@Override
	public int getStreangth() {
		return strength;
	}


	@Override
	public void setStreangth(int streangth) {
		this.strength = streangth;
	}


	@Override
	public void changeShootAmmo(int value) {
		shootAmmo +=  value;
		if(shootAmmo < 0) shootAmmo = 0;
		if(shootAmmo > shootMaxAmmo) shootAmmo = shootMaxAmmo;
	}


	@Override
	public void setShootAtmmo(int value) {
		if(value < 0) shootAmmo = 0;
		else if (value > shootMaxAmmo) shootAmmo = shootMaxAmmo;
		else shootAmmo = value;
		
	}


	@Override
	public boolean hasShootAmmo() {
		return shootAmmo > 0;
	}


	@Override
	public void changeMinesAmmo(int value) {
		minesAmmo += value;
		if(minesAmmo < 0) minesAmmo = 0;
		if(minesAmmo > minesMaxAmmo) minesAmmo = minesMaxAmmo;
		
	}


	@Override
	public void setMinesAmmo(int value) {
		if(value < 0) minesAmmo = 0;
		else if(value > minesMaxAmmo) minesAmmo = minesMaxAmmo;
		else minesAmmo = value;
		
	}


	@Override
	public boolean hasMinesLeft() {
		return minesAmmo > 0;
	}


	@Override
	public void setMinesMaxAmmo(int value) {
		this.minesMaxAmmo = value;
		
	}


	@Override
	public void setShootMaxAmmo(int value) {
		this.shootMaxAmmo = value;
	}
	
	@Override
	public void setMaxLife(int value) {
		this.maxLife = value;
		
	}

	@Override
	public void changeLife(int life) {
		this.life += life;
		if(this.life < 0) this.life = 0;
		else if(this.life > maxLife) this.life = maxLife;
		
	}


	@Override
	public boolean hasLifeLeft() {
		return life > 0;
	}


	@Override
	public int getMaxLife() {
		return maxLife;
	}


	@Override
	public int getMaxShootAmmo() {
		return shootMaxAmmo;
	}


	@Override
	public int getActualShootAmmo() {
		return shootAmmo;
	}


	@Override
	public int getActualMinesAmmo() {
		return minesAmmo;
	}


	@Override
	public int getMaxMinesAmmo() {
		return minesMaxAmmo;
	}


	@Override
	public void setSpeed(float speed) {
		this.speed = speed;
		
	}


	@Override
	public float getSpeed() {
		return this.speed;
	}
	

}
