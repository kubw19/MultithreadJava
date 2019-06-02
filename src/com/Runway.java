package com;

import java.awt.*;
import java.util.ArrayList;

public class Runway {
    public String number;
    public Point finalApproach;
    public Point runwayExit;
    public Point runwayStart;
    public Point airbornePoint;

    public ArrayList<TaxiwayPoint> approachTaxiwayPath = new ArrayList<>();

    public ArrayList<TaxiwayPoint> departureTaxiwayPath = new ArrayList<>();

    public void addApproachTaxiwayPoint(Point point){
        approachTaxiwayPath.add(new TaxiwayPoint(point));
    }

    public void addApproachTaxiwayPoint(Point point, boolean useRunways){
        if(useRunways)
            approachTaxiwayPath.add(new TaxiwayPoint(point, true));
        else
            approachTaxiwayPath.add(new TaxiwayPoint(point));
    }

    public void addApproachTaxiwayPoint(Point point, boolean useRunways, boolean blockTaxiway){
            approachTaxiwayPath.add(new TaxiwayPoint(point, false, blockTaxiway));
    }

    public void addDepartureTaxiwayPoint(Point point){
        departureTaxiwayPath.add(new TaxiwayPoint(point));
    }

    public void addDepartureTaxiwayPoint(Point point, boolean useRunways){
        if(useRunways)
            departureTaxiwayPath.add(new TaxiwayPoint(point, true));
        else
            departureTaxiwayPath.add(new TaxiwayPoint(point));
    }
    public void addDepartureTaxiwayPoint(Point point, boolean useRunways, boolean blockTaxiway){
        departureTaxiwayPath.add(new TaxiwayPoint(point, false, blockTaxiway));
    }

    public float m;
    public float c;


    public Runway(String number) {
        this.number = number;
    }

    public void setFinalApproach(Point finalApproach) {
        this.finalApproach = finalApproach;
    }

    public void setRunwayExit(Point runwayExit) {
        this.runwayExit = runwayExit;
    }

    public void setRunwayStart(Point runwayStart) {
        this.runwayStart = runwayStart;
    }

    public void setAirbornePoint(Point airbornePoint) {
        this.airbornePoint = airbornePoint;
    }

    public void setM(float m) {
        this.m = m;
    }

    public void setC(float c) {
        this.c = c;
    }
}