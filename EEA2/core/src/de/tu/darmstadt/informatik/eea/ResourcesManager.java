package de.tu.darmstadt.informatik.eea;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ResourcesManager implements IResourcesManager {
	private AssetManager assetManager = new AssetManager();

	public ResourcesManager() {
		assetManager = new AssetManager();
	}

	@Override
	public Texture getTexture(String path) {
		if(!finishLoadingAsset(path, Texture.class)) {
			// TODO The texture has not been found, return a debug texture?
		}		
		return assetManager.get(path);
	}

	@Override
	public void loadTextureAsync(String path) {
		assetManager.load(path, Texture.class);
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
			if(assetManager.isLoaded(path, type)) return true;
		} while (!assetManager.update());
		// The entire queue was loaded but not the asset, maybe it was never queued
		assetManager.load(path, type);
		assetManager.finishLoading();
		
		if(assetManager.isLoaded(path, type)) return true;
		// The asset does not exist //TODO Exception here?
		return false;
	}
}
