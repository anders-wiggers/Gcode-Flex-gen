package Impl;

import Framework.interfaces.Writer;
import java.io.*;


public class WriterImpl implements Writer {
    @Override
    public boolean write(String filePath, String item) throws IOException{
        PrintWriter writer = new PrintWriter(filePath + ".gcode", "UTF-8");
        writer.println(item);
        writer.close();
        return false;
    }

    @Override
    public boolean append(String filePath, String item) throws IOException {
        File file = new File(filePath+".gcode");
        FileWriter fr = new FileWriter(file, true);
        fr.write(item);
        fr.close();
        return false;
    }
}
