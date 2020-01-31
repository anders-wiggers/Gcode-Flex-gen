import Impl.*;
import interfaces.Builder;
import interfaces.Extrude;
import interfaces.Writer;

import java.io.IOException;

public class main {

    public static void main(String[] args) {
        System.out.println("Printing file");

        Builder builder = new BuilderFlexImpl();

        Writer writer = new WriterImpl();
        try {
            writer.write("print", "");
            for (String s : builder.build(20,5,0.2)) {
                writer.append("print", s + "\n");
            }
        } catch (IOException io) {

        }

    }
}