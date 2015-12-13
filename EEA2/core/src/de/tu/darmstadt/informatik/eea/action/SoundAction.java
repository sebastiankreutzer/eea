package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * This class provides an action that triggers the play back of a Sound file. A Sound file is a
 * small (< 1 MB) audio file that is played only once per play back (it does not loop). The audio is played
 * every time this action acts and as a result, the registered audio file can be played multiple times 
 * simultaneously.
 * @author Johann Reinhard
 * @version 1.0
 */
public class SoundAction extends EEAAudioAction {
	
	protected String path;
	protected long audioID;
	protected float pitch;
	
	/**
	 * Creates a new SoundAction, used for short and small audio files (< 1 MB) and no loop playback.
	 * @param file The path to the sound file.
	 */
	public SoundAction(String file) {
		this(file, 1);
	}
	
	/**
	 * Creates a new SoundAction, used for short and small audio files (< 1 MB) and no loop playback.
	 * @param file The path to the sound file.
	 * @param volume The volume between 0 and 1 (full).
	 */
	public SoundAction(String file, float volume){
		this(file, volume, 1, 1);
	}
	
	/**
	 * Creates a new SoundAction, used for short and small audio files (< 1 MB) and no loop playback.
	 * @param file The path to the sound file.
	 * @param volume The volume between 0 and 1 (full).
	 * @param pitch The pitch between 0.5 (slow) and 2 (fast), default 1.
	 * @param pan The pan between -1 (left) and 1 (right), default 0 (center).
	 */
	public SoundAction(String file, float volume, float pitch, float pan) {
		super(volume, pan);
		this.path = file;
		this.pitch = Math.min(2f, Math.max(0.5f, pitch));
	}

	/**
	 * Plays the sound. If the sound is already playing, it will be played again, concurrently.
	 * @return true if the sound could successfully be played, otherwise false.
	 */
	@Override
	public boolean act(float delta) {
		Sound s = Gdx.audio.newSound(Gdx.files.internal(path));
		audioID = s.play(volume, pitch, pan);
		
		if(audioID == -1) return false;
		return true;
	}

}
