package de.tu_darmstadt.informatik.eea.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.ShapeRendererEEA;
import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * A component for drawing geometric shapes. IMPORTANT: These components are
 * rendered after the other {@link EEARenderComponent} and therefore always are
 * on top of other RenderComponents.
 * 
 * @author jr
 *
 */
public class ShapeRenderComponent extends EEARenderComponent {

	public static enum Shape {
		Rectangle, Ellipse
	};

	public static String ID = "ShapeRenderComponent";

	protected final RenderableShape shape;
	//protected final ShapeRenderer shapeRenderer;

	protected Color color = Color.BLACK;
	protected final ShapeType type;

	/**
	 * Creates a new ShapeRenderComponent that draws a default shape defined
	 * with a {@link Shape}.
	 * 
	 * @param shape
	 *            The shape template.
	 * @param filled
	 *            Whether the shape is drawn filled or lined.
	 */
	public ShapeRenderComponent(Shape shape, boolean filled) {
		this(getRenderableShape(shape), filled);
	}

	/**
	 * Determines the {@link RenderableShape} for a given {@link Shape}.
	 * 
	 * @param shape
	 *            The Shape.
	 * @return The RenderableShape representing the shape.
	 */
	protected static RenderableShape getRenderableShape(Shape shape) {
		switch (shape) {
		case Rectangle:
			return new RectangleShape();

		case Ellipse:
			return new EllipticShape();

		default:
			return new RectangleShape();
		}
	}

	/**
	 * Creates a new ShapeRenderComponent that draws custom shapes.
	 * 
	 * @param shape
	 *            The custom {@link RenderableShape}.
	 * @param filled
	 *            Whether the shape is drawn filled or lined.
	 */
	public ShapeRenderComponent(RenderableShape shape, boolean filled) {
		super(ID);
		this.shape = shape;
		//shapeRenderer = EEAGame.getGraphics().getShapeRenderer();
		type = filled ? ShapeType.Filled : ShapeType.Line;
	}

	/**
	 * Sets the color this shape is rendered.
	 * 
	 * @param r
	 *            The red value 0-255.
	 * @param g
	 *            The green value 0-255.
	 * @param b
	 *            The blue value 0-255.
	 */
	public void setColor(int r, int g, int b) {
		color = new Color(r / 255f, g / 255f, b / 255f, 1.0f);
	}

	@Override
	public void render(Batch batch) {
		EEAGame.getGraphics().drawShape(this);
		// shapeRenderer.set(type);
		// shapeRenderer.setColor(color);
		// shape.render(owner, shapeRenderer);
	}

	/**
	 * Renders the shape using the ShapeRenderer.
	 * 
	 * @param renderer
	 *            The ShapeRenderer.
	 */
	public void renderShape(ShapeRendererEEA renderer) {
		renderer.set(type);
		renderer.setColor(color);
		shape.render(owner, renderer);
	}
}

/**
 * The default implementation for a rectangular renderable shape.
 * 
 * @author jr
 *
 */
class RectangleShape implements RenderableShape {

	@Override
	public void render(Entity owner, ShapeRendererEEA renderer) {
		renderer.rect(owner.getX() - (1 - owner.getScaleX()) * owner.getOriginX(),
				owner.getY() - (1 - owner.getScaleY()) * owner.getOriginY(), owner.getOriginX(), owner.getOriginY(),
				owner.getWidth(), owner.getHeight(), owner.getScaleX(), owner.getScaleY(), owner.getRotation());
	}
}

/**
 * The default imlementation for a elliptic/circular shape.
 * 
 * @author jr
 *
 */
class EllipticShape implements RenderableShape {

	@Override
	public void render(Entity owner, ShapeRendererEEA renderer) {
		renderer.ellipse(owner.getX(), owner.getY(), owner.getScaledWidth(), owner.getScaledHeight(), owner.getRotation());
	}
}
