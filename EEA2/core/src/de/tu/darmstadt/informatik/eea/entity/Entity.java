package de.tu.darmstadt.informatik.eea.entity;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor {

	private final String id;

	private List<Component> components = new ArrayList<Component>();
	private Iterator<Component> iterator;
	private RenderComponent renderComponent;

	public Entity(String id) {
		this.id = id;
	}

	public void addComponent(Component c) {
		components.add(c);

		if (c instanceof RenderComponent)
			this.renderComponent = (RenderComponent) c;

		c.setOwnerEntity(this);
	}

	public void removeComponent(Component c) {
		components.remove(c);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (renderComponent != null)
			renderComponent.render(batch);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		iterator = components.iterator();
		while(iterator.hasNext()){
			iterator.next().update(delta);
		}
	}
	
	public Shape getShape() {
		Shape shape = new Rectangle.Float(getX(), getY(), getWidth(), getHeight());
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(getRotation()), getOriginX(), getOriginY());
		return at.createTransformedShape(shape);
	}
	
	public boolean collides(Entity other) {
		// Note: Shape does not allow to directly test for intersection of two rotated rectangles.
		Area area = new Area(getShape());
		Area otherArea = new Area(other.getShape());
		// Set the first area to the intersection of the two shapes and test if the result is empty.
		area.intersect(otherArea);
		return !area.isEmpty();
	}

	public String getID() {
		return id;
	}
	
	public void dispose(){
		if (renderComponent != null) renderComponent.dispose();
		iterator = components.iterator();
		while(iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
		components.clear();
	}

}
