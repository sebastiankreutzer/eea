package de.tu_darmstadt.informatik.tanks2.factories;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.tu_darmstadt.informatik.eea.IResourcesManager;
import de.tu_darmstadt.informatik.eea.entity.AnimationRenderComponent;
import de.tu_darmstadt.informatik.eea.entity.Entity;

/**
 * Eine Factory zum erzeugen von Explosionen.
 * 
 * @author jr
 *
 */
public class ExplosionFactory {

	private static int counter = 0;
	private boolean debug;

	private final String[] animationFrameNames = { "expl01.png", "expl02.png", "expl03.png", "expl04.png", "expl05.png",
			"expl06.png", "expl07.png", "expl08.png", "expl09.png", "expl10.png", "expl11.png", "expl12.png",
			"expl13.png", "expl14.png", "expl15.png", "expl16.png" };

	IResourcesManager resourcesManager;
	TextureRegion[] animationFrames = null;

	/**
	 * Erstellt eine Factory die Explosionen erzeugt.
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager
	 */
	public ExplosionFactory(IResourcesManager resourcesManager) {
		this(resourcesManager, false);
	}

	/**
	 * Erstellt eine Factory die Explosionen erzeugt.
	 * 
	 * @param resourcesManager
	 *            Der ResourcesManager
	 * @param debug
	 *            Debugmodus an (true) oder aus (false)
	 */
	public ExplosionFactory(IResourcesManager resourcesManager, boolean debug) {
		this.resourcesManager = resourcesManager;
		this.debug = debug;
		// Weise den ResourcesManager an die benoetigten Texturen vorzuladen.
		for (String name : animationFrameNames) {
			resourcesManager.loadTextureAsync(name);
		}
	}

	public Entity createExplosion(float x, float y, float duration, float width, float height, boolean debug) {
		// Erstelle eine neue Explosion und setze die Parameter
		Entity explosion = new Entity("Explosion" + (counter++));
		explosion.setBounds(x, y, width, height);

		// Wenn die Frames der Animation noch nicht initialisiert sind
		if (animationFrames == null) {
			// Erstelle ein Array von TextureRegions und fuelle es
			int frames = animationFrameNames.length;
			animationFrames = new TextureRegion[frames];
			for (int i = 0; i < frames; i++) {
				animationFrames[i] = new TextureRegion(resourcesManager.getTexture(animationFrameNames[i]));
			}
		}

		// Erstelle eine AnimationRenderComponent
		AnimationRenderComponent animation = new AnimationRenderComponent(duration, animationFrames);
		// Nach dem Ende der Animation soll die Entity entfernt werden
		animation.setRemoveWhenFinished(true);

		// Fuege die RenderComponent hinzu und gebe die Entity zurueck
		explosion.addComponent(animation);
		return explosion;
	}

}
