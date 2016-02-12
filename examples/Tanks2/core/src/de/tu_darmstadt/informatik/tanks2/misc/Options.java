package de.tu_darmstadt.informatik.tanks2.misc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.RWFile;

public class Options {
	
	public static enum Difficulty { EASY, NORMAL, HARD }
	
	private final RWFile file;
	//private final String fileName = "options";
	//private final String folderName = "options";
	
	// Difficulty settings
	private Difficulty difficulty;
	// Sound settings
	private boolean soundEnabled;
	
	public Options (IResourcesManager resourcesManager) {
		// Default setting
		difficulty = Difficulty.EASY;
		soundEnabled = true;
		
		file = resourcesManager.openRWFile("options");
		
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
	private void toggleSound() {
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
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(file.write(false));
			oos.writeObject(difficulty);
			oos.writeBoolean(soundEnabled);
			oos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * Loads the options
	 */
	public void load() {
		
		// Check if file exists to avoid exceptions
		if (file.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(file.read());
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
}

