package interfaces;

import Impl.Position;

public interface Extrude {

    String[] GenerateLayer(Position start, double length, double width, boolean first);



}
