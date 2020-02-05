package Config;

public class PrintConstants {
    public static double NOZZLE_WIDTH = 0.4;
    public static double FILAMENT_SIZE = 0.4;


    public static String[] END_CODE = {
            "G4 ; WAIT",
            "M104 S0 ; turn off temperature",
            "M140 S0 ; turn off heatbed",
            "M107 ; turn off fan",
            "G1 X0 Y200; home X axis",
            "G1 E-1 F900",
            "M84 ; disable motors"
    };

    public static String[] START_CODE  = {
            "M862.3 P \"[printer_model]\" ; printer model check",
            "M862.1 P[nozzle_diameter] ; nozzle diameter check",
            "G90 ; use absolute coordinates",
            "M83 ; extruder relative mode",
            "M104 S220 ; set extruder temp",
            "M140 S65 ; set bed temp",
            "M190 S65 ; wait for bed temp",
            "M109 S220 ; wait for extruder temp",
            "G28 W ; home all without mesh bed level",
            "G80 ; mesh bed leveling",
            "G1 Y-3.0 F1000.0 ; go outside print area",
            "G92 E0.0",
            "G1 X60.0 E9.0 F1000.0 ; intro line",
            "G1 X100.0 E12.5 F1000.0 ; intro line",
            "G92 E0.0",
            "GG21 ; set units to millimeters",
            "M83 ; use relative distances for extrusion",

    };

}
