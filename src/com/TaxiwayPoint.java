package com;

import java.awt.*;
import java.util.ArrayList;

public class TaxiwayPoint {
    public PointExtended point;
    public boolean blockRunways;
    public boolean blockTaxiway;

    public boolean runwayEntry;
    public ArrayList<Airplane> using = new ArrayList<>();

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

    public TaxiwayPoint(PointExtended point, boolean blockRunways, boolean blockTaxiway){
        this.point = point;
        this.blockRunways = false;
        this.blockTaxiway = true;
        runwayEntry = false;
    }




}
