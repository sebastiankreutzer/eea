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

/**
 * The Entity class represents any object in your game, independent of the specific uses.
 * Multiple {@link de.tu.darmstadt.informatik.eea.entity.EEAComponent} can be used to determine the specific
 * behavior of any entity, a special {@link de.tu.darmstadt.informatik.eea.entity.RenderComponent}
 * determines how the entity should be displayed if necessary. Entities must be added to an
 * {@link de.tu.darmstadt.informatik.eea.states.EntityManager}.
 * @author Tim Borowski, Sebastian Kreutzer, Johann Reinhard
 * @version 2.0
 */
public class Entity extends Actor {

	private final String id;

	private List<EEAComponent> components = new ArrayList<EEAComponent>();
	private Iterator<EEAComponent> iterator;
	private RenderComponent renderComponent;
	
	private EntityManager manager;
	
	private boolean isPassable;

	/**
	 * Creates an entity with the given ID.
	 * @param id The ID that identifies this entity.
	 */
	public Entity(String id) {
		this.id = id;
		isPassable = true;
		manager = null;
	}

	/**
	 * Adds a {@link de.tu.darmstadt.informatik.eea.entity.EEAComponent} to the components of this 
	 * entity. If this component is a {@link de.tu.darmstadt.informatik.eea.entity.RenderComponent}
	 * the current, if any, will be replaced.
	 * @param c The component to add to this entity.
	 */
	public void addComponent(EEAComponent c) {
		components.add(c);

		if (c instanceof RenderComponent)
			this.renderComponent = (RenderComponent) c;

		c.setOwnerEntity(this);
		c.onAddComponent();
	}

	
	/**
	 * Removes this {@link de.tu.darmstadt.informatik.eea.entity.EEAComponent} from the list of registered
	 * components of this {@link de.tu.darmstadt.informatik.eea.entity.Entity}. This method does not remove 
	 * the registered {@link de.tu.darmstadt.informatik.eea.entity.RenderComponent}.
	 * @param c The component to remove from this entity.
	 */
	public void removeComponent(EEAComponent c) {
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
		Shape shape = new Rectangle.Float(getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
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
	
	/**
	 * Checks whether this entity has a registered 
	 * {@link de.tu.darmstadt.informatik.eea.states.EntityManager}.
	 * @return true if this entity has a registered manager, otherwise.
	 */
	public boolean isManaged() {
		return manager != null;
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public EntityManager getManager() {
		return manager;
	}

}
