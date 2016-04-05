package de.tu_darmstadt.informatik.eea;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class RWFile extends ROMFile {

	protected RWFile(String path, FileType type, String charset) {
		super(Gdx.files.getFileHandle(path, type), charset);
	}

	public OutputStream write(boolean append) throws IOException {
		try {
			return handle.write(append);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

	public Writer writer(boolean append) throws IOException {
		try {
			return handle.writer(append, charset);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

	public void writeString(String string, boolean append) throws IOException {
		try {
			handle.writeString(string, append, charset);
		} catch (GdxRuntimeException e) {
			throw new IOException(e.getLocalizedMessage(), e.getCause());
		}
	}

}
