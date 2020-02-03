import Framework.Position;
import Framework.PrintFactory;
import Impl.*;
import Framework.interfaces.Builder;
import Framework.interfaces.Writer;
import Impl.BuilderImpl.BuilderD3;
import Impl.ExtrudeImpl.FlexExtrudeImpl;
import Impl.ExtrudeImpl.SolidExtrudeImpl;

import java.io.IOException;

public class main {

    public static void main(String[] args) {
        System.out.println("Printing file");

        Builder builder = new BuilderD3();

        PrintFactory pf = new PrintFactory(new FlexExtrudeImpl(), new SolidExtrudeImpl());

        Writer writer = new WriterImpl();
        try {
            writer.write("print", "");
            for (String s :builder.build(20,2,2,new Position(0,0,0.2))) {
            //for (String s : pf.outputPrint(20,2,0.2)) {
                writer.append("print", s + "\n");
            }
        } catch (IOException io) {

        }

    }
}