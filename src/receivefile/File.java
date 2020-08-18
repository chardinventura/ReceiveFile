package receivefile;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

public class File extends java.io.File implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private byte[] bytes;
	
	public File(String filePath) throws IOException {
		super(filePath);
		bytes = Files.readAllBytes(toPath());
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
