package Framework;

import Config.PrintConstants;
import Framework.interfaces.Extrude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintFactory {
    private ArrayList<Extrude> extrudes = new ArrayList<>();
    private Util util = Util.getInstance();

    public PrintFactory(Extrude ... e) {
        for( Extrude extrude : e ){
            extrudes.add(extrude);
        }
    }

    public String[] outputPrint(double length, double width, double height){
        Position startPosition = new Position(0,0,0.2);
        List<String> commands = new ArrayList<>();

        double lengthPerExtrude = length / extrudes.size();
        double CorrectWidth = Math.floor( width / ( 2 * PrintConstants.FILAMENT_SIZE )) * (2 * PrintConstants.FILAMENT_SIZE);

        commands = addStartCode(commands);

        commands.add(util.generateGoToPoint(startPosition,300));


        for ( double h = 0; h < height ; h += 0.2 ) {

            Position baseline = new Position(startPosition.getX(),startPosition.getY(),startPosition.getZ());

            if ( h == 0 ) {
                for ( Extrude e : extrudes) {
                    commands.addAll(Arrays.asList(e.GenerateLayer(baseline,lengthPerExtrude,CorrectWidth,true)));
                    baseline.setX( baseline.getX() + lengthPerExtrude );

                    commands.add(util.generateGoToPoint(baseline,300));
                }
            } else {
                for ( Extrude e : extrudes) {
                    commands.addAll(Arrays.asList(e.GenerateLayer(baseline,lengthPerExtrude,CorrectWidth,false)));
                    baseline.setX( baseline.getX() + lengthPerExtrude );

                    commands.add(util.generateGoToPoint(baseline,1200));
                }
            }

            startPosition.setZ(h+0.4);
            commands.add(util.generateGoToPoint(startPosition,1200) + " ;  END OF LOOP");

        }

        commands = addEndCode(commands);


        return commands.toArray(new String[0]);
    }

    private List<String> addStartCode(List<String> input){
        for ( String s : PrintConstants.START_CODE ){
            input.add(s);
        }
        return input;
    }

    private List<String> addEndCode(List<String> input){
        for ( String s : PrintConstants.END_CODE ){
            input.add(s);
        }
        return input;
    }

}
