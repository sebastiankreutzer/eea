package de.tu_darmstadt.informatik.eea;

import java.io.InputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class RWFile extends ROMFile {

	protected RWFile(String path) {
		super(Gdx.files.local(path));
	}

	/**
	 * @return The InputStream or null if the file cannot be read.
	 */
	public InputStream read() {
		try {
			return handle.read();
		} catch (GdxRuntimeException e) {
			return null;
		}
	}
	
	public OutputStream write(boolean append) {
		return handle.write(append);
	}

}
