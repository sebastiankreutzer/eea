package de.tu.darmstadt.informatik.tanks2.factories;

import de.tu.darmstadt.informatik.eea.EEAGraphics;
import de.tu.darmstadt.informatik.eea.entity.Entity;
import de.tu.darmstadt.informatik.eea.entity.ImageRenderComponent;
import de.tu.darmstadt.informatik.tanks2.entities.Explosion;

public class ExplosionFactory {
	

	final float x;
	final float y;
	final float speed;
	final float width;
	final float height;
	final boolean debug;
	private EEAGraphics eeaGraphics;
	
	public ExplosionFactory(float x, float y, float speed, float width, float height, boolean debug, EEAGraphics eeaGraphics){
		this.debug = debug;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.eeaGraphics = eeaGraphics;
	}
	
	public Entity createEntity() {
		Entity explosion = new Explosion("Explosion"+Math.random(), width, height, speed);
		explosion.setPosition(x, y);
//		new TextureRegion(new Texture(""));
//		// TODO set width, height and loop
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
		ImageRenderComponent anim = new ImageRenderComponent("expl02.png", eeaGraphics);
		explosion.addComponent(anim);
		return explosion;
	}
		

}
