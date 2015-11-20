package de.tu.darmstadt.informatik.eea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AnimationRenderComponent extends RenderComponent {
	
	final static private String ID = "AnimationRenderComponent";
	
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0;
	
	public AnimationRenderComponent(String file, float duration) {
		super(ID);
		textureAtlas = new TextureAtlas(Gdx.files.internal("data/spritesheet.atlas"));
        animation = new Animation(duration / textureAtlas.getTextures().size, textureAtlas.getRegions());
	}

	@Override
	public void render(Batch batch) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(elapsedTime, true), owner.getX(), owner.getY(), owner.getOriginX(), owner.getOriginY(), owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), owner.getRotation());
	}
	

}
