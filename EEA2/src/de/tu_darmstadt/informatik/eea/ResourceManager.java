package de.tu_darmstadt.informatik.eea;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ResourceManager implements IResourceManager {
	private AssetManager assetManager = new AssetManager();

	public ResourceManager() {
		assetManager = new AssetManager();
	}

	@Override
	public ROMFile openROMFile(String path) throws FileNotFoundException {
		return openROMFile(path, PathName.RELATIVE, null);
	}
	
	public ROMFile openROMFile(String path, PathName type) throws FileNotFoundException {
		return openROMFile(path, type, null);
	}

	public ROMFile openROMFile(String path, PathName type, String charset) throws FileNotFoundException {
		try {
			switch (type) {
			case ABSOLUTE:
				return new ROMFile(Gdx.files.absolute(path), charset);

			default:
				return new ROMFile(Gdx.files.internal(path), charset);
			}
		} catch (GdxRuntimeException e) {
			throw new FileNotFoundException(e.getLocalizedMessage());
		}
	}

	public RWFile openRWFile(String path) {
		return openRWFile(path, PathName.RELATIVE, null);
	}
	
	public RWFile openRWFile(String path, PathName type) {
		return openRWFile(path, type, null);
	}

	public RWFile openRWFile(String path, PathName type, String charset) {
		switch (type) {
		case ABSOLUTE:
			return new RWFile(Gdx.files.absolute(path), charset);

		default:
			return new RWFile(Gdx.files.local(path), charset);
		}
	}
	
	@Override
	public Texture getTexture(String path) {
		return getAsset(path, Texture.class);
	}
	
	@Override
	public void loadTextureAsync(String path) {
		assetManager.load(path, Texture.class);
	}

	public Sound getSound(String path) {
		return getAsset(path, Sound.class);
	}
	
	@Override
	public void loadSoundAsync(String path) {
		assetManager.load(path, Sound.class);
	}

	public Music getMusic(String path) {
		return getAsset(path, Music.class);
	}

	@Override
	public void loadMusicAsync(String path) {
		assetManager.load(path, Music.class);
	}
	
	@Override
	public TextureAtlas getTextureAtlas(String path) {
		return getAsset(path, TextureAtlas.class);
	}

	@Override
	public void loadTextureAtlasAsynch(String path) {
		assetManager.load(path, TextureAtlas.class);
	}
	
	@Override
	public BitmapFont getFont(String path) {
		return getAsset(path, BitmapFont.class);
	}
	
	@Override
	public void loadFontAsync(String path) {
		assetManager.load(path, BitmapFont.class);
	}

	/**
	 * Updates the underlying asset manager.
	 */
	@Override
	public void update() {
		assetManager.update();
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
	protected <T> T getAsset(String path, Class<T> type) {
		if (!finishLoadingAsset(path, type)) {
			throw new RuntimeException("Could not load asset of type " + type.getName() + " from " + path);
		}
		return assetManager.get(path, type);
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
	protected <T> boolean finishLoadingAsset(String path, Class<T> type) {
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
		// The asset does not exist
		return false;
	}
}
