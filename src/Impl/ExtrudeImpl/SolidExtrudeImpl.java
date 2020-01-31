package Impl.ExtrudeImpl;

import Framework.Position;
import Framework.Util;
import Framework.interfaces.Extrude;

import java.util.ArrayList;

import Config.PrintConstants;
import Framework.interfaces.ExtrudeAmount;


public class SolidExtrudeImpl implements Extrude {
    ArrayList<String> path = new ArrayList<>();
    Util var = Util.getInstance();

    ExtrudeAmount ea;

    public SolidExtrudeImpl(){
        ea = new ExtrudeAmountProsaI3MK3();
    }

    @Override
    public String[] GenerateLayer(Position start, double length, double width, boolean first) {
        String speed;
        if(first){
            speed = "F300";
        }else {
            speed = "F1200";
        }

        if( length < 2 * PrintConstants.NOZZLE_WIDTH ){
            String movment = "G1 Y" + (start.getY() + width)+ " E" + ea.CalculateExtrude(width);
            path.add(movment);
        } else {
            if (start.getY() + width > 180) {
                System.out.println("Out of bounds");
            } else {
                String movment = "G1 Y" + (start.getY() + width) + " "+ speed + " E" + ea.CalculateExtrude(width);
                path.add(movment);

                var.addToLength(PrintConstants.FILAMENT_SIZE);
                movment = "G1 X" + (start.getX() + PrintConstants.NOZZLE_WIDTH) + " E" + ea.CalculateExtrude(PrintConstants.FILAMENT_SIZE);
                path.add(movment);

                movment = "G1 Y" + start.getY() + " E" + ea.CalculateExtrude(width);
                path.add(movment);

                movment = "G1 X" + (start.getX() + (PrintConstants.NOZZLE_WIDTH * 2)) + " E" + ea.CalculateExtrude(PrintConstants.FILAMENT_SIZE);
                path.add(movment);

                Position p = new Position(var.round(start.getX() + PrintConstants.NOZZLE_WIDTH * 2), start.getY(), start.getZ());

                return GenerateLayer(p, length - 2*PrintConstants.NOZZLE_WIDTH, width, first);
            }
        }

        String[] returnValues = path.toArray(new String[0]);

        path.clear();

        return returnValues;
    }

}
