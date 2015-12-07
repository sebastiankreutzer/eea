package de.tu.darmstadt.informatik.tanks2.highscore;

import java.util.Comparator;

public class HighscoreComparator implements Comparator<Highscore>{

	@Override
	public int compare(Highscore h1, Highscore h2) {
		
		// Same amount of shots fired
		if (h1.getFiredShots() == h2.getFiredShots()) {
			// Highscore 1 needed more time
			if(h1.getPassedTime() > h2.getPassedTime()) {
				return 1;
			}
			// Highscore 2 needed more time
			else if (h1.getPassedTime() < h2.getPassedTime()) {
				return -1;
			}
			// Both needed equal time
			else return h1.getPlayerName().compareTo(h2.getPlayerName());
		}
		// Highscore 1 fired more shots
		else if (h1.getFiredShots() > h2.getFiredShots()) {
			return 1;
		}
		// Highscore 2 fired more shots
		else {
			return -1;
		}
	}

}