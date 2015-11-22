package de.tu.darmstadt.informatik.tanks2.entities;



public class ScatterShoot extends Shoot{
	
	protected long time;

	public ScatterShoot(String id, int strength, long time) {
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

	public long getTime() {
		return this.time;
	}
}
