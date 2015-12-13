package de.tu.darmstadt.informatik.eea;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ResourcesManager implements IResourcesManager {
	private AssetManager assetManager = new AssetManager();
	private final Set<String> loadedTextures = new HashSet<String>();
	
	@Override
	public Texture getTexture(String path) {
		if(!loadedTextures.contains(path)){
			loadTextureAsync(path);
		}		
		if(!assetManager.isLoaded(path)){
			assetManager.finishLoading();
		}		
		return assetManager.get(path);
	}

	@Override
	public void loadTextureAsync(String path) {
		assetManager.load(path, Texture.class);
		loadedTextures.add(path);
	}
}
