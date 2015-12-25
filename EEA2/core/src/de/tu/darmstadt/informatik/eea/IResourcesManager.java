package de.tu.darmstadt.informatik.eea;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface IResourcesManager {
	public Texture getTexture(String path);
	public void loadTextureAsync(String path);
	public Sound getSound(String path);
	public void loadSoundAsync(String path);
	public Music getMusic(String path);
	public void loadMusicAsync(String path);
	
	public void update();
}
