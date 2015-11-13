package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

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
	
	

}
