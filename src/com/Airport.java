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

    public int airplaneAmount = 50;

    public volatile boolean wybieranie[];
    public volatile int numerek[];

    private BufferedImage background;

    public ArrayList<Airplane> airplanes = new ArrayList<>();

    private ArrayList<Runway> runways = new ArrayList<>();

    public String[] states = {"departure", "approach"};

    public int ileLaduje = 0;
    public int ileStartuje = 0;

    public Semaphore addLanding = new Semaphore(1);
    public Semaphore addTakeoff = new Semaphore(1);

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

        runway.departureTaxiwayPath.add(new Point(-142, 305));
        runway.departureTaxiwayPath.add(new Point(-198, 163));
        runway.departureTaxiwayPath.add(new Point(-353, 225));
        runway.departureTaxiwayPath.add(new Point(-373, 175));

        runway.approachTaxiwayPath.add(new Point(482, -128));
        runway.approachTaxiwayPath.add(new Point(-198, 163));
        runway.approachTaxiwayPath.add(new Point(-142, 305));

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

        runway.approachTaxiwayPath.add(new Point(-359, -124));
        runway.approachTaxiwayPath.add(new Point(-210, -84));
        runway.approachTaxiwayPath.add(new Point(-261, 186));
        runway.approachTaxiwayPath.add(new Point(-198, 163));
        runway.approachTaxiwayPath.add(new Point(-142, 305));

        runway.departureTaxiwayPath.add(new Point(-142, 305));
        runway.departureTaxiwayPath.add(new Point(-183, 195));
        runway.departureTaxiwayPath.add(new Point(-19, 128));
        runway.departureTaxiwayPath.add(new Point(-5, 128));
        runway.departureTaxiwayPath.add(new Point(29, 112));
        runway.departureTaxiwayPath.add(new Point(45, 75));
        runway.departureTaxiwayPath.add(new Point(231, 115));
        runway.departureTaxiwayPath.add(new Point(296, 97));
        runway.departureTaxiwayPath.add(new Point(299, 85));




        runways.add(runway);

        //Runway 33
        runway = new Runway("33");

        runway.setFinalApproach(new Point(900,-383));
        runway.setRunwayExit(new Point(-166, 84));

        runway.setRunwayStart(new Point(465,154));
        runway.setAirbornePoint(new Point(-587, 269));

        runway.setM(-0.4389359f);
        runway.setC(11.47158f);

        runway.departureTaxiwayPath.add(new Point(-142, 305));
        runway.departureTaxiwayPath.add(new Point(-198, 163));
        runway.departureTaxiwayPath.add(new Point(482, -128));
        runway.departureTaxiwayPath.add(new Point(463, -191));

        runway.approachTaxiwayPath.add(new Point(-198, 163));
        runway.approachTaxiwayPath.add(new Point(-142, 305));


        runways.add(runway);

         //Runway 11
        runway = new Runway("11");
        runway.setFinalApproach(new Point(-576,-126));
        runway.setRunwayExit(new Point(299, 85));

        runway.setRunwayStart(new Point(-412,120));
        runway.setAirbornePoint(new Point(576, 153));

        runway.setM(0.2425532f);
        runway.setC(13.44681f);

        runway.approachTaxiwayPath.add(new Point(296, 97));
        runway.approachTaxiwayPath.add(new Point(231, 115));
        runway.approachTaxiwayPath.add(new Point(45, 75));
        runway.approachTaxiwayPath.add(new Point(29, 112));
        runway.approachTaxiwayPath.add(new Point(-5, 125));
        runway.approachTaxiwayPath.add(new Point(-19, 128));
        runway.approachTaxiwayPath.add(new Point(-183, 195));
        runway.approachTaxiwayPath.add(new Point(-142, 305));

        runway.departureTaxiwayPath.add(new Point(-142, 305));
        runway.departureTaxiwayPath.add(new Point(-198, 163));
        runway.departureTaxiwayPath.add(new Point(-261, 186));
        runway.departureTaxiwayPath.add(new Point(-210, -84));
        runway.departureTaxiwayPath.add(new Point(-359, -124));
        runway.departureTaxiwayPath.add(new Point(-371, -76));

        runways.add(runway);


        for(int i = 0; i< airplaneAmount; i++) {
            String state = states[random.nextInt(100)%2];
            airplanes.add(new Airplane(i,this, runways.get(random.nextInt(100)%4), state));

        }

    }

    public void paint(Graphics graphics){
        graphics.drawImage(background, 0,0, width, height,null);

        for(Airplane airplane : airplanes){
            airplane.draw(graphics);
        }

        graphics.drawString("Lądujących: " + ileLaduje, 0,50);
        graphics.drawString("Startujacych: " + ileStartuje, 0,100);
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
