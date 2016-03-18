package de.tu_darmstadt.informatik.eea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EEAGraphics {
	
	private Viewport viewport;
	private BitmapFont font;
	private Color color;
	
	private ShapeRenderer renderer = new ShapeRenderer();
		
	public EEAGraphics(Viewport viewport) {
		this.viewport = viewport;
		font = new BitmapFont();
		color = Color.BLACK;
	}
	
	public void drawString(Batch batch, String string, float x, float y){
		font.draw(batch, string, x, y);
	}
	
	public void drawRect(float x, float y, float width, float height){
		renderer.setColor(color);
		renderer.setProjectionMatrix(viewport.getCamera().combined);
		renderer.begin(ShapeType.Line);
		renderer.rect(x, y, width, height);
		renderer.end();
	}
	
	public void fillRect(float x, float y, float width, float height) {
		renderer.setColor(color);
		renderer.setProjectionMatrix(viewport.getCamera().combined);
		renderer.begin(ShapeType.Filled);
		renderer.rect(x, y, width, height);
		renderer.end();
	}
	
	public void setFont(BitmapFont font) {
		this.font = font;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
	/**
	 * Utility function. Converts mouse coordinates from screen to stage space.
	 * @return The converted vector
	 */
	public Vector2 mouseToStageCoordinates() {
		return toStageCoordinates(new Vector2(Gdx.input.getX(),
				Gdx.input.getY()));
	}
	
	/**
	 * Utility function. Converts the given screen coordinates to stage space.
	 * @param pos A vector in screen space
	 * @return The converted vector
	 */
	public Vector2 toStageCoordinates(Vector2 pos) {
		return viewport.unproject(pos);
	}
	
	/**
	 * Utility function. Converts the given screen coordinates to stage space.
	 * @param x 
	 * @param y
	 * @return The converted vector
	 */
	public Vector2 toStageCoordinates(float x, float y) {
		return toStageCoordinates(new Vector2(x, y));
	}
	
	/**
	 * Utility function. Converts the given stage coordinates to screen space.
	 * @param pos A vector in stage space
	 * @return The converted vector
	 */
	public Vector2 toScreenCoordinates(Vector2 pos) {
		Vector2 screenPos = viewport.project(pos);
		screenPos.y = viewport.getScreenHeight() - pos.y;
		return screenPos;
	}
	
	/**
	 * Utility function. Converts the given stage coordinates to screen space.
	 * @param x
	 * @param y
	 * @return The converted vector
	 */
	public Vector2 toScreenCoordinates(float x, float y) {
		return toScreenCoordinates(new Vector2(x, y));
	}
	
	/**
	 * Returns the frames per second (FPS) of this game.
	 * @return FPS
	 */
	public int getFramerate() {
		return Gdx.graphics.getFramesPerSecond();
	}
	
	public float getAspectRatio() {
		return viewport.getScreenWidth() / viewport.getScreenHeight();
	}
	
}
