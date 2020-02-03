package Impl.ExtrudeImpl;

import Config.PrintConstants;
import Framework.Position;
import Framework.Util;
import Framework.interfaces.Extrude;
import Framework.interfaces.ExtrudeAmount;

import java.util.ArrayList;
import java.util.Arrays;

public class D2ExtrudeImpl implements Extrude {
    Extrude flex, soild;
    ArrayList<String> path = new ArrayList<>();
    Util var = Util.getInstance();
    ExtrudeAmount ea;


    public D2ExtrudeImpl() {
        this.flex = new FlexExtrudeImpl();
        this.soild = new SolidExtrudeImpl();
    }

    @Override
    public String[] GenerateLayer(Position start, double length, double width, boolean first) {

        path.addAll(Arrays.asList(flex.GenerateLayer(start,length,width/2,first)));

        Position newPoint = new Position(start.getX(),start.getY()+width/2 + PrintConstants.NOZZLE_WIDTH,start.getZ());

        path.add(Util.getInstance().generateGoToPoint(newPoint,1200));

        path.addAll(Arrays.asList(soild.GenerateLayer(newPoint,length,PrintConstants.NOZZLE_WIDTH,first)));

        String[] returnValues = path.toArray(new String[0]);
        path.clear();

        return returnValues;
    }


}
