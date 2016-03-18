package de.tu_darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.tu_darmstadt.informatik.eea.IResourcesManager;

public class ImageRenderComponent extends EEARenderComponent {
	
	public static final String ID = "ImageRenderComponent";
	private IResourcesManager resourcesManager;
	// TODO Better to keep the reference to Texture instead of a string to avoid searching for this
	// texture every scaling event
	private String texturePath;
	
	public ImageRenderComponent(String texturePath, IResourcesManager resourcesManager) {
		super(ID);
		this.resourcesManager = resourcesManager;
		this.texturePath = texturePath;
		resourcesManager.loadTextureAsync(texturePath);
		//tex = resourcesManager.getTexture(texturePath);
	}

	@Override
	public void render(Batch batch) {
		Texture tex = resourcesManager.getTexture(this.texturePath);
		batch.draw(tex, owner.getX() - (1 - owner.getScaleX()) * owner.getOriginX(),
				owner.getY() - (1 - owner.getScaleY()) * owner.getOriginY(), owner.getOriginX(), owner.getOriginY(), 
				owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), 
				owner.getRotation(), 0, 0, tex.getWidth(), tex.getHeight(), false, false);
	}
	
	/**
	 * Sets the owner entity and adjusts its size.
	 * @param owningEntity The entity this component was added to
	 */
	@Override
	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
		Texture tex = resourcesManager.getTexture(this.texturePath);
		owner.setSize(tex.getWidth(), tex.getHeight());
	}
}
