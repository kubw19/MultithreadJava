package com;

import java.awt.*;
import java.lang.management.RuntimeMXBean;

public class Airplane {

    public Point position = new Point();

    public Airport airport;
    public AirplanePositioner airplanePositioner;
    public Runway runway;
    private int height = 30;
    private int width = 30;
    public String state;

    public boolean active;

    public int id;

    public Airplane(int id, Airport airport, Runway runway, String state){
        this.id = id;
        active = false;
        this.runway = runway;
        this.state  = state;
        if(state == "approach"){
            this.position = new Point(runway.finalApproach);
        }
        else if(state == "departure"){
            this.position = new Point(runway.departureTaxiwayPath.get(0));
        }
        this.airport = airport;
        this.airplanePositioner = new AirplanePositioner(this);
        this.airplanePositioner.start();
    }

    public void draw(Graphics graphics){
        if(!active)return;
        graphics.setColor(Color.BLACK);
        graphics.fillRect(position.x + airport.width / 2 - width / 2, -1 * position.y + airport.height / 2 - height / 2, 20, 20);

        System.out.println(id + position.toString());

    }


}
