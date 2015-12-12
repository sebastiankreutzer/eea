package de.tu.darmstadt.informatik.eea.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicAction extends EEAAudioAction {

	boolean isPlaying = false;
	Music music;

	/**
	 * Creates a new MusicAction, used for playing long, looping audio files.
	 * @see de.tu.darmstadt.informatik.eea.action.EEAAudioAction#de.tu.darmstadt.informatik.eea.action.EEAAudioAction.EEAAudioAction(String path, float volume, float pan)
	 */
	public MusicAction(String path, float volume, float pan) {
		super(volume, pan);
		music = Gdx.audio.newMusic(Gdx.files.internal(path));
		music.setPan(pan, volume);
	}

	@Override
	public boolean act(float delta) {
		if(!music.isPlaying()) {
			music.setPan(pan, volume);
		}
		return true;
	}

	@Override
	public void setVolume(float volume) {
		super.setVolume(volume);
		music.setVolume(getVolume());
	}

	@Override
	public void setPan(float pan) {
		super.setPan(pan);
		music.setPan(getPan(), volume);
	}

}
