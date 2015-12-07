package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationRenderComponent extends RenderComponent {
	
	final static private String ID = "AnimationRenderComponent";
	
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0;
	
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
	
	public void setPlayMode(Animation.PlayMode mode){
		animation.setPlayMode(mode);
	}

	@Override
	public void render(Batch batch) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(elapsedTime, true), owner.getX(), owner.getY(), owner.getOriginX(), owner.getOriginY(), owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), owner.getRotation());
	}
	

}
