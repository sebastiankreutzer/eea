package de.tu_darmstadt.informatik.tanks2.misc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.RWFile;

/**
 * Speichert und Verwaltet Informationen ueber die vom Spieler gewuenschte
 * Verhaltensweise des Spiels bezuegliche Tons und Schwierigkeitsgrad.
 * 
 * @author jr
 *
 */
public class Options {

	public static enum Difficulty {
		EASY, NORMAL, HARD
	}

	private static Options instance = null;
	private final RWFile file;

	private Difficulty difficulty;
	private boolean soundEnabled;

	/**
	 * Erzeugt eine neue Options Instanz. Es wird versucht die vorherigen
	 * Optionen wieder herzustellen, ansonsten werden die Standardwerte gesetzt.
	 */
	private Options() {
		file = EEAGame.getResourceManager().openRWFile("options");
		try {
			load();
		} catch (IOException e) {
			System.out.println("Could not load options.");
			difficulty = Difficulty.EASY;
			soundEnabled = true;
		}
	}

	public static Options getInstance() {
		if (instance == null) {
			instance = new Options();
		}
		return instance;
	}

	/**
	 * Setzt den Schwierigkeitsgrad auf einen Wert aus
	 * {@link Options.Difficulty}.
	 * 
	 * @param difficulty
	 *            Der neue Schwierigkeitsgrad.
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
		save();
	}

	/**
	 * Gibt den Namen des aktuellen Schwierigkeitsgrades zurueck.
	 * 
	 * @return Den Namen des Schwierigkeitsgrades.
	 */
	public String getDifficulty() {
		return difficulty.toString();
	}

	/**
	 * @return true Wenn der Ton eingeschaltet ist, ansonsten false.
	 */
	public boolean isSoundEnabled() {
		return soundEnabled;
	}

	/**
	 * Schaltet den Ton ein.
	 */
	public void enableSound() {
		soundEnabled = true;
		save();
	}

	/**
	 * Schaltet den Ton aus.
	 */
	public void disableSound() {
		soundEnabled = false;
		save();
	}

	/**
	 * Speichert die aktuellen Optionen.
	 */
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(file.write(false));
			oos.writeObject(difficulty);
			oos.writeBoolean(soundEnabled);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Laed die gespeicherten Optionen.
	 * 
	 * @throws IOException
	 *             Wenn die Optionen nicht geladen werden konnten.
	 */
	public void load() throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(file.read());
			this.difficulty = (Difficulty) ois.readObject();
			this.soundEnabled = (boolean) ois.readBoolean();
			ois.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
