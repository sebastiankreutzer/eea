package de.tu.darmstadt.informatik.eea;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ResourcesManager implements IResourcesManager {
	private AssetManager assetManager = new AssetManager();
	
	@Override
	public Texture getTexture(String path) {
		return assetManager.get(path);
	}

	@Override
	public void loadTexture(String path) {
		assetManager.load(path, Texture.class);
		assetManager.finishLoading();
	}
}
