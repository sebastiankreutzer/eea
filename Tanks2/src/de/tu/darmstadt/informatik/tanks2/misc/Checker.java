package de.tu.darmstadt.informatik.tanks2.misc;

import java.util.List;

import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.tanks2.exceptions.SemanticException;
import de.tu.darmstadt.informatik.tanks2.interfaces.IMap;
import temp.removeASAP.Tanks;

public class Checker {
	
	private IMap map;
	
	public Checker(IMap map){
		this.map = map;
	}
	
	public IMap check() throws SemanticException {
		int playerCount = 0;
		int OpponentCount = 0;
		
		List<Entity> localmap = map.getEntities();
		
		
		for(Entity entity : localmap){
			if(entity.getID().startsWith(Tanks.player1)){
				playerCount++;
				if(checkForCollision(entity, localmap)){
					throw new SemanticException("PlayerOne colides with an other Entity in the map");
				}
			}
			if(entity.getID().startsWith(Tanks.opponentTank) || entity.getID().startsWith(Tanks.player2)){
				OpponentCount++;
				if(checkForCollision(entity, localmap)){
					throw new SemanticException(entity.getID() +" colides with an other Entity in the map");
				}
			}
		}
		
		
		
		if(playerCount > 1){
			throw new SemanticException("More than 1 Player has been declared");
		}
		if(playerCount == 0){
			throw new SemanticException("No Player has been declared");
		}
		
		if(OpponentCount == 0){
			throw new SemanticException("No Opponent has been declared");
		}
		
		return map;
	}
	
	private boolean checkForCollision(Entity entity, List<Entity> entities){
		for(Entity OtherEntity : entities){
			if( (OtherEntity.getID().startsWith("Wall") || OtherEntity.getID().startsWith(Tanks.opponentTank) || OtherEntity.getID().startsWith(Tanks.player1) 
					|| OtherEntity.getID().startsWith(Tanks.player2)) 
					&& entity.collides(OtherEntity)) return true;
			
			//if(entity.colides(OtherEntity)) return true;
		}
		return false;
		
	}

}
