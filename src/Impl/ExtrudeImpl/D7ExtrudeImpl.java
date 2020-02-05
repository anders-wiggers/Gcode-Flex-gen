package Impl.ExtrudeImpl;

import Config.PrintConstants;
import Framework.Position;
import Framework.Util;
import Framework.interfaces.Extrude;
import Framework.interfaces.ExtrudeAmount;

import java.util.ArrayList;
import java.util.Arrays;

public class D7ExtrudeImpl implements Extrude {
    Extrude flex, soild;
    ArrayList<String> path = new ArrayList<>();
    Util var = Util.getInstance();
    ExtrudeAmount ea;


    public D7ExtrudeImpl() {
        this.flex = new FlexExtrudeImpl();
        this.soild = new SolidExtrudeImpl();
    }

    @Override
    public String[] GenerateLayer(Position start, double length, double width, boolean first) {

        if ( start.getZ() > Util.getInstance().getHeight()/2 ) path.addAll(Arrays.asList(soild.GenerateLayer(start,length,width,first)));
        else path.addAll(Arrays.asList(flex.GenerateLayer(start,length,width,first)));
        String[] returnValues = path.toArray(new String[0]);
        path.clear();

        return returnValues;
    }


}
