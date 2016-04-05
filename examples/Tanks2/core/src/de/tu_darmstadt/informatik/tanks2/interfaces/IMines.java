package de.tu_darmstadt.informatik.tanks2.interfaces;

public interface IMines {
	
	public void setMaxMines(int value);
	
	public int getMaxMines();
	
	public void setMinesAmmo(int value);
	
	public void changeMinesAmmo(int value);
	
	public boolean hasMinesLeft();
	
	public int getMines();
}
