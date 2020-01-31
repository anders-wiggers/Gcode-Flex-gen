package Impl.BuilderImpl;

import Config.PrintConstants;
import Impl.ExtrudeImpl.FlexExtrudeImpl;
import Framework.Position;
import Impl.ExtrudeImpl.SolidExtrudeImpl;
import Framework.Variables;
import Framework.interfaces.Builder;
import Framework.interfaces.Extrude;

import java.util.ArrayList;

public class BuilderHalfRing implements Builder {
    private ArrayList<String> positions = new ArrayList<>();
    private Extrude extrudeFlex;
    private Extrude extrudeSolid;

    private Variables var = Variables.getInstance();

    public BuilderHalfRing(){}

    @Override
    public String[] build(double length, double width, double height, Position p) {
        for ( String s : PrintConstants.START_CODE ){
            positions.add(s);
        }

        positions.add(var.generateGoToPoint(p,300));

        extrudeFlex = new FlexExtrudeImpl();

        extrudeSolid = new SolidExtrudeImpl();

        for ( double h = 0; h < height ; h += 0.2 ) {


            p.setZ(h);
            positions.add(var.generateGoToPoint(p,1200));
        }

        for(String s : PrintConstants.END_CODE){
            positions.add(s);
        }
        return positions.toArray(new String[0]);
    }
}
