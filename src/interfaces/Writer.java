package interfaces;

import java.io.IOException;

public interface Writer {

    boolean write(String filePath, String item) throws IOException;

    boolean append(String filePath, String item) throws IOException;

}
