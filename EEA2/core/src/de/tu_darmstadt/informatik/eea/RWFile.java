package de.tu_darmstadt.informatik.eea;

import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class RWFile extends ROMFile {

	protected final String charset;

	protected RWFile(String path) {
		this(path, null);
	}

	protected RWFile(String path, String charset) {
		super(Gdx.files.local(path));
		this.charset = charset;
	}

	public Reader reader() {
		try {
			return handle.reader(charset);
		} catch (GdxRuntimeException e) {
			return null;
		}
	}

	public String readString() {
		return handle.readString(charset);
	}

	public OutputStream write(boolean append) {
		try {
			return handle.write(append);
		} catch (GdxRuntimeException e) {
			return null;
		}
	}

	public Writer writer(boolean append) {
		try {
			return handle.writer(append, charset);
		} catch (GdxRuntimeException e) {
			return null;
		}
	}

	public void writeString(String string, boolean append) {
		handle.writeString(string, append, charset);
	}

}
