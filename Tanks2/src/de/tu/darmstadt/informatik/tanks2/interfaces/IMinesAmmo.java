package de.tu.darmstadt.informatik.tanks2.interfaces;

public interface IMinesAmmo {
	
	public void setMinesMaxAmmo(int value);
	
	public void changeMinesAmmo(int value);
	
	public void setMinesAmmo(int value);
	
	public boolean hasMinesLeft();
	
	public int getActualMinesAmmo();
	
	public int getMaxMinesAmmo();
}
