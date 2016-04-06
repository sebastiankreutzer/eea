package de.tu_darmstadt.informatik.eea;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * This class provides simplified access to files in a read-only mode. Useful
 * for opening files located in the asset folder.
 * 
 * @author jr
 *
 */
public class ROMFile {

	protected final FileHandle handle;
	protected final String charset;

	/**
	 * Creates a new ROMFile.
	 * 
	 * @param fileHandle
	 *            The file handle for this ROMFile.
	 * @param charset
	 *            The charset for this ROMFile, may be null.
	 */
	protected ROMFile(FileHandle fileHandle, String charset) {
		handle = fileHandle;
		this.charset = charset;
	}

	/**
	 * @return Returns true if the file exists.
	 */
	public boolean exists() {
		return handle.exists();
	}

	/**
	 * @return An InputStream for reading this file.
	 * @throws IOException
	 *             if the file represents a directory, doesn't exist, or could
	 *             not be read.
	 */
	public InputStream read() throws IOException {
		try {
			return handle.read();
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

	/**
	 * @return A reader for reading this file.
	 * @throws IOException
	 *             if the file represents a directory, doesn't exist, or could
	 *             not be read.
	 */
	public Reader reader() throws IOException {
		try {
			return handle.reader(charset);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

	/**
	 * @return A single string containing the whole content of this file.
	 * @throws IOException
	 *             if the file represents a directory, doesn't exist, or could
	 *             not be read.
	 */
	public String readString() throws IOException {
		try {
			return handle.readString(charset);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}
}
