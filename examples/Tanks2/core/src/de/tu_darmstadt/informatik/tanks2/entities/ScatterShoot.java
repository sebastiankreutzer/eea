package de.tu_darmstadt.informatik.tanks2.entities;



public class ScatterShoot extends Shoot{
	
	protected float time;

	public ScatterShoot(String id, int strength, float time) {
		super(id, strength);
		this.time = time;
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder("Scattershot ");
		sb.append(time);
		sb.append(super.toString().substring(4));
		return sb.toString();
		
	}

	public float getTime() {
		return this.time;
	}
}
