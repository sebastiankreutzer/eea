package de.tu.darmstadt.informatik.tanks2.interfaces;

public interface IShootAmmo {
	
	public void setShootMaxAmmo(int value);
	public void changeShootAmmo(int value);
	public void setShootAtmmo(int value);
	public boolean hasShootAmmo();
	public int getMaxShootAmmo();
	public int getActualShootAmmo();

}
