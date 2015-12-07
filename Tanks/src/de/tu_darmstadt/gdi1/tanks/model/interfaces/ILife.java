package de.tu_darmstadt.gdi1.tanks.model.interfaces;

public interface ILife {
	
	public void setMaxLife(int value);
	
	public void setLife(int value);
	
	public void changeLife(int life);
	
	public int getActualLife();
	
	public boolean hasLifeLeft();
	
	public int getMaxLife();
	

}
