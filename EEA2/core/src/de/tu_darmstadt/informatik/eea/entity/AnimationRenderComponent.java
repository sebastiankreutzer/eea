package de.tu_darmstadt.informatik.eea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.tu_darmstadt.informatik.eea.action.DestroyEntityAction;

public class AnimationRenderComponent extends EEARenderComponent {
	
	final static private String ID = "AnimationRenderComponent";
	
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0;
    private boolean removeWhenFinished;
	
	public AnimationRenderComponent(float duration, String file) {
		super(ID);
		textureAtlas = new TextureAtlas(Gdx.files.internal("data/spritesheet.atlas"));
        animation = new Animation(duration / textureAtlas.getTextures().size, textureAtlas.getRegions());
	}
	
	public AnimationRenderComponent(float duration, TextureRegion... region){
		super(ID);
		animation = new Animation(duration/ region.length , region);
	}
	
	public AnimationRenderComponent(float duration, Animation.PlayMode playMode, TextureRegion... region){
		this(duration, region);
		setPlayMode(playMode);
	}
	
	public void setRemoveWhenFinished(boolean remove) {
		this.removeWhenFinished = remove;
	}
	
	public void setPlayMode(Animation.PlayMode mode){
		animation.setPlayMode(mode);
	}

	@Override
	public boolean update(float delta) {
		elapsedTime += delta;
		if (removeWhenFinished && animation.isAnimationFinished(elapsedTime)) {
			getOwnerEntity().addAction(new DestroyEntityAction());
		}
		return super.update(delta);
	}

	@Override
	public void render(Batch batch) {
		batch.draw(animation.getKeyFrame(elapsedTime, false), owner.getX(), owner.getY(), owner.getOriginX(), owner.getOriginY(), owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), owner.getRotation());
	}
	

}
