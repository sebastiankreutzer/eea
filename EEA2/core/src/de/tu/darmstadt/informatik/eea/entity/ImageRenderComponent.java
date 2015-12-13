package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;

public class ImageRenderComponent extends RenderComponent {
	
	public static final String ID = "ImageRenderComponent";

	private Texture texture;
	
	public ImageRenderComponent(Texture texture) {
		super(ID);
		this.texture = texture;
	}

	@Override
	public void render(Batch batch) {
		batch.draw(texture, owner.getX(), owner.getY(), owner.getOriginX(), owner.getOriginY(), owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), owner.getRotation(), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
	}
	
	@Override
	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
		owner.setSize(texture.getWidth(), texture.getHeight());
		//owner.setOrigin(owner.getWidth()  / 2, owner.getHeight()  / 2);
		//System.out.println(owner.getScaleX());
	}

}
