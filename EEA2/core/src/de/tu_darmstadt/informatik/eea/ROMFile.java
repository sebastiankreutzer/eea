package de.tu_darmstadt.informatik.eea;

import java.io.IOException;
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

	public InputStream read() throws IOException {
		try {
			return handle.read();
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

}
