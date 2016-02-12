package de.tu_darmstadt.gdi1.tanks.model.interfaces;

public interface IShootAmmo {
	
	public void setShootMaxAmmo(int value);
	public void changeShootAmmo(int value);
	public void setShootAtmmo(int value);
	public boolean hasShootAmmo();
	public int getMaxShootAmmo();
	public int getActualShootAmmo();

}
