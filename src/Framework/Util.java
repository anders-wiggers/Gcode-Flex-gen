package Framework;

public class Util {
    private static Util var;
    private double extrudedSoFar;

    private Util(){
        extrudedSoFar = 0;
    }

    public static Util getInstance() {
        {
            if (var==null)
                var = new Util();
            return var;
        }
    }

    public double getExtrudedSoFar() {
        return extrudedSoFar;
    }

    public void addToLength(double l){
        extrudedSoFar += l;
        extrudedSoFar = Math.round(extrudedSoFar * 100.0) / 100.0;
    }

    public double round(double l){
        return Math.round(l * 100.0) / 100.0;
    }

    public String generateGoToPoint(Position p, double speed){
        return "G0 X" + p.getX() + " Y" +p.getY() + " Z" + p.getZ() + " F" + speed;
    }

    public String binder(Position p1, Position p2){
        String movement;
        if ( p1.getX() == p2.getX() ) {
            String distance = "" + (p2.getY() - p1.getY());
            movement = "G1 Y" + p2.getY() + " E" + distance;
        }
        else {
            String distance = "" + (p2.getX() - p1.getX());
            movement = "G1 X" + p2.getX() + " E" + distance;
        }


        return movement;
    }
}
