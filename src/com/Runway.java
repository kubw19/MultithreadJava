package com;

import java.awt.*;
import java.util.ArrayList;

public class Runway {
    public String number;
    public Point finalApproach;
    public Point runwayExit;
    public Point runwayStart;
    public Point airbornePoint;

    public ArrayList<Point> approachTaxiwayPath = new ArrayList<>();
    public ArrayList<Point> departureTaxiwayPath = new ArrayList<>();


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