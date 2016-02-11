package de.tu_darmstadt.informatik.tanks2.misc;

import com.badlogic.gdx.utils.GdxRuntimeException;

import de.tu_darmstadt.informatik.eea.IResourcesManager;

public class SourceFile {

	public static final char EOL = '\n';
	public static final char EOT = '\u0000';

	private java.io.InputStream source;
	private int currentLine;

	public SourceFile(String filename, IResourcesManager resourcesManager) {
		try {
			source = resourcesManager.openROMFile(filename).read();
			currentLine = 1;
		} catch (GdxRuntimeException e) {
			System.out.println("Error loading file " + filename + " : " + e.toString());
			source = null;
			currentLine = 0;
		}
	}

	char getSource() {
		try {
			int c = source.read();

			if (c == -1) {
				c = EOT;
			} else if (c == EOL) {
				currentLine++;
			}
			return (char) c;
		} catch (java.io.IOException s) {
			return EOT;
		}
	}

	public int getCurrentLine() {
		return currentLine;
	}
}
