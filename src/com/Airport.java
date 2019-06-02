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

    public final int airplaneAmount = 100;

    public volatile boolean wybieranie[];
    public volatile int numerek[];

    private BufferedImage background;

    public ArrayList<Airplane> airplanes = new ArrayList<>();

    private ArrayList<Runway> runways = new ArrayList<>();

    public String[] states = {"approach", "departure"};

    public int ileLaduje = 0;
    public int ileStartuje = 0;
    public int ileObsluzonych = 0;
    public int ileDoObsluzenia = airplaneAmount;

    public Semaphore addLanding = new Semaphore(1);
    public Semaphore addTakeoff = new Semaphore(1);
    public Semaphore changeToManage = new Semaphore(1);
    public Semaphore taxiwayInUse = new Semaphore(1);



    public Airport(){
        wybieranie = new boolean[airplaneAmount];
        numerek = new int[airplaneAmount];


        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

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

        runway.addDepartureTaxiwayPoint(new Point(-142, 305));
        runway.addDepartureTaxiwayPoint(new Point(-198, 163));
        runway.addDepartureTaxiwayPoint(new Point(-353, 225));
        runway.addDepartureTaxiwayPoint(new Point(-365, 193));

        runway.addApproachTaxiwayPoint(new Point(445, -240));
        runway.addApproachTaxiwayPoint(new Point(290, -174));
        runway.addApproachTaxiwayPoint(new Point(299, -146));
        runway.addApproachTaxiwayPoint(new Point(322, -92), true);
        runway.addApproachTaxiwayPoint(new Point(305, -72));
        runway.addApproachTaxiwayPoint(new Point(286, -44));
        runway.addApproachTaxiwayPoint(new Point(130, 21));
        runway.addApproachTaxiwayPoint(new Point(-198, 163), true);
        runway.addApproachTaxiwayPoint(new Point(-142, 305));

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

        runway.addApproachTaxiwayPoint(new Point(-359, -124));
        runway.addApproachTaxiwayPoint(new Point(-210, -84));
        runway.addApproachTaxiwayPoint(new Point(-213, -58));
        runway.addApproachTaxiwayPoint(new Point(-253, 146), true);
        runway.addApproachTaxiwayPoint(new Point(-261, 186));
        runway.addApproachTaxiwayPoint(new Point(-198, 163));
        runway.addApproachTaxiwayPoint(new Point(-142, 305));

        runway.addDepartureTaxiwayPoint(new Point(-142, 305));
        runway.addDepartureTaxiwayPoint(new Point(-183, 195));
        runway.addDepartureTaxiwayPoint(new Point(-19, 128));
        runway.addDepartureTaxiwayPoint(new Point(-5, 128));
        runway.addDepartureTaxiwayPoint(new Point(29, 112));
        runway.addDepartureTaxiwayPoint(new Point(45, 75));
        runway.addDepartureTaxiwayPoint(new Point(231, 115));
        runway.addDepartureTaxiwayPoint(new Point(296, 97));
        runway.addDepartureTaxiwayPoint(new Point(294, 101));




        runways.add(runway);

        //Runway 33
        runway = new Runway("33");

        runway.setFinalApproach(new Point(900,-383));
        runway.setRunwayExit(new Point(-166, 84));

        runway.setRunwayStart(new Point(465,154));
        runway.setAirbornePoint(new Point(-587, 269));

        runway.setM(-0.4389359f);
        runway.setC(11.47158f);

        runway.addDepartureTaxiwayPoint(new Point(-142, 305));
        runway.addDepartureTaxiwayPoint(new Point(-183, 195));
        runway.addDepartureTaxiwayPoint(new Point(-19, 128));
        runway.addDepartureTaxiwayPoint(new Point(-5, 128));
        runway.addDepartureTaxiwayPoint(new Point(29, 112));
        runway.addDepartureTaxiwayPoint(new Point(45, 75));
        runway.addDepartureTaxiwayPoint(new Point(231, 115));
        runway.addDepartureTaxiwayPoint(new Point(296, 97));
        runway.addDepartureTaxiwayPoint(new Point(332, -32));
        runway.addDepartureTaxiwayPoint(new Point(337, -67));
        runway.addDepartureTaxiwayPoint(new Point(482, -128));
        runway.addDepartureTaxiwayPoint(new Point(468, -170));

        runway.addApproachTaxiwayPoint(new Point(-198, 163));
        runway.addApproachTaxiwayPoint(new Point(-142, 305));


        runways.add(runway);

        //Runway 11
        runway = new Runway("11");
        runway.setFinalApproach(new Point(-576,-126));
        runway.setRunwayExit(new Point(52, 26));

        runway.setRunwayStart(new Point(-412,120));
        runway.setAirbornePoint(new Point(576, 153));

        runway.setM(0.2425532f);
        runway.setC(13.44681f);

        runway.addApproachTaxiwayPoint(new Point(64, 50));
        runway.addApproachTaxiwayPoint(new Point(59, 50));
        runway.addApproachTaxiwayPoint(new Point(-198, 163));
        runway.addApproachTaxiwayPoint(new Point(-142, 305));

        runway.addDepartureTaxiwayPoint(new Point(-142, 305));
        runway.addDepartureTaxiwayPoint(new Point(-198, 163));
        runway.addDepartureTaxiwayPoint(new Point(-227, 171));
        runway.addDepartureTaxiwayPoint(new Point(-239, 159));
        runway.addDepartureTaxiwayPoint(new Point(-253, 149), true);
        //runway.addDepartureTaxiwayPoint(new Point(-253, 146));
        runway.addDepartureTaxiwayPoint(new Point(-210, -84));
        runway.addDepartureTaxiwayPoint(new Point(-359, -124));
        runway.addDepartureTaxiwayPoint(new Point(-366, -94));

        runways.add(runway);


        for(int i = 0; i< airplaneAmount; i++) {
            String state = states[random.nextInt(100)%2];
            airplanes.add(new Airplane(i,this, runways.get(random.nextInt(1000)%4), state));

        }

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
