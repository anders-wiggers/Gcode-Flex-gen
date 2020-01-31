package Impl;

import Config.PrintConstants;
import interfaces.Extrude;
import sun.security.util.Length;

import java.util.ArrayList;

public class FlexExtrudeImpl implements Extrude {
    ArrayList<String> path = new ArrayList<>();
    Variables var = Variables.getInstance();

    @Override
    public String[] GenerateLayer(Position start, double length, double width, boolean first) {
        String speed;
        if(first){
            speed = "F300";
        }else {
            speed = "F1200";
        }

        if( width < 2 * PrintConstants.NOZZLE_WIDTH ){
            var.addToLength(length);
            String movment = "G1 X" + (start.getX() + length)+ " E" + var.getExtrudedSoFar();
            path.add(movment);
        } else {
            if (start.getX() + length > 180) {
                System.out.println("Out of bounds");
            } else {
                var.addToLength(length);
                String movment = "G1 X" + (start.getX() + length) + " "+ speed + " E" + var.getExtrudedSoFar();
                path.add(movment);

                var.addToLength(PrintConstants.FILAMENT_SIZE);
                movment = "G1 Y" + (start.getY() + PrintConstants.NOZZLE_WIDTH) + " E" + var.getExtrudedSoFar();
                path.add(movment);

                var.addToLength(length);
                movment = "G1 X" + start.getX() + " E" + var.getExtrudedSoFar();
                path.add(movment);

                var.addToLength(PrintConstants.FILAMENT_SIZE);
                movment = "G1 Y" + (start.getY() + (PrintConstants.NOZZLE_WIDTH * 2)) + " E" + var.getExtrudedSoFar();
                path.add(movment);

                Position p = new Position(start.getX(), var.round(start.getY() + PrintConstants.NOZZLE_WIDTH * 2), start.getZ());

                return GenerateLayer(p, length, width - 2 * PrintConstants.NOZZLE_WIDTH, first);
            }
        }

        return path.toArray(new String[0]);
    }

}
