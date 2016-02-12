package de.tu_darmstadt.gdi1.tanks.model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tu_darmstadt.gdi1.tanks.model.component.render.AnimationRenderComponent;
import de.tu_darmstadt.gdi1.tanks.model.entities.Explosion;
import eea.engine.component.RenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

public class ExplosionFactory implements IEntityFactory{
	

	final float x;
	final float y;
	final float speed;
	final float width;
	final float height;
	final boolean debug;
	
	public ExplosionFactory(float x, float y, float speed, float width, float height, boolean debug){
		this.debug = debug;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}

	@Override
	public Entity createEntity() {
		Entity explosion = new Explosion("Explosion"+Math.random(), width, height, speed);
		explosion.setPosition(new Vector2f(x, y));
		if(!debug){
			try {
				
				RenderComponent anim = new AnimationRenderComponent(new Image[]{
						new Image("assets/expl01.png"),
						new Image("assets/expl02.png"),
						new Image("assets/expl03.png"),
						new Image("assets/expl04.png"),
						new Image("assets/expl05.png"),
						new Image("assets/expl06.png"),
						new Image("assets/expl07.png"),
						new Image("assets/expl08.png"),
						new Image("assets/expl09.png"),
						new Image("assets/expl10.png"),
						new Image("assets/expl11.png"),
						new Image("assets/expl12.png"),
						new Image("assets/expl13.png"),
						new Image("assets/expl14.png"),
						new Image("assets/expl15.png"),
						new Image("assets/expl16.png")
				}, 0.01f, width, height, false);
				explosion.addComponent(anim);
			}catch (SlickException e) {
				e.printStackTrace();
			}
		} else explosion.setSize(new Vector2f(10,10));
		return explosion;
	}
		

}
