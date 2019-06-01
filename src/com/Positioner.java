package com;

import java.awt.*;

public class Positioner {
    public static void moveTo(Point position, Point from, Point to){
        double a = to.y - from.y;
        double b = from.x - to.x;
        double c = a*from.x + b* from.y;

        if(b == 0){
            if(a < 0){
                from.y--;
            }
            else{
                from.y++;
            }

            return;
        }

        int nextX;
        if(b < 0){
            nextX = position.x + 1;
        }
        else{
            nextX = position.x - 1;
        }


        int nextY = (int)((a*nextX - c) / (b * -1));

        //System.out.println(new Point(nextX, nextY));
        position.x = nextX;
        position.y = nextY;
        //a = a /(b*-1);
        //c = c / (b*-1);
        //System.out.println("y = "+a+"x + " + c + "    " + position);
       // System.exit(0);

    }
}
