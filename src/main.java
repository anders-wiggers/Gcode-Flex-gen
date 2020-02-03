import Framework.Position;
import Framework.PrintFactory;
import Impl.*;
import Impl.BuilderImpl.BuilderFlexImpl;
import Framework.interfaces.Builder;
import Framework.interfaces.Writer;
import Impl.BuilderImpl.BuilderHalfRing;
import Impl.BuilderImpl.BuilderKvart;
import Impl.BuilderImpl.BuilderSolidImpl;
import Impl.ExtrudeImpl.FlexExtrudeImpl;
import Impl.ExtrudeImpl.SolidExtrudeImpl;

import java.io.IOException;

public class main {

    public static void main(String[] args) {
        System.out.println("Printing file");

        Builder builder = new BuilderHalfRing();

        PrintFactory pf = new PrintFactory(new FlexExtrudeImpl(), new SolidExtrudeImpl());

        Writer writer = new WriterImpl();
        try {
            writer.write("print", "");
            for (String s :builder.build(20,2,2,new Position(0,0,0.2))) {
                writer.append("print", s + "\n");
            }
        } catch (IOException io) {

        }

    }
}