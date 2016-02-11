package de.tu_darmstadt.informatik.eea;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EEAGraphics {
	
	private BitmapFont font;
	public static ShapeRenderer render = new ShapeRenderer();
	private Batch batch;
	
	public EEAGraphics(Viewport viewport) {
		font = new BitmapFont();
	}
	
	public void drawString(Batch batch, String string, float x, float y){
		font.draw(batch, string, x, y);
	}
	
	public void drawShape(){
		render.begin(ShapeType.Filled);
		render.box(0, 10, 20, 100, 100, 100);
		render.end();
	}
}
