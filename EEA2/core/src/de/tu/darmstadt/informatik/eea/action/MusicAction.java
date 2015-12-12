package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * This class provides an action that triggers the play back of a Music file. A Music file is a
 * long audio file that is played repeatedly (it does loop forever). The audio is played
 * the first time this action acts and as a result, the registered audio file cannot be played multiple times 
 * simultaneously.
 * @author Johann Reinhard
 * @version 1.0
 */
public class MusicAction extends EEAAudioAction {

	boolean isPlaying = false;
	Music music;
	
	/**
	 * Creates a new MusicAction, used for playing long, looping audio files.
	 * @param path The path to the file.
	 */
	public MusicAction(String path) {
		this(path, 1, 1);
	}

	/**
	 * Creates a new MusicAction, used for playing long, looping audio files.
	 * @param path The path to the file.
	 * @param volume The volume between 0 and 1 (full).
	 */
	public MusicAction(String path, float volume) {
		this(path, volume, 1);
	}

	/**
	 * Creates a new MusicAction, used for playing long, looping audio files.
	 * @param path The path to the file.
	 * @param volume The volume between 0 and 1 (full).
	 * @param pan The pan between -1 (left) and 1 (right), default 0 (center).
	 */
	public MusicAction(String path, float volume, float pan) {
		super(volume, pan);
		music = Gdx.audio.newMusic(Gdx.files.internal(path));
		music.setPan(pan, volume);
	}

	/**
	 * Starts the play back of the music stream. In case the stream was paused this will resume 
	 * the play back. In case the music stream is finished playing this will restart the play back.
	 * @return true
	 */
	@Override
	public boolean act(float delta) {
		if(!music.isPlaying()) {
			music.play();
		}
		return true;
	}

	@Override
	public void setVolume(float volume) {
		super.setVolume(volume);
		if(music != null) music.setVolume(getVolume());
	}

	@Override
	public void setPan(float pan) {
		super.setPan(pan);
		if(music != null) music.setPan(getPan(), volume);
	}

}
