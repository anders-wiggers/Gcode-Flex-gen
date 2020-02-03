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

public class BuilderD3 implements Builder {
    private ArrayList<String> positions = new ArrayList<>();
    private Extrude extrudeFlex;
    private Extrude extrudeSolid;

    private Util util = Util.getInstance();

    public BuilderD3(){}

    @Override
    public String[] build(double length, double width, double height, Position p) {
        for ( String s : PrintConstants.START_CODE ){
            positions.add(s);
        }

        double correctWidth = Math.floor( width / ( 2 * PrintConstants.FILAMENT_SIZE )) * (2 * PrintConstants.FILAMENT_SIZE);
        double correctLength = Math.floor( length / ( 2 * PrintConstants.FILAMENT_SIZE )); //Fixed :)))

        positions.add(util.generateGoToPoint(p,300));

        extrudeFlex = new FlexExtrudeImpl();

        extrudeSolid = new SolidExtrudeImpl();

        for ( double h = 0; h < height ; h += 0.2 ) {
            if (h == 0) {
                positions.addAll(Arrays.asList(extrudeSolid.GenerateLayer(p,correctLength,correctWidth,true)));
            } else if (h > height/2) {
                positions.addAll(Arrays.asList(extrudeFlex.GenerateLayer(p,correctLength,correctWidth,true)));
            } else {
                positions.addAll(Arrays.asList(extrudeSolid.GenerateLayer(p,correctLength, correctWidth, false)));
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
