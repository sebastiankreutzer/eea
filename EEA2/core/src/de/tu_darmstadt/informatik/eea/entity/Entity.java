package de.tu_darmstadt.informatik.eea.entity;

import static com.badlogic.gdx.utils.Align.bottom;
import static com.badlogic.gdx.utils.Align.left;
import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.top;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import de.tu_darmstadt.informatik.eea.EEAGraphics;
import de.tu_darmstadt.informatik.eea.states.EntityManager;

/**
 * The Entity class represents any object in your game, independent of the
 * specific uses. Multiple
 * {@link de.tu_darmstadt.informatik.eea.entity.EEAComponent} can be used to
 * determine the specific behavior of any entity, a special
 * {@link de.tu_darmstadt.informatik.eea.entity.RenderComponent} determines how
 * the entity should be displayed if necessary. Entities must be added to an
 * {@link de.tu_darmstadt.informatik.eea.states.EntityManager}.
 * 
 * @author Tim Borowski, Sebastian Kreutzer, Johann Reinhard
 * @version 2.0
 */
public class Entity extends Actor {

	private final String id;

	private CopyOnWriteArrayList<EEAComponent> components = new CopyOnWriteArrayList<EEAComponent>();
	private Iterator<EEAComponent> iterator;
	private RenderComponent renderComponent;

	private EntityManager manager;

	protected Vector2 center;
	private boolean isPassable;

	/**
	 * Creates an entity with the given ID.
	 * 
	 * @param id
	 *            The ID that identifies this entity.
	 */
	public Entity(String id) {
		this.id = id;
		isPassable = true;
		manager = null;
	}

	/**
	 * Adds a {@link de.tu_darmstadt.informatik.eea.entity.EEAComponent} to the
	 * components of this entity. If this component is a
	 * {@link de.tu_darmstadt.informatik.eea.entity.RenderComponent} the
	 * current, if any, will be replaced.
	 * 
	 * @param c
	 *            The component to add to this entity.
	 */
	public void addComponent(EEAComponent c) {
		components.add(c);

		if (c instanceof RenderComponent)
			this.renderComponent = (RenderComponent) c;

		c.setOwnerEntity(this);
		c.onAddComponent();
	}
	
	@Override
	protected void sizeChanged() {
		setOriginX(getWidth()/2);
		setOriginY(getHeight()/2);
		//setOrigin(Align.center);
		super.sizeChanged();
	}

	/**
	 * Removes this {@link de.tu.darmstadt.informatik.eea.entity.EEAComponent}
	 * from the list of registered components of this
	 * {@link de.tu.darmstadt.informatik.eea.entity.Entity}. This method does
	 * not remove the registered
	 * {@link de.tu.darmstadt.informatik.eea.entity.RenderComponent}.
	 * 
	 * @param c
	 *            The component to remove from this entity.
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
		while (iterator.hasNext()) {
			if (!iterator.next().update(delta))
				break;
		}
	}

	public Shape getShape() {
		Shape shape = new Rectangle.Float(getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(getRotation()), getOriginX() * getScaleX() + getX(), getOriginY() * getScaleY() + getY());
		return at.createTransformedShape(shape);
	}

	public boolean collides(Entity other) {
		if (other == null || (id != null && id.equals(other.getID())))
			return false;
		// Note: Shape does not allow to directly test for intersection of two
		// rotated rectangles.
		Area area = new Area(getShape());
		Area otherArea = new Area(other.getShape());
		// Set the first area to the intersection of the two shapes and test if
		// the result is empty.
		area.intersect(otherArea);

		return !area.isEmpty();
	}

	public String getID() {
		return id;
	}

	public void setPassable(boolean passable) {
		isPassable = passable;
	}

	public boolean isPassable() {
		return isPassable;
	}

	@Override
	public boolean remove() {
		for (EEAComponent component : components) {
			component.onRemoveComponent();
			components.remove(component);
		}
		clear();
		components.clear();
		manager.removeEntity(this);
		return super.remove();
	}

	/**
	 * Checks whether this entity has a registered
	 * {@link de.tu_darmstadt.informatik.eea.states.EntityManager}.
	 * 
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
	
	///--------------------------
	/// Center coordinates fixes
	
	/// Position
	
	/** Returns the X position of the actor's left edge. */
	public float getX () {
		return super.getX() + (1 - getScaleX()) * getOriginX();
	}

	/** Returns the X position of the specified {@link Align alignment}. */
	public float getX (int alignment) {
		float x = this.getX();
		if ((alignment & right) != 0)
			x += this.getWidth();
		else if ((alignment & left) == 0) //
			x += this.getWidth() / 2;
		return x;
	}

	/** Sets the X position of the actor's left edge. */
	public void setX (float x) {
		if (this.getX() != x) {
			super.setX(x - (1 - getScaleX()) * getOriginX());
			positionChanged();
		}
	}
	
	/** Returns the Y position of the actor's bottom edge. */
	public float getY () {
		return super.getY() + (1 - getScaleY()) * getOriginY();
	}

	/** Returns the Y position of the specified {@link Align alignment}. */
	public float getY (int alignment) {
		float y = this.getY();
		if ((alignment & top) != 0)
			y += this.getHeight();
		else if ((alignment & bottom) == 0) //
			y += this.getHeight() / 2;
		return y;
	}

	/** Sets the Y position of the actor's left edge. */
	public void setY (float y) {
		if (this.getY() != y) {
			super.setY(y - (1 - getScaleY()) * getOriginY());
			positionChanged();
		}
	}
	
	/** Sets the position of the actor's bottom left corner. */
	public void setPosition (float x, float y) {
		if (this.getX() != x || this.getY() != y) {
			this.setX(x);
			this.setY(y);
			positionChanged();
		}
	}

	/** Sets the position using the specified {@link Align alignment}. Note this may set the position to non-integer coordinates. */
	public void setPosition (float x, float y, int alignment) {
		if ((alignment & right) != 0)
			x -= this.getWidth();
		else if ((alignment & left) == 0) //
			x -= this.getWidth() / 2;

		if ((alignment & top) != 0)
			y -= this.getHeight();
		else if ((alignment & bottom) == 0) //
			y -= this.getWidth() / 2;

		setPosition(x, y);
	}
	
	/// Size
	
	public void setWidth (float width) {
		float oldWidth = this.getWidth();
		super.setWidth(width);
		if (width != oldWidth){
			this.moveBy((oldWidth - width) * (1 - this.getScaleX())/2, 0);
			sizeChanged();
		}
	}

	public void setHeight (float height) {
		float oldHeight = this.getHeight();
		super.setHeight(height);
		if (height != oldHeight){
			this.moveBy(0, (oldHeight - height) * (1 - this.getScaleY())/2);
			sizeChanged();
		}
	}
	
	/** Sets the width and height. */
	public void setSize (float width, float height) {
		this.setWidth(width);
		this.setHeight(height);
	}

}
