package de.tu.darmstadt.informatik.eea.entity;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.tu.darmstadt.informatik.eea.states.EntityManager;
import sun.java2d.pipe.ShapeSpanIterator;

public class Entity extends Actor {

	private final String id;

	private List<Component> components = new ArrayList<Component>();
	private Iterator<Component> iterator;
	private RenderComponent renderComponent;
	
	private EntityManager manager;
	
	private boolean isPassable;

	public Entity(String id) {
		this.id = id;
		isPassable = true;
		manager = null;
	}

	public void addComponent(Component c) {
		components.add(c);

		if (c instanceof RenderComponent)
			this.renderComponent = (RenderComponent) c;

		c.setOwnerEntity(this);
		c.onAddComponent();
	}

	public void removeComponent(Component c) {
		c.onRemoveComponent();
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
		at.rotate(Math.toRadians(getRotation()), getOriginX()+getX(), getOriginY()+getY());
		return at.createTransformedShape(shape);
	}
	
	public boolean collides(Entity other) {
	    if (other == null || (id != null && id.equals(other.getID())))
	        return false;
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
	
	public void setPassable(boolean passable){
		isPassable = passable;
	}
	
	public boolean isPassable(){
		return isPassable;
	}

	@Override
	public boolean remove() {
		iterator = components.iterator();
		while(iterator.hasNext()){
			iterator.next().onRemoveComponent();
			iterator.remove();
		}
		components.clear();
		return super.remove();
	}
	
	public boolean isActive() {
		return manager != null;
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public EntityManager getManager() {
		return manager;
	}

}
