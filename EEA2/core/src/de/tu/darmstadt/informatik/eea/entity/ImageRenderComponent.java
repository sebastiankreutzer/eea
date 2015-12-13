package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.tu.darmstadt.informatik.eea.IResourcesManager;

public class ImageRenderComponent extends RenderComponent {
	
	public static final String ID = "ImageRenderComponent";
	private IResourcesManager resourcesManager;
	private String texturePath;
	
	public ImageRenderComponent(String texturePath, IResourcesManager resourcesManager) {
		super(ID);
		this.resourcesManager = resourcesManager;
		this.texturePath = texturePath;
		resourcesManager.loadTexture(texturePath);
	}

	@Override
	public void render(Batch batch) {
		Texture tex = resourcesManager.getTexture(this.texturePath);
		batch.draw(tex, owner.getX(), owner.getY(), owner.getOriginX(), owner.getOriginY(), 
				owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), 
				owner.getRotation(), 0, 0, tex.getWidth(), tex.getHeight(), false, false);
	}
	
	@Override
	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
		Texture tex = resourcesManager.getTexture(this.texturePath);
		owner.setSize(tex.getWidth(), tex.getHeight());
	}
}
