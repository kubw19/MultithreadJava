package com;

import java.awt.*;

public class PointExtended {

    public volatile boolean wybieranie[] = new boolean[5000];
    public volatile int numerek[] = new int[5000];

    public Point point;
    public boolean runwayEntry;
    public PointExtended(int x, int y, boolean runwayEntry){
        this.runwayEntry = runwayEntry;
        point = new Point(x,y);
    }

    public PointExtended(int x, int y){
        this.runwayEntry = false;
        point = new Point(x,y);
    }


    private int max(){
        int maximum = numerek[0];
        for(int j = 0; j<numerek.length;j++){
            if(numerek[j] > maximum)maximum = numerek[j];
        }
        return  maximum;
    }

    public void lock(Airplane airplane){
        wybieranie[airplane.id] = true;
        numerek[airplane.id] = max() + 1;
        wybieranie[airplane.id] = false;

        for(int i = 0;i<numerek.length;i++){
            while(wybieranie[i]){
                try {airplane.airplanePositioner.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            }
            while(numerek[i] != 0 && (numerek[airplane.id] > numerek[i] || (numerek[airplane.id] == numerek[i] && airplane.id > i))){
                try {airplane.airplanePositioner.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    }

    public void unlock(Airplane airplane){
        numerek[airplane.id] = 0;
    }

    public static PointExtended getPoint(int x, int y, Airport airport){
        for(PointExtended point : airport.points){
            if(point.point.equals(new Point(x,y))){
                return point;
            }
        }

        PointExtended point = new PointExtended(x, y);
        airport.points.add(point);
        return point;
    }



    public static void fillTaxiwayPoints(Airport airport){
        airport.points.add(new PointExtended(-365, 193, true));
        airport.points.add(new PointExtended(291, 103, true));
        airport.points.add(new PointExtended(468, -170, true));
        airport.points.add(new PointExtended(-239, 159, true));
        airport.points.add(new PointExtended(-366, -94, true));
        airport.points.add(new PointExtended(299, -131, true));
    }

}
