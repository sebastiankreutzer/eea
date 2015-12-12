package de.tu.darmstadt.informatik.tanks2.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Options {
	
	public static enum Difficulty { EASY, NORMAL, HARD }

	private static final String fileName = "options";
	private static final String folderName = "options";
	
	private static Options options = new Options();
	
	// Difficulty settings
	private Difficulty difficulty;
	// Sound settings
	private boolean soundEnabled;
	
	private Options () {
		// Default setting
		difficulty = Difficulty.EASY;
		soundEnabled = true;
		
		// Load saved settings if existent
		this.load();
	}
	
	/**
	 * Sets the difficulty
	 * @param difficulty difficulty to be set
	 */
	public void setDifficulty (Difficulty difficulty) {
		this.difficulty = difficulty;
		// Speichern
		save();
	}
	
	/**
	 * Returns the difficulty settings
	 * @return difficulty settings
	 */
	public String getDifficulty() {
		return difficulty.toString();
	}
	
	/**
	 * Toggles the sound dependent on sound settings
	 * @throws SlickException 
	 */
	public void toggleSound() {
		// TODO Sound
//		if(soundEnabled) {
//			bgm = new Sound("assets/theme.ogg");
//			bgm.loop();
//			//bgm.play();
//		} else if(bgm != null) {
//			bgm.stop();
//		}
	}
	
	/**
	 * Returns if the sound is enabled
	 * @return true if the sound is enables otherwise false
	 */
	public boolean isSoundEnabled() {
		return soundEnabled;
	}
	
	/**
	 * Enables the sound
	 */
	public void enableSound() {
		soundEnabled = true;
		toggleSound();
		save();
	}
	
	/**
	 * Disables the sound
	 */
	public void disableSound() {
		soundEnabled = false;
		toggleSound();
		save();
	}

	/**
	 * Saves the Options
	 */
	public void save() {
		
		File f = new File(folderName);
		
		// Ordner erstellen falls noch nicht vorhanden
		if(!f.exists()) {
			f.mkdir();
		}
		
		try {
			FileOutputStream file = new FileOutputStream(folderName + "/" + fileName);
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(difficulty);
			oos.writeBoolean(soundEnabled);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Loads the options
	 */
	public void load() {
		
		File f = new File(folderName + "/" + fileName);
		
		// Check if file exists to avoid exceptions
		if (f.exists()) {
			try {
				FileInputStream file = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(file);
				this.difficulty = (Difficulty) ois.readObject();
				this.soundEnabled = (boolean) ois.readBoolean();
				ois.close();
			} catch (FileNotFoundException e) {
				System.out.println("No options file found.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Returns the single instance of Options 
	 * @return single instance of Options
	 */
	public static Options getInstance() {
		return options;
	}
}

