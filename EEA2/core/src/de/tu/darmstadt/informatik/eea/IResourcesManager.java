package de.tu.darmstadt.informatik.eea;

import com.badlogic.gdx.graphics.Texture;

public interface IResourcesManager {
	public Texture getTexture(String path);
	public void loadTexture(String path);
}
