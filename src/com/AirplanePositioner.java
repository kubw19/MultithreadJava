package com;

import java.awt.*;
import java.util.Random;

public class AirplanePositioner extends Thread {

    private Airplane airplane;
    private int direction;

    private Random random;

    public AirplanePositioner(Airplane airplane){
        random = new Random();
        this.airplane = airplane;
        if(airplane.state == "approach"){
            direction = (airplane.runway.runwayExit.x - airplane.runway.finalApproach.x);
            if(direction >= 0)direction = 1;
            else direction = -1;
        }

        else if(airplane.state == "departure"){
            direction = (airplane.runway.airbornePoint.x - airplane.runway.runwayStart.x);
            if(direction >= 0)direction = 1;
            else direction = -1;
        }


        }

    public boolean pathEnd(){

        if(airplane.state == "approach"){
            if(airplane.position.distance(airplane.runway.runwayExit) == 0) return true;
        }

        else if(airplane.state == "departure"){
            if(airplane.position.distance(airplane.runway.airbornePoint) == 0) return true;
        }

        return false;
    }

    private void useRunway(){
        airplane.active = true;
       while(!pathEnd()){
            try {sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            airplane.position.x+=direction;
            airplane.position.y = (int)(airplane.runway.m * airplane.position.x + airplane.runway.c);
            airplane.airport.repaint();
        }
        Airport airport = airplane.airport;
        airplane.airport.airplanes.remove(airplane);
        airport.repaint();
    }


    public int max(){
        int maximum = airplane.airport.numerek[0];
        for(int j = 0; j<airplane.airport.numerek.length;j++){
            if(airplane.airport.numerek[j] > maximum)maximum = airplane.airport.numerek[j];
        }
        return  maximum;
    }


    public void run() {

        int[] numerek = airplane.airport.numerek;

        airplane.airport.wybieranie[airplane.id] = true;
        airplane.airport.numerek[airplane.id] = max() + 1;
        airplane.airport.wybieranie[airplane.id] = false;

        for(int i = 0;i<airplane.airport.numerek.length;i++){
            while(airplane.airport.wybieranie[i]){
                try {sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            }
            while(airplane.airport.numerek[i] != 0 && (airplane.airport.numerek[airplane.id] > airplane.airport.numerek[i] || (airplane.airport.numerek[airplane.id] == airplane.airport.numerek[i] && airplane.id > i))){
                try {sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            }
        }

        useRunway();

        airplane.airport.numerek[airplane.id] = 0;

    }
}
