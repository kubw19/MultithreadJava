package com;

import java.awt.*;

public class Airplane {

    public Point position = new Point();

    public Color color;

    public Airport airport;
    public AirplanePositioner airplanePositioner;
    public Runway runway;
    private int height = 10;
    private int width = 10;
    public String state;

    public boolean active;

    public int id;

    public Airplane(int id, Airport airport, Runway runway, String state){
        int los = airport.random.nextInt(1000) % 5;
        this.id = id;
        active = false;
        this.runway = runway;
        this.state  = state;
        if(state == "approach"){
            this.position = new Point(runway.finalApproach);
            color = Color.RED;
        }
        else if(state == "departure"){
            this.position = new Point(runway.departureTaxiwayPath.get(0).point.point);
            color = Color.GREEN;
        }
        this.airport = airport;
        this.airplanePositioner = new AirplanePositioner(this);
        this.airplanePositioner.start();
    }

    public void draw(Graphics graphics){
        if(!active)return;
        graphics.setColor(this.color);
        graphics.fillOval(position.x + airport.width / 2 - width / 2, -1 * position.y + airport.height / 2 - height / 2, width, height);
        graphics.setColor(Color.BLACK);
    }


}
