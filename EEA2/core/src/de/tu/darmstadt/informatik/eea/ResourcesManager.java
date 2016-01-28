package de.tu.darmstadt.informatik.eea;

import java.io.File;
import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ResourcesManager implements IResourcesManager {
	private AssetManager assetManager = new AssetManager();

	public ResourcesManager() {
		assetManager = new AssetManager();
	}
	
	public static File loadFile(String path) {
		return Gdx.files.internal(path).file();
	}

	@Override
	public Texture getTexture(String path) {
		if (!finishLoadingAsset(path, Texture.class)) {
			// TODO The texture has not been found, return a debug texture?
		}
		return assetManager.get(path, Texture.class);
	}

	public Sound getSound(String path) {
		return getAsset(path, Sound.class);
	}

	public Music getMusic(String path) {
		return getAsset(path, Music.class);
	}
	
	private <T> T getAsset(String path, Class<T> type) {
		if (!finishLoadingAsset(path, type)) {
			// TODO Throw exception, so they can handle it?
			new FileNotFoundException(path);
		}
		return assetManager.get(path, type);
	}

	@Override
	public void loadTextureAsync(String path) {
		assetManager.load(path, Texture.class);
	}

	public void loadSoundAsync(String path) {
		assetManager.load(path, Sound.class);
	}

	@Override
	public void loadMusicAsync(String path) {
		assetManager.load(path, Music.class);
	}

	@Override
	public void update() {
		assetManager.update();
	}

	public float getLoadProgress() {
		return assetManager.getProgress();
	}

	private <T> boolean finishLoadingAsset(String path, Class<T> type) {
		do {
			if (assetManager.isLoaded(path, type))
				return true;
		} while (!assetManager.update());
		// The entire queue was loaded but not the asset, maybe it was never
		// queued
		assetManager.load(path, type);
		assetManager.finishLoading();

		if (assetManager.isLoaded(path, type))
			return true;
		// The asset does not exist //TODO Exception here?
		return false;
	}
}
