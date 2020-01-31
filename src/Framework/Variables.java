package Framework;

public class Variables {
    private static Variables var;
    private double extrudedSoFar;

    private Variables(){
        extrudedSoFar = 0;
    }

    public static Variables getInstance() {
        {
            if (var==null)
                var = new Variables();
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
}
