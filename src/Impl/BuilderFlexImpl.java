package Impl;

import Config.PrintConstants;
import interfaces.Builder;
import interfaces.Extrude;

import java.util.ArrayList;
import java.util.Arrays;

public class BuilderFlexImpl implements Builder {
    private ArrayList<String> positions = new ArrayList<>();
    private Extrude extrude;
    private Variables var = Variables.getInstance();

    public BuilderFlexImpl(){}

    @Override
    public String[] build(double length, double width, double height) {
        for ( String s : PrintConstants.START_CODE ){
            positions.add(s);
        }

        Position p = new Position(0,0,0.2);

        positions.add(var.generateGoToPoint(p,300));

        extrude = new FlexExtrudeImpl();

        for ( double h = 0; h < height ; h += 0.2 ) {
            if (h == 0) {
                positions.addAll(Arrays.asList(extrude.GenerateLayer(p, length, width, true)));
            } else {
                positions.addAll(Arrays.asList(extrude.GenerateLayer(p, length, width, false)));
            }
            p.setZ(h);
            positions.add(var.generateGoToPoint(p,1200));
        }

        for(String s : PrintConstants.END_CODE){
            positions.add(s);
        }
        return positions.toArray(new String[0]);
    }
}
