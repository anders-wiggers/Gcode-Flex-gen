import Framework.Position;
import Framework.PrintFactory;
import Impl.*;
import Framework.interfaces.Builder;
import Framework.interfaces.Writer;
import Impl.BuilderImpl.BuilderD3;
import Impl.BuilderImpl.BuilderSolidImpl;
import Impl.ExtrudeImpl.D3ExtrudeImpl;
import Impl.ExtrudeImpl.D7ExtrudeImpl;
import Impl.ExtrudeImpl.FlexExtrudeImpl;
import Impl.ExtrudeImpl.SolidExtrudeImpl;

import java.io.IOException;

public class main {

    public static void main(String[] args) {
        System.out.println("Printing file");

        Builder builder = new BuilderD3();

        PrintFactory pf = new PrintFactory(new D3ExtrudeImpl(), new D7ExtrudeImpl());

        Writer writer = new WriterImpl();
        try {
            writer.write("print", "");
            //for (String s :builder.build(140,3,2,new Position(10,10,0.2))) {
            for (String s : pf.outputPrint(140,2,1.2)) {
                writer.append("print", s + "\n");
            }
        } catch (IOException io) {

        }

    }
}