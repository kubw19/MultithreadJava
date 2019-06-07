package com;

import java.awt.*;
import java.util.ArrayList;

public class TaxiwayPoint {
    public Point point;
    public boolean blockRunways;
    public boolean blockTaxiway;
    public ArrayList<Airplane> using = new ArrayList<>();

    public TaxiwayPoint(Point point){
        this.point = point;
        this.blockRunways = false;
    }

    public TaxiwayPoint(Point point, boolean blockRunways){
        this.point = point;
        this.blockRunways = blockRunways;
    }

    public TaxiwayPoint(Point point, boolean blockRunways, boolean blockTaxiway){
        this.point = point;
        this.blockRunways = false;
        this.blockTaxiway = true;
    }




}
