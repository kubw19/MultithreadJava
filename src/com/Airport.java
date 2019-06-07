package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Airport extends JPanel implements KeyListener {

    public int height = 600;
    public int width = 1066;

    public Random random;

    public int airplaneAmount = 0;

    public volatile boolean wybieranie[];
    public volatile int numerek[];

    private BufferedImage background;

    public ArrayList<Airplane> airplanes = new ArrayList<>();

    private ArrayList<Runway> runways = new ArrayList<>();

    public ArrayList<PointExtended> points = new ArrayList<>();

    public String[] states = {"approach", "departure"};

    public int ileLaduje = 0;
    public int ileStartuje = 0;
    public int ileObsluzonych = 0;
    public int ileDoObsluzenia = 0;

    public Semaphore addLanding = new Semaphore(1);
    public Semaphore addTakeoff = new Semaphore(1);
    public Semaphore changeToManage = new Semaphore(1);
    public Semaphore taxiwayInUse = new Semaphore(1);



    public Airport(){


        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        PointExtended.fillTaxiwayPoints(this);

        try {
            background = ImageIO.read(new File("airportFull.png"));
        }
        catch(IOException e){}

        random = new Random();

        //Runway 15
        Runway runway = new Runway("15");

        runway.setFinalApproach(new Point(-900,406));
        runway.setRunwayExit(new Point(463, -191));

        runway.setRunwayStart(new Point(-373,175));
        runway.setAirbornePoint(new Point(595, -249));

        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-142, 305, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-198, 163, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-353, 225, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-365, 193, this).point);

        runway.addApproachTaxiwayPoint(PointExtended.getPoint(445, -240, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(290, -174, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(299, -146, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(322, -92, this).point, true);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(305, -72, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(286, -44, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(130, 21, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-198, 163, this).point, true);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-142, 305, this).point);

        runway.setM(-0.4389359f);
        runway.setC(11.47158f);

        runways.add(runway);

        //Runway 29
        runway = new Runway("29");
        runway.setFinalApproach(new Point(900,231));
        runway.setRunwayExit(new Point(-371, -76));

        runway.setRunwayStart(new Point(299,85));
        runway.setAirbornePoint(new Point(-600, -132));

        runway.setM(0.2425532f);
        runway.setC(13.44681f);

        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-359, -124, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-210, -84, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-213, -58, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-253, 146, this).point, true);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-261, 186, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-198, 163, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-142, 305, this).point);

        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-142, 305, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-183, 195, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-19, 128, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-5, 128, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(29, 112, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(45, 75, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(231, 115, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(296, 97, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(294, 101, this).point);




        runways.add(runway);

        //Runway 33
        runway = new Runway("33");

        runway.setFinalApproach(new Point(900,-383));
        runway.setRunwayExit(new Point(-166, 84));

        runway.setRunwayStart(new Point(465,154));
        runway.setAirbornePoint(new Point(-587, 269));

        runway.setM(-0.4389359f);
        runway.setC(11.47158f);

        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-142, 305, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-183, 195, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-19, 128, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-5, 128, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(29, 112, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(45, 75, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(231, 115, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(296, 97, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(332, -32, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(337, -67, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(482, -128, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(468, -170, this).point);

        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-198, 163, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-142, 305, this).point);


        runways.add(runway);

        //Runway 11
        runway = new Runway("11");
        runway.setFinalApproach(new Point(-576,-126));
        runway.setRunwayExit(new Point(52, 26));

        runway.setRunwayStart(new Point(-412,120));
        runway.setAirbornePoint(new Point(576, 153));

        runway.setM(0.2425532f);
        runway.setC(13.44681f);

        runway.addApproachTaxiwayPoint(PointExtended.getPoint(64, 50, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(59, 50, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-198, 163, this).point);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-142, 305, this).point);

        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-142, 305, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-198, 163, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-227, 171, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-239, 159, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-253, 149, this).point, true);
        //runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-253, 146));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-210, -84, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-359, -124, this).point);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-366, -94, this).point);

        runways.add(runway);



    }

    public void addAirplanes(int airplaneAmountDep, int airplaneAmountArr){
        airplanes = new ArrayList<>();
        this.airplaneAmount = airplaneAmountArr + airplaneAmountDep;
        wybieranie = new boolean[airplaneAmount];
        numerek = new int[airplaneAmount];
        ileDoObsluzenia = airplaneAmount;

        addLanding = new Semaphore(1);
        addTakeoff = new Semaphore(1);
        changeToManage = new Semaphore(1);


        for(int i = 0; i< airplaneAmountArr; i++) {
            airplanes.add(new Airplane(i,this, runways.get(random.nextInt(1000)%4), "approach"));
        }
        for(int i = 0; i< airplaneAmountDep; i++) {
            airplanes.add(new Airplane(i,this, runways.get(random.nextInt(1000)%4), "departure"));
        }




        repaint();
    }

    public void paint(Graphics graphics){
        graphics.drawImage(background, 0,0, width, height,null);

        for(Airplane airplane : airplanes){
            airplane.draw(graphics);
        }

        graphics.drawString("Do obslugi: " + ileDoObsluzenia, 0,10);
        graphics.drawString("Obsluzonych: " + ileObsluzonych, 0,30);
        graphics.drawString("Obslugiwanych: " + (ileLaduje + ileStartuje), 0,50);
        graphics.drawString("Lądujących: " + ileLaduje, 0,90);
        graphics.drawString("Startujacych: " + ileStartuje, 0,110);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            airplanes.get(0).position.x++;
        };
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            airplanes.get(0).position.x--;
        };
        if(e.getKeyCode() == KeyEvent.VK_UP){
            airplanes.get(0).position.y++;
        };
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            airplanes.get(0).position.y--;
        }

        System.out.println(airplanes.get(0).position);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
