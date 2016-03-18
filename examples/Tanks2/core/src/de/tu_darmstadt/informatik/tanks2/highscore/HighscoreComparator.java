package de.tu_darmstadt.informatik.tanks2.highscore;

import java.util.Comparator;

/**
 * Ein Comparator zum Vergleichen von Highscores.
 * 
 * @author jr
 *
 */
public class HighscoreComparator implements Comparator<Highscore> {

	@Override
	public int compare(Highscore h1, Highscore h2) {

		// Vergleiche Zeit, wenn gleiche Anzahl an Schuessen
		if (h1.getFiredShots() == h2.getFiredShots()) {
			// Highscore1 oder Highscore2 benoetigte mehr Zeit
			if (h1.getPassedTime() > h2.getPassedTime()) {
				return 1;
			} else if (h1.getPassedTime() < h2.getPassedTime()) {
				return -1;
			}
			// Beide benoetigten gleich viel Zeit
			else
				return h1.getPlayerName().compareTo(h2.getPlayerName());
		}
		// Highscore1 oder Highscore2 hat mehr geschossen
		else if (h1.getFiredShots() > h2.getFiredShots()) {
			return 1;
		} else {
			return -1;
		}
	}

}