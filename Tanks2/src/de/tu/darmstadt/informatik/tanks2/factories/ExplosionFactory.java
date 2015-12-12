package de.tu.darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.tu.darmstadt.informatik.eea.entity.AnimationRenderComponent;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.eea.entity.RenderComponent;
import de.tu.darmstadt.informatik.tanks2.entities.Explosion;

public class ExplosionFactory {
	
	public static Entity createExplosion(float x, float y, float speed, float width, float height, boolean debug) {
		Entity explosion = new Explosion("Explosion"+Math.random(), width, height, speed);
		explosion.setPosition(x, y);
//		new TextureRegion(new Texture(""));
//		// TODO set width, height and loop, set debug options
//		RenderComponent anim = new AnimationRenderComponent(0.9f, new TextureRegion[]{
//				new TextureRegion(new Texture("expl01.png")),
//				new TextureRegion(new Texture("expl02.png")),
//				new TextureRegion(new Texture("expl03.png")),
//				new TextureRegion(new Texture("expl04.png")),
//				new TextureRegion(new Texture("expl05.png")),
//				new TextureRegion(new Texture("expl06.png")),
//				new TextureRegion(new Texture("expl07.png")),
//				new TextureRegion(new Texture("expl08.png")),
//				new TextureRegion(new Texture("expl09.png")),
//				new TextureRegion(new Texture("expl10.png")),
//				new TextureRegion(new Texture("expl11.png")),
//				new TextureRegion(new Texture("expl12.png")),
//				new TextureRegion(new Texture("expl13.png")),
//				new TextureRegion(new Texture("expl14.png")),
//				new TextureRegion(new Texture("expl15.png")),
//				new TextureRegion(new Texture("expl16.png"))
//		});// 0.01f, width, height, false);
		ImageRenderComponent anim = new ImageRenderComponent(new Texture("expl02.png"));
		explosion.addComponent(anim);
		return explosion;
	}
		

}
