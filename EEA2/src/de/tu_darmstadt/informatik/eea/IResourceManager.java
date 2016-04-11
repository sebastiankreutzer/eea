package de.tu_darmstadt.informatik.eea;

import java.io.FileNotFoundException;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public interface IResourceManager {

	public enum PathName {
		RELATIVE, ABSOLUTE;
	};

	/**
	 * Opens a ROM file with the default charset.
	 * 
	 * @param path
	 *            The relative file path.
	 * @return A read-only ROMFile.
	 * @throws FileNotFoundException
	 *             If the file does not exist or cannot be read.
	 */
	public ROMFile openROMFile(String path) throws FileNotFoundException;

	/**
	 * Opens a ROMFile from specified path and path type.
	 * 
	 * @param path
	 *            The file path.
	 * @param type
	 *            The {@link PathName} type for the path.
	 * @return A read-only ROMFile.
	 * @throws FileNotFoundException
	 *             If the file does not exist or cannot be read.
	 */
	public ROMFile openROMFile(String path, PathName type) throws FileNotFoundException;

	/**
	 * Opens a ROMFile with the specified charset from the specified path and
	 * path type.
	 * 
	 * @param path
	 *            The file path.
	 * @param type
	 *            The {@link PathName} type for the path.
	 * @param charset
	 *            The charset, may be null.
	 * @return A read-only ROMFile that uses the specified charset for read
	 *         operations.
	 * @throws FileNotFoundException
	 *             If the file does not exist or cannot be read.
	 */
	public ROMFile openROMFile(String path, PathName type, String charset) throws FileNotFoundException;

	/**
	 * Opens a RWFile or creates a new one if it does not exist.
	 * 
	 * @param path
	 *            The relative file path.
	 */
	public RWFile openRWFile(String path);

	/**
	 * Opens a RWFile or creates a new one if it does not exist.
	 * 
	 * @param path
	 *            The file path.
	 * @param type
	 *            The {@link PathName} type for the path.
	 * @return A read- and writable RWFile.
	 */
	public RWFile openRWFile(String path, PathName type);

	/**
	 * Opens a RWFile or creates a new one if it does not exist, with the
	 * specified charset.
	 * 
	 * @param path
	 *            The file path.
	 * @param type
	 *            The {@link PathName} type for the path.
	 * @param charset
	 *            The charset, may be null.
	 * @return A read- and writable RWFile that uses the specified charset for
	 *         read operations.
	 */
	public RWFile openRWFile(String path, PathName type, String charset);

	/**
	 * Loads a texture from the given path.
	 * 
	 * @param path
	 *            The file path
	 * @return The texture.
	 */
	public Texture getTexture(String path);

	/**
	 * Starts loading a texture asynchronously.
	 * 
	 * @param path
	 *            The file path
	 */
	public void loadTextureAsync(String path);

	/**
	 * Loads a TextureAtlas from the given path.
	 * 
	 * @param path
	 *            The file path
	 * @return The texture.
	 */
	public TextureAtlas getTextureAtlas(String path);

	/**
	 * Starts loading a TextureAtlas asynchronously.
	 * 
	 * @param path
	 *            The file path
	 */
	public void loadTextureAtlasAsynch(String path);

	/**
	 * Loads a sound from the given path.
	 * 
	 * @param path
	 *            The file path
	 */
	public Sound getSound(String path);

	/**
	 * Starts loading a sound asynchronously.
	 * 
	 * @param path
	 *            The file path
	 */
	public void loadSoundAsync(String path);

	/**
	 * Loads music from the given path.
	 * 
	 * @param path
	 *            The file path
	 */
	public Music getMusic(String path);

	/**
	 * Starts loading music asynchronously.
	 * 
	 * @param path
	 *            The file path
	 */
	public void loadMusicAsync(String path);
	
	/**
	 * Loads a font from the given path.
	 * 
	 * @param path
	 *            The file path
	 */
	public BitmapFont getFont(String path);
	
	/**
	 * Starts loading a font asynchronously.
	 * 
	 * @param path
	 *            The file path
	 */
	public void loadFontAsync(String path);

	public void update();
}
