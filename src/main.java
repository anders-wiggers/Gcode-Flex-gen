import Framework.Position;
import Impl.*;
import Impl.BuilderImpl.BuilderFlexImpl;
import Framework.interfaces.Builder;
import Framework.interfaces.Writer;
import Impl.BuilderImpl.BuilderHalfRing;
import Impl.BuilderImpl.BuilderKvart;
import Impl.BuilderImpl.BuilderSolidImpl;

import java.io.IOException;

public class main {

    public static void main(String[] args) {
        System.out.println("Printing file");

        Builder builder = new BuilderKvart();

        Writer writer = new WriterImpl();
        try {
            writer.write("print", "");
            for (String s : builder.build(10,2,2,new Position(0,0,0.2))) {
                writer.append("print", s + "\n");
            }
        } catch (IOException io) {

        }

    }
}