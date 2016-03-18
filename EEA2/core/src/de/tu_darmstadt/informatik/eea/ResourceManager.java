package de.tu_darmstadt.informatik.eea;

import java.io.FileNotFoundException;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ResourceManager implements IResourceManager {
	private AssetManager assetManager = new AssetManager();

	public ResourceManager() {
		assetManager = new AssetManager();
	}

	/**
	 * Creates a new ROM file.
	 * 
	 * @param path
	 *            The file path
	 */
	@Override
	public ROMFile openROMFile(String path) {
		return new ROMFile(path);
	}

	/**
	 * Creates a new RW file.
	 * 
	 * @param path
	 *            The file path
	 */
	public RWFile openRWFile(String path) {
		return new RWFile(path);
	}

	/**
	 * Loads a texture from the given path.
	 * 
	 * @param path
	 *            The file path
	 */
	@Override
	public Texture getTexture(String path) {
		return getAsset(path, Texture.class);
	}

	/**
	 * Loads a sound from the given path.
	 * 
	 * @param path
	 *            The file path
	 */
	public Sound getSound(String path) {
		return getAsset(path, Sound.class);
	}

	/**
	 * Loads music from the given path.
	 * 
	 * @param path
	 *            The file path
	 */
	public Music getMusic(String path) {
		return getAsset(path, Music.class);
	}

	/**
	 * Loads an asset from the given path.
	 * 
	 * @param path
	 *            The file path
	 * @param type
	 *            The type of the asset
	 * @return The loaded asset object
	 */
	private <T> T getAsset(String path, Class<T> type) {
		if (!finishLoadingAsset(path, type)) {
			// TODO Throw exception, so they can handle it?
			return null;
		}
		return assetManager.get(path, type);
	}

	/**
	 * Starts loading a texture asynchronously.
	 * 
	 * @param path
	 *            The file path
	 */
	@Override
	public void loadTextureAsync(String path) {
		assetManager.load(path, Texture.class);
	}

	/**
	 * Starts loading a sound asynchronously.
	 * 
	 * @param path
	 *            The file path
	 */
	@Override
	public void loadSoundAsync(String path) {
		assetManager.load(path, Sound.class);
	}

	/**
	 * Starts loading music asynchronously.
	 * 
	 * @param path
	 *            The file path
	 */
	@Override
	public void loadMusicAsync(String path) {
		assetManager.load(path, Music.class);
	}

	/**
	 * Updates the underlying asset manager.
	 */
	@Override
	public void update() {
		assetManager.update();
	}

	/**
	 * Returns the progress of completion.
	 * 
	 * @return A value between 0 and 1, where 1 means that loading has be
	 *         completed.
	 */
	public float getLoadProgress() {
		return assetManager.getProgress();
	}

	/**
	 * Blocks until the loading of the given asset has been completed.
	 * 
	 * @param path
	 *            The file path of the asset
	 * @param type
	 *            The asset type
	 * @return True, if loading has been completed, false if the asset could not
	 *         be loaded.
	 */
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

	@Override
	public TextureAtlas getTextureAtlas(String path) {
		return getAsset(path, TextureAtlas.class);
	}

	@Override
	public void loadTextureAtlasAsynch(String path) {
		assetManager.load(path, TextureAtlas.class);
	}
}
