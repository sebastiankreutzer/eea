package de.tu_darmstadt.informatik.eea;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.tu_darmstadt.informatik.eea.component.ShapeRenderComponent;
import de.tu_darmstadt.informatik.eea.component.TextRenderComponent;

/**
 * This class is responsible for certain graphic functions and provides a range
 * of utility functions such as coordinate transformations.
 * 
 * @author Johann Reinhard
 *
 */
public class EEAGraphics {

	protected final Viewport viewport;
	protected BitmapFont font;
	protected Color color = Color.BLACK;

	protected final ShapeRendererEEA renderer;
	protected final ArrayList<ShapeRenderComponent> shapes = new ArrayList<ShapeRenderComponent>();

	/**
	 * Creates a new EEAGrapics instance with the given viewport.
	 * 
	 * @param viewport
	 *            The viewport of this application.
	 */
	public EEAGraphics(Viewport viewport) {
		this.viewport = viewport;
		// Initialize text and shape rendering.
		font = new BitmapFont();
		renderer = new ShapeRendererEEA();

		renderer.setAutoShapeType(true);
		renderer.setColor(color);
	}

	/**
	 * Initializes the renderer for a new shape.
	 * 
	 * @param filled
	 *            Whether the next shape is filled or outlined.
	 */
	protected void prepareDrawShape(boolean filled) {
		renderer.setProjectionMatrix(viewport.getCamera().combined);
		renderer.begin(filled ? ShapeType.Filled : ShapeType.Line);
	}

	/**
	 * Queues a shape to be rendered.
	 * 
	 * @param shape
	 */
	public void drawShape(ShapeRenderComponent shape) {
		shapes.add(shape);
	}

	/**
	 * Draws the queued shapes and clears the queue.
	 */
	public void drawShapes() {
		renderer.setProjectionMatrix(viewport.getCamera().combined);
		renderer.begin();
		for (ShapeRenderComponent s : shapes) {
			s.renderShape(renderer);
		}
		renderer.end();
		shapes.clear();
	}

	/**
	 * Changes the color for the next shapes drawn.
	 * 
	 * @param r
	 *            The red value: 0-255
	 * @param g
	 *            The green value: 0-255
	 * @param b
	 *            The blue value: 0-255
	 */
	public void setShapeColor(int r, int g, int b) {
		color = new Color(r / 255f, g / 255f, b / 255f, 1.0f);
		renderer.setColor(color);
	}

	/**
	 * Sets the default font.
	 * 
	 * @param font
	 *            The font used as default for all {@link TextRenderComponent}.
	 */
	public void setFont(BitmapFont font) {
		this.font = font;
	}

	/**
	 * @return The current default font.
	 */
	public BitmapFont getFont() {
		return font;
	}

	/**
	 * @return The viewport of the application.
	 */
	public Viewport getViewport() {
		return viewport;
	}

	/**
	 * Utility function. Returns the cursor coordinates converted from screen to
	 * stage space.
	 * 
	 * @return The converted vector representing the position of the cursor.
	 */
	public Vector2 getCursorPosition() {
		return toStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
	}

	/**
	 * Utility function. Converts the given screen coordinates to stage space.
	 * 
	 * @param pos
	 *            A vector in screen space
	 * @return The converted vector
	 */
	public Vector2 toStageCoordinates(Vector2 pos) {
		return viewport.unproject(pos);
	}

	/**
	 * Utility function. Converts the given screen coordinates to stage space.
	 * 
	 * @param x
	 * @param y
	 * @return The converted vector
	 */
	public Vector2 toStageCoordinates(float x, float y) {
		return toStageCoordinates(new Vector2(x, y));
	}

	/**
	 * Utility function. Converts the given stage coordinates to screen space.
	 * 
	 * @param pos
	 *            A vector in stage space
	 * @return The converted vector
	 */
	public Vector2 toScreenCoordinates(Vector2 pos) {
		Vector2 screenPos = viewport.project(pos);
		screenPos.y = viewport.getScreenHeight() - pos.y;
		return screenPos;
	}

	/**
	 * Utility function. Converts the given stage coordinates to screen space.
	 * 
	 * @param x
	 * @param y
	 * @return The converted vector
	 */
	public Vector2 toScreenCoordinates(float x, float y) {
		return toScreenCoordinates(new Vector2(x, y));
	}

	/**
	 * Returns the frames per second (FPS) of this game.
	 * 
	 * @return FPS
	 */
	public int getFramerate() {
		return Gdx.graphics.getFramesPerSecond();
	}

	/**
	 * @return The aspect ratio of the screen.
	 */
	public float getAspectRatio() {
		return viewport.getScreenWidth() / viewport.getScreenHeight();
	}

	/**
	 * Returns the width of currently visible area of the world. This is
	 * generally NOT equal to the width of the application window, the screen
	 * width.
	 * 
	 * @return The world width.
	 */
	public float getWorldWidth() {
		return viewport.getWorldWidth();
	}

	/**
	 * Returns the height of currently visible area of the world. This is
	 * generally NOT equal to the width of the application window, the screen
	 * width.
	 * 
	 * @return The world height.
	 */
	public float getWorldHeight() {
		return viewport.getWorldHeight();
	}

}
