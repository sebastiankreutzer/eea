package de.tu_darmstadt.informatik.eea;

import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ROMFile {

	protected final FileHandle handle;

	public ROMFile(String path) {
		this(Gdx.files.internal(path));
	}
	
	protected ROMFile(FileHandle handle) {
		this.handle = handle;
	}
	
	public boolean exists() {
		return handle.exists();
	}

	public InputStream read() {
		try {
			return handle.read();
		} catch (GdxRuntimeException e) {
			throw e;
		}
	}

}
