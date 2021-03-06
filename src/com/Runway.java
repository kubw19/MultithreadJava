package com;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Runway {
    public String number;
    public Point finalApproach;
    public Point runwayExit;
    public Point runwayStart;
    public Point airbornePoint;

    public int maxLandings = -1;
    public Semaphore land;

    public PointExtended landingQueueDecreasePoint;

    public ArrayList<TaxiwayPoint> approachTaxiwayPath = new ArrayList<>();

    public ArrayList<TaxiwayPoint> departureTaxiwayPath = new ArrayList<>();

    public void addApproachTaxiwayPoint(PointExtended point){
        approachTaxiwayPath.add(new TaxiwayPoint(point));
    }

    public void addApproachTaxiwayPoint(PointExtended point, boolean useRunways){
        if(useRunways)
            approachTaxiwayPath.add(new TaxiwayPoint(point, true));
        else
            approachTaxiwayPath.add(new TaxiwayPoint(point));
    }

    public void addDepartureTaxiwayPoint(PointExtended point){
        departureTaxiwayPath.add(new TaxiwayPoint(point));
    }

    public void addDepartureTaxiwayPoint(PointExtended point, boolean useRunways){
        if(useRunways)
            departureTaxiwayPath.add(new TaxiwayPoint(point, true));
        else
            departureTaxiwayPath.add(new TaxiwayPoint(point));
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