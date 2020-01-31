
M862.3 P "[printer_model]" ; printer model check
M862.1 P[nozzle_diameter] ; nozzle diameter check
G90 ; use absolute coordinates
M83 ; extruder relative mode
M104 S230 ; set extruder temp
M140 S85 ; set bed temp
M190 S85 ; wait for bed temp
M109 S230 ; wait for extruder temp
G28 W ; home all without mesh bed level
G80 ; mesh bed leveling
G1 Y-3.0 F1000.0 ; go outside print area
G92 E0.0
G1 X60.0 E9.0 F1000.0 ; intro line
G1 X100.0 E12.5 F1000.0 ; intro line
G92 E0.0
GG21 ; set units to millimeters
M83 ; use relative distances for extrusion
G0 X0.0 Y0.0 Z0.2 F300.0
G1 X20.0 F300 E20.0
G1 Y0.4 E20.4
G1 X0.0 E40.4
G1 Y0.8 E40.8
G1 X20.0 F300 E60.8
G1 Y1.2000000000000002 E61.2
G1 X0.0 E81.2
G1 Y1.6 E81.6
G1 X20.0 F300 E101.6
G1 Y2.0 E102.0
G1 X0.0 E122.0
G1 Y2.4000000000000004 E122.4
G1 X20.0 F300 E142.4
G1 Y2.8 E142.8
G1 X0.0 E162.8
G1 Y3.2 E163.2
G1 X20.0 F300 E183.2
G1 Y3.6 E183.6
G1 X0.0 E203.6
G1 Y4.0 E204.0
G1 X20.0 F300 E224.0
G1 Y4.4 E224.4
G1 X0.0 E244.4
G1 Y4.8 E244.8
G1 X20.0 E264.8
G0 X0.0 Y0.0 Z0.0 F1200.0
G4 ; WAIT
M104 S0 ; turn off temperature
M140 S0 ; turn off heatbed
M107 ; turn off fan
G1 X0 Y200; home X axis
G1 E-1 F900
M84 ; disable motors
