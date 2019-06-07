package com;

import java.awt.*;

public class PointExtended {
    public Point point;
    public PointExtended(int x, int y){
        point = new Point(x,y);
    }

    public static PointExtended getPoint(int x, int y, Airport airport){
        for(PointExtended point : airport.points){
            if(point.point.equals(new Point(x,y)))return point;
        }

        return null;
    }

    public static void fillTaxiwayPoints(Airport airport){
        airport.points.add(new PointExtended(-353, 225));
        airport.points.add(new PointExtended(-365, 193));
        airport.points.add(new PointExtended(445, -240));
        airport.points.add(new PointExtended(290, -174));
        airport.points.add(new PointExtended(299, -146));
        airport.points.add(new PointExtended(322, -92));
        airport.points.add(new PointExtended(305, -72));
        airport.points.add(new PointExtended(286, -44));
        airport.points.add(new PointExtended(130, 21));
        airport.points.add(new PointExtended(-213, -58));
        airport.points.add(new PointExtended(-253, 146));
        airport.points.add(new PointExtended(-261, 186));
        airport.points.add(new PointExtended(294, 101));
        airport.points.add(new PointExtended(-183, 195));
        airport.points.add(new PointExtended(-19, 128));
        airport.points.add(new PointExtended(-5, 128));
        airport.points.add(new PointExtended(29, 112));
        airport.points.add(new PointExtended(45, 75));
        airport.points.add(new PointExtended(231, 115));
        airport.points.add(new PointExtended(296, 97));
        airport.points.add(new PointExtended(332, -32));
        airport.points.add(new PointExtended(337, -67));
        airport.points.add(new PointExtended(482, -128));
        airport.points.add(new PointExtended(468, -170));
        airport.points.add(new PointExtended(64, 50));
        airport.points.add(new PointExtended(59, 50));
        airport.points.add(new PointExtended(-142, 305));
        airport.points.add(new PointExtended(-198, 163));
        airport.points.add(new PointExtended(-227, 171));
        airport.points.add(new PointExtended(-239, 159));
        airport.points.add(new PointExtended(-253, 149));
        airport.points.add(new PointExtended(-210, -84));
        airport.points.add(new PointExtended(-359, -124));
        airport.points.add(new PointExtended(-366, -94));
    }

}
