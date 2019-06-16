package com;

public class TaxiwayPoint {
    public PointExtended point;
    public boolean blockRunways;
    public boolean runwayEntry;

    public TaxiwayPoint(PointExtended point){
        this.point = point;
        this.blockRunways = false;
        runwayEntry = false;
    }

    public TaxiwayPoint(PointExtended point, boolean blockRunways){
        this.point = point;
        this.blockRunways = blockRunways;
        runwayEntry = false;
    }
}
