package de.tu.darmstadt.informatik.tanks2.interfaces;

public interface ILife {
	
	public void setMaxLife(int value);
	
	public void setLife(int value);
	
	public void changeLife(int life);
	
	public int getActualLife();
	
	public boolean hasLifeLeft();
	
	public int getMaxLife();
	

}
