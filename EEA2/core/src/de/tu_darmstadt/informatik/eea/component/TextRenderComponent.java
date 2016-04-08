package de.tu_darmstadt.informatik.eea.component;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.tu_darmstadt.informatik.eea.EEA;

public class TextRenderComponent extends EEARenderComponent {
	
	public static final String ID = "TextRenderComponent";
	
	protected String text;
	protected BitmapFont font;
	
	public TextRenderComponent(String text){
		this(text, EEA.getGraphics().getFont());
	}
	
	public TextRenderComponent(String text, BitmapFont font) {
		super(ID);
		this.text = text;
		this.font = font;
	}
	
	public void setText(String text){
		this.text = text;
	}

	@Override
	public void render(Batch batch) {
		font.draw(batch, text, owner.getX(), owner.getY() + owner.getHeight());
	}

}
