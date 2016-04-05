package de.tu_darmstadt.informatik.eea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ROMFile {

	protected final FileHandle handle;
	protected final String charset;

	protected ROMFile(String path, FileType type, String charset) throws FileNotFoundException {
		try {
			handle = Gdx.files.getFileHandle(path, type);
			this.charset = charset;
		} catch (GdxRuntimeException e) {
			throw new FileNotFoundException(e.getLocalizedMessage());
		}
	}
	
	protected ROMFile(FileHandle fileHandle, String charset) {
		handle = fileHandle;
		this.charset = charset;
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

	public Reader reader() throws IOException {
		try {
			return handle.reader(charset);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}
	
	public String readString() throws IOException {
		try {
			return handle.readString(charset);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}
}
