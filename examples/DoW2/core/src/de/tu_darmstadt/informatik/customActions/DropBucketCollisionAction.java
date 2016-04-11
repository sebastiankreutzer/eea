package de.tu_darmstadt.informatik.customActions;

import de.tu_darmstadt.informatik.dow2.GameplayState;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;
import de.tu_darmstadt.informatik.eea.action.EEAAction;
import de.tu_darmstadt.informatik.eea.action.SoundAction;
import de.tu_darmstadt.informatik.eea.entity.Entity;

public class DropBucketCollisionAction extends EEAAction{

	private Entity bucket;
	private GameplayState gameplayState;

	public DropBucketCollisionAction(Entity bucket, GameplayState gameplayState) {
		this.bucket = bucket;
		this.gameplayState = gameplayState;
	}

	/**
	 * Prüft, ob sich die Entity (in diesem Falle ein Tropfen) mit dem bucket Kollidieren und behandelt diesen Fall.
	 */
	@Override
	public boolean act(float delta) {
		// getTarget() gibt das kollidierte Entity zurück. Beachte, wenn mit 2 Entities eine Kollision stattfindet, dann wird die Action 2x ausgeführt.
		if ( getTarget() == bucket ){
			// erzeuge einen Sound (keine Sorge der aufruf ist gecached ;) )
			SoundAction fetchDropSound = new SoundAction("SlimeSplash.mp3");
			// spiele den Sound ab
			getEntity().addAction(fetchDropSound);
			
			// führe die Destroy-Action auf dem Tropfen aus. getEntity() gibt den der Action zugeordneten Tropfen.
			getEntity().addAction(new DestroyEntityAction());
			
			// erhöhe den Score
			gameplayState.setScore(gameplayState.getScore() + 1);
			gameplayState.scoreRenderComponent.setText(gameplayState.getScore()+" Drops catched");
		}

		return true;
	}

}
