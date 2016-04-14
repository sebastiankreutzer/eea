package de.tu_darmstadt.informatik.eea.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;

/**
 * A RenderComponent for drawing an animation of multiple images.
 * 
 * @author Johann Reinhard
 *
 */
public class AnimationRenderComponent extends EEARenderComponent {

	public static final String ID = "AnimationRenderComponent";

	private final Animation animation;
	private float elapsedTime = 0;
	private boolean removeWhenFinished;

	/**
	 * Creates a new AnimationRenderComponent from a TextureAtlas.
	 * 
	 * @param duration
	 *            The duration for each animation loop in seconds.
	 * @param file
	 *            The path to the TextureAtlas, relative to the assets folder.
	 */
	public AnimationRenderComponent(float duration, String file) {
		super(ID);
		Array<AtlasRegion> regions = EEAGame.getResourceManager().getTextureAtlas(file).getRegions();
		animation = new Animation(duration / regions.size, regions);
	}

	/**
	 * Creates a new AnimationRenderComponent from an array of textures.
	 * 
	 * @param duration
	 *            The duration for each animation loop in seconds.
	 * @param textures
	 *            The Textures for this animation.
	 */
	public AnimationRenderComponent(float duration, Texture... textures) {
		super(ID);
		TextureRegion[] keyFrames = new TextureRegion[textures.length];
		for (int i = 0; i < textures.length; i++) {
			keyFrames[i] = new TextureRegion(textures[i]);
		}
		animation = new Animation(duration, keyFrames);
	}

	/**
	 * Creates a new AnimationRenderComponent from an array of TextureRegion.
	 * 
	 * @param duration
	 *            The duration for each animation loop in seconds.
	 * @param regions
	 *            The TextureRegions for this animation.
	 */
	public AnimationRenderComponent(float duration, TextureRegion... regions) {
		super(ID);
		animation = new Animation(duration / regions.length, regions);
	}

	/**
	 * The owner entity will be destroyed after the animation playback is
	 * finished.
	 * 
	 * @param remove
	 *            If true the owner will be destroyed.
	 */
	public void setDestroyEntityAfter(boolean remove) {
		this.removeWhenFinished = remove;
	}

	/**
	 * Sets the {@link Animation.PlayMode} for this animation.
	 * @param mode
	 * The playmode, default is {@link PlayMode.NORMAL}.
	 */
	public void setPlayMode(Animation.PlayMode mode) {
		animation.setPlayMode(mode);
	}
	
	/**
	 * @return true if the animation has finished, otherwise false
	 */
	public boolean isAnimationFinished() {
		return animation.isAnimationFinished(elapsedTime);
	}

	@Override
	public boolean update(float delta) {
		elapsedTime += delta;
		if (removeWhenFinished && isAnimationFinished()) {
			getOwnerEntity().addAction(new DestroyEntityAction());
		}
		return super.update(delta);
	}

	@Override
	public void render(Batch batch) {
		batch.draw(animation.getKeyFrame(elapsedTime, false), owner.getX(), owner.getY(), owner.getOriginX(),
				owner.getOriginY(), owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(),
				owner.getRotation());
	}

}
