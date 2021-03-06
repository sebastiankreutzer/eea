package de.tu_darmstadt.informatik.eea.action;

/**
 * Audio action template with volume and pan.
 * @author Johann Reinhard
 * @version 1.0
 */
public abstract class EEAAudioAction extends EEAAction {
	
	protected float volume;
	protected float pan;

	/**
	 * Creates a new AudioAction.
	 * @param path The path to the sound file.
	 * @param volume The volume between 0 and 1 (full).
	 * @param pan The pan between -1 (left) and 1 (right), default 0 (center).
	 */
	public EEAAudioAction(float volume, float pan) {
		setVolume(volume);
		setPan(pan);
	}
	
	/**
	 * Sets the volume of this audio.
	 * @param volume The volume between 0 and 1 (full).
	 */
	public void setVolume(float volume) {
		this.volume = Math.min(1f, Math.max(0f, volume));
	}
	
	/**
	 * Returns the volume of this audio.
	 * @return The volume between 0 and 1 (full).
	 */
	public float getVolume() {
		return volume;
	}
	
	/**
	 * Sets the pan of this audio.
	 * @param pan The pan between -1 (left) and 1 (right), default 0 (center).
	 */
	public void setPan(float pan) {
		this.pan = Math.min(1f, Math.max(-1f, pan));
	}
	
	/**
	 * Returns the pan of this audio.
	 * @return The pan between -1 (left) and 1 (right), default 0 (center).
	 */
	public float getPan() {
		return pan;
	}

}