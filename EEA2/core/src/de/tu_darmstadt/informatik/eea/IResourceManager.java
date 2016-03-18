package de.tu_darmstadt.informatik.eea;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface IResourceManager {
	public ROMFile openROMFile(String path);
	public RWFile openRWFile(String path);
	
	public Texture getTexture(String path);
	public void loadTextureAsync(String path);
	public Sound getSound(String path);
	public void loadSoundAsync(String path);
	public Music getMusic(String path);
	public void loadMusicAsync(String path);
	
	public void update();
}
