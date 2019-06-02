package com;

import java.awt.*;
import java.util.Random;

public class AirplanePositioner extends Thread {

    private Airplane airplane;
    private int direction;

    private Random random;

    private void moveTo(Point to){
        Point from = new Point(airplane.position);

        int distance = (int)to.distance(from);
        distance /= 20;

        while(!airplane.position.equals(to)) {
            try {sleep(100 / ((long)distance + 1));} catch (InterruptedException e) {e.printStackTrace();}
            Positioner.moveTo(airplane.position, from, to);
            airplane.airport.repaint();
        }
    }

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

    void lockRunways(){
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
    }

    void unlockRunways(){
        airplane.airport.numerek[airplane.id] = 0;
    }

    private void useRunway(){

        lockRunways();

       while(!pathEnd()){
            try {sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            airplane.position.x+=direction;
            airplane.position.y = (int)(airplane.runway.m * airplane.position.x + airplane.runway.c);
            airplane.airport.repaint();
        }

        unlockRunways();

    }


    public int max(){
        int maximum = airplane.airport.numerek[0];
        for(int j = 0; j<airplane.airport.numerek.length;j++){
            if(airplane.airport.numerek[j] > maximum)maximum = airplane.airport.numerek[j];
        }
        return  maximum;
    }


    public void run() {

        try {
            sleep(random.nextInt(60000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {airplane.airport.changeToManage.acquire();} catch (InterruptedException e) {e.printStackTrace();}
        airplane.airport.ileDoObsluzenia--;
        airplane.airport.changeToManage.release();

        if(airplane.state == "departure") {
            try {airplane.airport.addTakeoff.acquire();} catch (InterruptedException e) {e.printStackTrace();}
            airplane.airport.ileStartuje++;
            airplane.airport.addTakeoff.release();
        }

        else{
            try {airplane.airport.addLanding.acquire();} catch (InterruptedException e) {e.printStackTrace();}
            airplane.airport.ileLaduje++;
            airplane.airport.addLanding.release();
        }


        airplane.active = true;

        if(airplane.state == "departure")
            for(TaxiwayPoint next : airplane.runway.departureTaxiwayPath.subList(1, airplane.runway.departureTaxiwayPath.size())){
                if(next.blockRunways) {
                    lockRunways();
                    moveTo(new Point(next.point));
                    unlockRunways();
                }
                else moveTo(new Point(next.point));
            }



        useRunway();


        if(airplane.state == "approach")
            for(TaxiwayPoint next : airplane.runway.approachTaxiwayPath){
                if(next.blockRunways) {
                    lockRunways();
                    moveTo(new Point(next.point));
                    unlockRunways();
                }
                else moveTo(new Point(next.point));
            }

        Airport airport = airplane.airport;
        airplane.airport.airplanes.remove(airplane);
        if(airplane.state == "departure")airport.ileStartuje--;
        else airport.ileLaduje--;
        try {airplane.airport.changeToManage.acquire();} catch (InterruptedException e) {e.printStackTrace();}
        airplane.airport.ileObsluzonych++;
        airplane.airport.changeToManage.release();
        airport.repaint();

    }
}
