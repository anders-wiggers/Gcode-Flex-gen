package Impl.BuilderImpl;

import Config.PrintConstants;
import Impl.ExtrudeImpl.FlexExtrudeImpl;
import Framework.Position;
import Impl.ExtrudeImpl.SolidExtrudeImpl;
import Framework.Util;
import Framework.interfaces.Builder;
import Framework.interfaces.Extrude;

import java.util.ArrayList;
import java.util.Arrays;

public class BuilderHalfRing implements Builder {
    private ArrayList<String> positions = new ArrayList<>();
    private Extrude extrudeFlex;
    private Extrude extrudeSolid;

    private Util util = Util.getInstance();

    public BuilderHalfRing(){}

    @Override
    public String[] build(double length, double width, double height, Position p) {
        for ( String s : PrintConstants.START_CODE ){
            positions.add(s);
        }

        positions.add(util.generateGoToPoint(p,300));

        extrudeFlex = new FlexExtrudeImpl();

        extrudeSolid = new SolidExtrudeImpl();



        for ( double h = 0; h < height ; h += 0.2 ) {
            if (h == 0) {
                positions.addAll(Arrays.asList(extrudeSolid.GenerateLayer(p,length,width,true)));
            } else if (h > height/2) {
                positions.addAll(Arrays.asList(extrudeFlex.GenerateLayer(p,length,width,true)));
            } else {
                positions.addAll(Arrays.asList(extrudeSolid.GenerateLayer(p, length, width, false)));
            }
            p.setZ(h+0.4);
            positions.add(util.generateGoToPoint(p,1200) + " ;  END OF LOOP");
        }
        for(String s : PrintConstants.END_CODE){
            positions.add(s);
        }
        return positions.toArray(new String[0]);
    }
}
