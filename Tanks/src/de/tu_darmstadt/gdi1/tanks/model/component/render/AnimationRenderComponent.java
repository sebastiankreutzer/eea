package de.tu_darmstadt.gdi1.tanks.model.component.render;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.component.RenderComponent;
import eea.engine.entity.StateBasedEntityManager;

public class AnimationRenderComponent extends RenderComponent{
	
	private Vector2f size;
	protected Animation animation;

	public AnimationRenderComponent(Image[] frames, float speed ,float width, float height, boolean looping) {
		super("AnimationRenderComponent");
		this.size = new Vector2f(width, height);
		animation = new Animation(frames, 1);
		animation.setSpeed(speed);
		animation.setLooping(looping);
		animation.start();
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(animation.isStopped()) StateBasedEntityManager.getInstance().removeEntity(sb.getCurrentStateID(), getOwnerEntity());
	}

	@Override
	public Vector2f getSize() {
		return size;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		if(!animation.isStopped())
			animation.draw(getOwnerEntity().getPosition().x, getOwnerEntity().getPosition().y, size.x, size.y);
		
	}
	
	

}
