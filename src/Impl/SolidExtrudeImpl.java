package Impl;

import interfaces.Extrude;

import java.util.ArrayList;

import Config.PrintConstants;



public class SolidExtrudeImpl implements Extrude {
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

        if( length < 2 * PrintConstants.NOZZLE_WIDTH ){
            var.addToLength(width);
            String movment = "G1 Y" + (start.getY() + width)+ " E" + var.getExtrudedSoFar();
            path.add(movment);
        } else {
            if (start.getY() + width > 180) {
                System.out.println("Out of bounds");
            } else {
                var.addToLength(width);
                String movment = "G1 Y" + (start.getY() + width) + " "+ speed + " E" + var.getExtrudedSoFar();
                path.add(movment);

                var.addToLength(PrintConstants.FILAMENT_SIZE);
                movment = "G1 X" + (start.getX() + PrintConstants.NOZZLE_WIDTH) + " E" + var.getExtrudedSoFar();
                path.add(movment);

                var.addToLength(width);
                movment = "G1 Y" + start.getY() + " E" + var.getExtrudedSoFar();
                path.add(movment);

                var.addToLength(PrintConstants.FILAMENT_SIZE);
                movment = "G1 X" + (start.getX() + (PrintConstants.NOZZLE_WIDTH * 2)) + " E" + var.getExtrudedSoFar();
                path.add(movment);

                Position p = new Position(var.round(start.getX() + PrintConstants.NOZZLE_WIDTH * 2), start.getY(), start.getZ());

                return GenerateLayer(p, length - 2*PrintConstants.NOZZLE_WIDTH, width, first);
            }
        }

        return path.toArray(new String[0]);
    }


}
