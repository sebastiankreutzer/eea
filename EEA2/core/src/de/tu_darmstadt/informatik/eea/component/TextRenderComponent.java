package de.tu_darmstadt.informatik.eea.component;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.tu_darmstadt.informatik.eea.EEAGame;
import de.tu_darmstadt.informatik.eea.EEAGraphics;

/**
 * A component for drawing text.
 * 
 * @author jr
 *
 */
public class TextRenderComponent extends EEARenderComponent {

	public static final String ID = "TextRenderComponent";

	protected String text;
	protected BitmapFont font;

	/**
	 * Creates a new TextRenderComponent with the default font of
	 * {@link EEAGraphics}.
	 * 
	 * @param text
	 *            The text that should be drawn.
	 */
	public TextRenderComponent(String text) {
		this(text, EEAGame.getGraphics().getFont());
	}

	/**
	 * Creates a new TextRenderComponent with a specified font.
	 * 
	 * @param text
	 *            The text that should be drawn.
	 * @param font
	 *            The font for rendering the text.
	 */
	public TextRenderComponent(String text, BitmapFont font) {
		super(ID);
		this.text = text;
		this.font = font;
	}

	/**
	 * Changes the text this RenderComponent draws.
	 * 
	 * @param text
	 *            The new text.
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void render(Batch batch) {
		font.draw(batch, text, owner.getX(), owner.getY() + owner.getHeight());
	}

}
