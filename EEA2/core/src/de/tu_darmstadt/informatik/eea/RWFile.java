package de.tu_darmstadt.informatik.eea;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * This class provides additional methods for writing to a file. Note that files
 * in the asset folder cannot be used, due to their read-only nature.
 * 
 * @author jr
 *
 */
public class RWFile extends ROMFile {

	/**
	 * Creates a new RWFile.
	 * 
	 * @param fileHandle
	 *            The file handle for this RWFile.
	 * @param charset
	 *            The charset for this RWFile.
	 */
	protected RWFile(FileHandle fileHandle, String charset) {
		super(fileHandle, charset);
	}

	/**
	 * Returns an OutputStream for writing to this file. If the file does not
	 * yet exist it will be created.
	 * 
	 * @param append
	 *            Whether the current content should be preserved or overwritten.
	 * @return A OutputStream for writing.
	 * @throws IOException
	 *             if this file handle represents a directory or if it could not
	 *             be written.
	 */
	public OutputStream write(boolean append) throws IOException {
		try {
			return handle.write(append);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

	/**
	 * Returns a Writer for writing to this file. If the file does not
	 * yet exist it will be created.
	 * 
	 * @param append
	 *            Whether the current content should be preserved or overwritten.
	 * @return A Writer object for writing.
	 * @throws IOException
	 *             if this file handle represents a directory or if it could not
	 *             be written.
	 */
	public Writer writer(boolean append) throws IOException {
		try {
			return handle.writer(append, charset);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

	/**
	 * Writes a single string into this file. If the file does not
	 * yet exist it will be created.
	 * 
	 * @param append
	 *            Whether the current content should be preserved or overwritten.
	 * @throws IOException
	 *             if this file handle represents a directory or if it could not
	 *             be written.
	 */
	public void writeString(String string, boolean append) throws IOException {
		try {
			handle.writeString(string, append, charset);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

}
