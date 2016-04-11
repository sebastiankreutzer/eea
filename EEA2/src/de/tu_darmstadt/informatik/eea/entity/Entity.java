package de.tu_darmstadt.informatik.eea.entity;

import static com.badlogic.gdx.utils.Align.bottom;
import static com.badlogic.gdx.utils.Align.left;
import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.top;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import de.tu_darmstadt.informatik.eea.component.EEAComponent;
import de.tu_darmstadt.informatik.eea.component.EEARenderComponent;
import de.tu_darmstadt.informatik.eea.component.collision.EEACollisionTriggerComponent;
import de.tu_darmstadt.informatik.eea.component.collision.NoCollisionComponent;


/**
 * The Entity class represents any object in your game, independent of the
 * specific uses. Multiple
 * {@link de.tu_darmstadt.informatik.eea.component.EEAComponent} can be used to
 * determine the specific behavior of any entity, a special
<<<<<<< HEAD
 * {@link de.tu_darmstadt.informatik.eea.entity.EEARenderComponent} determines
 * how the entity should be displayed if necessary. Entities must be added to an
 * {@link de.tu_darmstadt.informatik.eea.states.EntityManager}.
=======
 * {@link de.tu_darmstadt.informatik.eea.component.EEARenderComponent} determines how
 * the entity should be displayed if necessary. Entities must be added to an
 * {@link de.tu_darmstadt.informatik.eea.entity.EntityManager}.
>>>>>>> d2c188de72ecd34bbc4f24b78f8955ed62c47afc
 * 
 * @author Tim Borowski, Sebastian Kreutzer, Johann Reinhard
 * @version 2.0
 */
public class Entity extends Actor {

	private final String id;

	private CopyOnWriteArrayList<EEAComponent> components = new CopyOnWriteArrayList<EEAComponent>();
	private Iterator<EEAComponent> iterator;
	private EEARenderComponent renderComponent;
	private EEACollisionTriggerComponent collisionComponent;

	private EntityManager manager;

	/**
	 * Creates an entity with the given ID.
	 * 
	 * @param id
	 *            The ID that identifies this entity.
	 */
	public Entity(String id) {
		if (id == null)
			throw new InvalidParameterException("ID cannot be null!");
		else
			this.id = id;

		collisionComponent = new NoCollisionComponent();
		manager = null;
	}

	/**
	 * Adds a {@link de.tu_darmstadt.informatik.eea.component.EEAComponent} to the
	 * components of this entity. If this component is a
	 * {@link de.tu_darmstadt.informatik.eea.component.EEARenderComponent} the
	 * current, if any, will be replaced.
	 * 
	 * @param c
	 *            The component to add to this entity.
	 */
	public void addComponent(EEAComponent c) {
		components.add(c);

		if (c instanceof EEARenderComponent)
			this.renderComponent = (EEARenderComponent) c;

		if (c instanceof EEACollisionTriggerComponent)
			this.collisionComponent = (EEACollisionTriggerComponent) c;

		c.setOwnerEntity(this);
		c.onAddComponent();
	}

	@Override
	protected void sizeChanged() {
		setOriginX(getWidth() / 2);
		setOriginY(getHeight() / 2);
		super.sizeChanged();
		collisionComponent.sizeChanged();
	}

	/**
	 * Removes this {@link de.tu_darmstadt.informatik.eea.component.tu.darmstadt.informatik.eea.entity.EEAComponent}
	 * from the list of registered components of this
	 * {@link de.tu.darmstadt.informatik.eea.entity.Entity}. This method does
	 * not remove the registered
<<<<<<< HEAD
	 * {@link de.EEARenderComponent.darmstadt.informatik.eea.entity.RenderComponent}
	 * .
=======
	 * {@link de.tu_darmstadt.informatik.eea.component.EEARenderComponent.darmstadt.informatik.eea.entity.RenderComponent}.
>>>>>>> d2c188de72ecd34bbc4f24b78f8955ed62c47afc
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
		while (iterator.hasNext() && iterator.next().update(delta)) {

		}
	}

	/**
	 * @return The first {@link Entity} this entity collides with or null if it
	 *         does not collide with any entity.
	 */
	public Entity collides() {
		return manager.collides(this);
	}

	/**
	 * Determines whether this entity collides with another entity.
	 * 
	 * @param other
	 *            The other Entity
	 * @return true if these entities collide, otherwise false.
	 */
	public boolean collidesWith(Entity other) {
		if (other == null || id.equals(other.getID()))
			return false;
		return other.collisionComponent.collide(collisionComponent);
	}

	public String getID() {
		return id;
	}

	/**
	 * @return The CollisionComponent of this Entity.
	 */
	public EEACollisionTriggerComponent getCollisionComponent() {
		return collisionComponent;
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
	 * {@link de.tu_darmstadt.informatik.eea.entity.EntityManager}.
	 * 
	 * @return true if this entity has a registered manager, otherwise.
	 */
	public boolean isManaged() {
		return manager != null;
	}

	/**
	 * @param manager
	 *            Sets the {@link EntityManager} for this entity.
	 */
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @return The {@link EntityManager} of this entity or null if the entity is
	 *         unmanaged.
	 */
	public EntityManager getManager() {
		return manager;
	}

	/// --------------------------
	/// Center coordinates fixes

	/// Position

	/** Returns the X position of the actor's left edge. */
	public float getX() {
		return super.getX() + (1 - getScaleX()) * getOriginX();
	}

	/** Returns the X position of the specified {@link Align alignment}. */
	public float getX(int alignment) {
		float x = this.getX();
		if ((alignment & right) != 0)
			x += getScaledWidth();
		else if ((alignment & left) == 0) //
			x += getScaledWidth() / 2;
		return x;
	}

	/** Sets the X position of the actor's left edge. */
	public void setX(float x) {
		if (this.getX() != x) {
			super.setX(x - (1 - getScaleX()) * getOriginX());
			positionChanged();
		}
	}

	/** Returns the Y position of the actor's bottom edge. */
	public float getY() {
		return super.getY() + (1 - getScaleY()) * getOriginY();
	}

	/** Returns the Y position of the specified {@link Align alignment}. */
	public float getY(int alignment) {
		float y = this.getY();
		if ((alignment & top) != 0)
			y += getScaledHeight();
		else if ((alignment & bottom) == 0) //
			y += getScaledHeight() / 2;
		return y;
	}

	/** Sets the Y position of the actor's left edge. */
	public void setY(float y) {
		if (this.getY() != y) {
			super.setY(y - (1 - getScaleY()) * getOriginY());
			positionChanged();
		}
	}

	/** Sets the position of the actor's bottom left corner. */
	public void setPosition(float x, float y) {
		if (this.getX() != x || this.getY() != y) {
			this.setX(x);
			this.setY(y);
			positionChanged();
		}
	}

	/**
	 * Sets the position using the specified {@link Align alignment}. Note this
	 * may set the position to non-integer coordinates.
	 */
	public void setPosition(float x, float y, int alignment) {
		if ((alignment & right) != 0)
			x -= getScaledWidth();
		else if ((alignment & left) == 0) //
			x -= getScaledWidth() / 2;

		if ((alignment & top) != 0)
			y -= getScaledHeight();
		else if ((alignment & bottom) == 0) //
			y -= getScaledHeight() / 2;

		setPosition(x, y);
	}

	/// Size

	public void setWidth(float width) {
		float oldWidth = super.getWidth();
		if (width != oldWidth) {
			super.setWidth(width);
			this.moveBy((oldWidth - width) * (1 - this.getScaleX()) / 2, 0);
			sizeChanged();
		}
	}

	public void setHeight(float height) {
		float oldHeight = this.getHeight();
		if (height != oldHeight) {
			super.setHeight(height);
			this.moveBy(0, (oldHeight - height) * (1 - this.getScaleY()) / 2);
			sizeChanged();
		}
	}

	/** Sets the width and height. */
	public void setSize(float width, float height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	/** Set bounds the x, y, width, and height. */
	public void setBounds(float x, float y, float width, float height) {
		if (getX() != x || getY() != y) {
			setX(x);
			setY(y);
			positionChanged();
		}
		if (this.getWidth() != width || this.getHeight() != height) {
			setWidth(width);
			setHeight(height);
			sizeChanged();
		}
	}

	public float getScaledWidth() {
		return super.getWidth() * this.getScaleX();
	}

	public float getScaledHeight() {
		return super.getHeight() * this.getScaleY();
	}

	@Override
	public void setScaleX(float scaleX) {
		super.setScaleX(scaleX);
		collisionComponent.sizeChanged();
	}

	@Override
	public void setScaleY(float scaleY) {
		super.setScaleY(scaleY);
		collisionComponent.sizeChanged();
	}

	@Override
	public void setScale(float scaleX, float scaleY) {
		super.setScale(scaleX, scaleY);
		collisionComponent.sizeChanged();
	}

	@Override
	public void setScale(float scaleXY) {
		super.setScale(scaleXY);
		collisionComponent.sizeChanged();
	}

	@Override
	public void scaleBy(float scale) {
		super.scaleBy(scale);
		collisionComponent.sizeChanged();
	}

	@Override
	public void scaleBy(float scaleX, float scaleY) {
		super.scaleBy(scaleX, scaleY);
		collisionComponent.sizeChanged();
	}

}
