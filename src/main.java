import Framework.Position;
import Impl.*;
import Impl.BuilderImpl.BuilderFlexImpl;
import Framework.interfaces.Builder;
import Framework.interfaces.Writer;
import Impl.BuilderImpl.BuilderSolidImpl;

import java.io.IOException;

public class main {

    public static void main(String[] args) {
        System.out.println("Printing file");

        Builder builder = new BuilderSolidImpl();

        Writer writer = new WriterImpl();
        try {
            writer.write("print", "");
            for (String s : builder.build(20,10,5,new Position(0,0,0.2))) {
                writer.append("print", s + "\n");
            }
        } catch (IOException io) {

        }

    }
}