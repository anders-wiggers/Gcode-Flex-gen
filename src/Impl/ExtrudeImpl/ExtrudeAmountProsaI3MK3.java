package Impl.ExtrudeImpl;

import Framework.interfaces.ExtrudeAmount;

public class ExtrudeAmountProsaI3MK3 implements ExtrudeAmount {
    @Override
    public double CalculateExtrude(double mm) {
        return mm/34;
    }
}
