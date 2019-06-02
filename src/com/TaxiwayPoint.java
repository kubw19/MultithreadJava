package com;

import java.awt.*;

public class TaxiwayPoint {
    public Point point;
    public boolean blockRunways;

    public TaxiwayPoint(Point point){
        this.point = point;
    }

    public TaxiwayPoint(Point point, boolean blockRunways){
        this.point = point;
        this.blockRunways = blockRunways;
    }
}
