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
        position.x = nextX;
        position.y = nextY;

    }
}
