package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.tu.darmstadt.informatik.eea.EEAGraphics;

public class ImageRenderComponent extends RenderComponent {
	
	public static final String ID = "ImageRenderComponent";
	private String texturePath;
	private EEAGraphics eeaGraphics;
	
	public ImageRenderComponent(String texturePath, EEAGraphics eeaGraphics) {
		super(ID);
		this.texturePath = texturePath;
		this.eeaGraphics = eeaGraphics;
		this.eeaGraphics.getAssetManager().load(texturePath, Texture.class);
		// TODO TiBo gefällt mir noch nicht so gut:
		this.eeaGraphics.getAssetManager().finishLoading();
	}

	@Override
	public void render(Batch batch) {
		Texture tex = eeaGraphics.getAssetManager().get(this.texturePath, Texture.class);
		batch.draw(tex, owner.getX(), owner.getY(), owner.getOriginX(), owner.getOriginY(), 
				owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), 
				owner.getRotation(), 0, 0, tex.getWidth(), tex.getHeight(), false, false);
	}
	
	@Override
	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
		
		Texture tex = eeaGraphics.getAssetManager().get(this.texturePath, Texture.class);
		owner.setSize(tex.getWidth(), tex.getHeight());
	}
}
