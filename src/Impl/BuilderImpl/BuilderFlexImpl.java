package Impl.BuilderImpl;

import Config.PrintConstants;
import Impl.ExtrudeImpl.FlexExtrudeImpl;
import Framework.Position;
import Framework.Util;
import Framework.interfaces.Builder;
import Framework.interfaces.Extrude;

import java.util.ArrayList;
import java.util.Arrays;

public class BuilderFlexImpl implements Builder {
    private ArrayList<String> positions = new ArrayList<>();
    private Extrude extrude;
    private Util var = Util.getInstance();

    public BuilderFlexImpl(){}

    @Override
    public String[] build(double length, double width, double height, Position p) {
        for ( String s : PrintConstants.START_CODE ){
            positions.add(s);
        }
        positions.add(var.generateGoToPoint(p,300));

        extrude = new FlexExtrudeImpl();

        for ( double h = 0; h < height ; h += 0.2 ) {
            if (h == 0) {
                positions.addAll(Arrays.asList(extrude.GenerateLayer(p, length, width, true)));
            } else {
                positions.addAll(Arrays.asList(extrude.GenerateLayer(p, length, width, false)));
            }
            p.setZ(h+0.4);
            positions.add(var.generateGoToPoint(p,1200) + " ; END OF LOOP");
        }

        for(String s : PrintConstants.END_CODE){
            positions.add(s);
        }
        return positions.toArray(new String[0]);
    }
}
