package de.tu_darmstadt.informatik.eea;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
}
