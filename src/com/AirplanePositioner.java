package com;

import java.awt.*;
import java.util.Random;

public class AirplanePositioner extends Thread {

    private Airplane airplane;
    private int direction;

    private PointExtended runwayEntry = null;

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

    private void moveTo(Point to){
        Point from = new Point(airplane.position);

        int distance = (int)to.distance(from);
        distance /= 20;

        while(!airplane.position.equals(to)) {
            try {sleep(100 / ((long)distance + 1));} catch (InterruptedException e) {e.printStackTrace();}
            Positioner.moveTo(airplane.position, from, to);
           // airplane.airport.repaint();
        }
    }

    public int max(){
        int maximum = airplane.airport.numerek[0];
        for(int j = 0; j<airplane.airport.numerek.length;j++){
            if(airplane.airport.numerek[j] > maximum)maximum = airplane.airport.numerek[j];
        }
        return  maximum;
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
        airplane.airport.runwayLocked = true;
        System.out.println("używa: "+airplane.state + airplane.runway.number + " " + airplane.id);
    }

    void unlockRunways(){
        airplane.airport.runwayLocked = false;
        airplane.airport.numerek[airplane.id] = 0;
    }


    private void useRunway(){

        if(airplane.runway.number == "11" && airplane.state == "departure"){}
        else lockRunways();


        if(runwayEntry!=null)runwayEntry.unlock(airplane);

        System.out.println("lece" + airplane.state + airplane.runway.number + " " + airplane.id);

       while(!pathEnd()){
            try {sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            airplane.position.x+=direction;
            airplane.position.y = (int)(airplane.runway.m * airplane.position.x + airplane.runway.c);
            //airplane.airport.repaint();
        }



        if(airplane.state == "approach" && airplane.runway.number == "29"){}
        else unlockRunways();
        System.out.println("polecialem " + airplane.state + airplane.runway.number + " " + airplane.id);

    }





    public void run() {

        try {sleep(random.nextInt(60000));} catch (InterruptedException e) {e.printStackTrace(); }//opóźnienie pojawienia

        try {airplane.airport.changeToManage.acquire();} catch (InterruptedException e) {e.printStackTrace();}
        airplane.airport.ileDoObsluzenia--;
        airplane.airport.changeToManage.release();



        //aktualizacja napisów
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


        airplane.active = true;//Rysowanie samolotu

        //kołowanie do startu
        if(airplane.state == "departure") {
            PointExtended prev = null;
            for (TaxiwayPoint next : airplane.runway.departureTaxiwayPath.subList(1, airplane.runway.departureTaxiwayPath.size())) {
                if(next.point.runwayEntry)System.out.println(airplane.id + "czeka na "+next.point.point.toString());
                next.point.lock(airplane);

                if(prev!=null && !prev.runwayEntry)prev.unlock(airplane);
                else if(prev !=null && prev.runwayEntry){
                    runwayEntry = prev;
                }

                  if (next.blockRunways) {
                    lockRunways();
                    moveTo(new Point(next.point.point));
                    if(airplane.runway.number != "11")unlockRunways();
                } else moveTo(new Point(next.point.point));

                prev = next.point;
            }
            prev.unlock(airplane);
            //try {sleep(random.nextInt(10000));} catch (InterruptedException e) {e.printStackTrace(); }//opóźnienie wjazdu na pas
        }


        if(airplane.state == "approach" && airplane.runway.maxLandings!=-1){
            try {
                airplane.runway.land.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        useRunway();


        if(airplane.state == "approach") {
            PointExtended prev = null;
            for (TaxiwayPoint next : airplane.runway.approachTaxiwayPath) {
                next.point.lock(airplane);
                if(prev!=null)prev.unlock(airplane);
                if (next.blockRunways) {
                    if(airplane.runway.number != "29")lockRunways();
                    moveTo(new Point(next.point.point));
                    unlockRunways();
                } else moveTo(new Point(next.point.point));

                if(airplane.runway.maxLandings != -1 && airplane.runway.landingQueueDecreasePoint.point.equals(next.point.point)){
                    airplane.runway.land.release();
                    System.out.println("Opuscilem" + airplane.runway.number);
                }

                prev = next.point;
            }
            prev.unlock(airplane);
        }

        Airport airport = airplane.airport;
        try {airport.changeToManage.acquire();} catch (InterruptedException e) {e.printStackTrace();}
        airport.airplanes.remove(airplane);
        if(airplane.state == "departure")airport.ileStartuje--;
        else airport.ileLaduje--;
        airport.ileObsluzonych++;
        airport.changeToManage.release();


    }
}
