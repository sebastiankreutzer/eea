package de.tu_darmstadt.informatik.eea.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * A RenderComponent for drawing single images.
 * 
 * @author jr
 *
 */
public class ImageRenderComponent extends EEARenderComponent {

	public static final String ID = "ImageRenderComponent";
	private Texture texture;

	/**
	 * Creates a new ImageRenderComponent.
	 * @param texturePath
	 * The path of the image file, relative to the assets folder.
	 */
	public ImageRenderComponent(String texturePath) {
		super(ID);
		texture = EEAGame.getResourceManager().getTexture(texturePath);
	}

	@Override
	public void render(Batch batch) {
		batch.draw(texture, owner.getX() - (1 - owner.getScaleX()) * owner.getOriginX(),
				owner.getY() - (1 - owner.getScaleY()) * owner.getOriginY(), owner.getOriginX(), owner.getOriginY(),
				owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), owner.getRotation(), 0, 0,
				texture.getWidth(), texture.getHeight(), false, false);
	}

	/**
	 * Sets the owner entity and adjusts its size.
	 * 
	 * @param owningEntity
	 *            The entity this component was added to
	 */
	@Override
	public void setOwnerEntity(Entity owningEntity) {
		super.setOwnerEntity(owningEntity);
		owner.setSize(texture.getWidth(), texture.getHeight());
	}
}
