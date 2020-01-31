package Impl.ExtrudeImpl;

import Config.PrintConstants;
import Framework.Position;
import Framework.Util;
import Framework.interfaces.Extrude;
import Framework.interfaces.ExtrudeAmount;

import java.util.ArrayList;

public class FlexExtrudeImpl implements Extrude {
    ArrayList<String> path = new ArrayList<>();
    Util var = Util.getInstance();
    ExtrudeAmount ea;

    public FlexExtrudeImpl(){
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

        double adjustedLength = Math.floor( length / (2 * PrintConstants.NOZZLE_WIDTH )) * (2 * PrintConstants.NOZZLE_WIDTH);

        if( width < 2 * PrintConstants.NOZZLE_WIDTH ){
            String movment = "G1 X" + (start.getX() + adjustedLength)+ " E" + ea.CalculateExtrude(adjustedLength);
            path.add(movment);
        } else {
            if (start.getX() + adjustedLength > 180) {
                System.out.println("Out of bounds");
            } else {
                String movment = "G1 X" + (start.getX() + adjustedLength) + " "+ speed + " E" + ea.CalculateExtrude(adjustedLength);
                path.add(movment);

                movment = "G1 Y" + (start.getY() + PrintConstants.NOZZLE_WIDTH) + " E" + ea.CalculateExtrude(PrintConstants.FILAMENT_SIZE);
                path.add(movment);

                movment = "G1 X" + start.getX() + " E" + ea.CalculateExtrude(adjustedLength);
                path.add(movment);

                movment = "G1 Y" + (start.getY() + (PrintConstants.NOZZLE_WIDTH * 2)) + " E" + ea.CalculateExtrude(PrintConstants.FILAMENT_SIZE);
                path.add(movment);

                Position p = new Position(start.getX(), var.round(start.getY() + PrintConstants.NOZZLE_WIDTH * 2), start.getZ());

                return GenerateLayer(p, length, width - 2 * PrintConstants.NOZZLE_WIDTH, first);
            }
        }
        String[] returnValues = path.toArray(new String[0]);

        path.clear();

        return returnValues;
    }

}
