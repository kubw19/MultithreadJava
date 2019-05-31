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

    public Airport(){
        wybieranie = new boolean[airplaneAmount];
        numerek = new int[airplaneAmount];
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        try {
            background = ImageIO.read(new File("airport.png"));
        }
        catch(IOException e){}

        random = new Random();

        //Runway 15
        Runway runway = new Runway("15");

        runway.setFinalApproach(new Point(-900,406));
        runway.setRunwayExit(new Point(397, -162));

        runway.setRunwayStart(new Point(-323,154));
        runway.setAirbornePoint(new Point(595, -249));

        runway.setM(-0.4389359f);
        runway.setC(11.47158f);

        runways.add(runway);

        //Runway 29
        runway = new Runway("29");
        runway.setFinalApproach(new Point(900,231));
        runway.setRunwayExit(new Point(-353, -72));

        runway.setRunwayStart(new Point(150,37));
        runway.setAirbornePoint(new Point(-600, -132));

        runway.setM(0.2425532f);
        runway.setC(13.44681f);

        runways.add(runway);

        //Runway 33
        runway = new Runway("33");

        runway.setFinalApproach(new Point(900,-383));
        runway.setRunwayExit(new Point(-307, 146));

        runway.setRunwayStart(new Point(465,154));
        runway.setAirbornePoint(new Point(-587, 269));

        runway.setM(-0.4389359f);
        runway.setC(11.47158f);

        runways.add(runway);

         //Runway 11
        runway = new Runway("11");
        runway.setFinalApproach(new Point(-576,-126));
        runway.setRunwayExit(new Point(220, 66));

        runway.setRunwayStart(new Point(-412,120));
        runway.setAirbornePoint(new Point(576, 153));

        runway.setM(0.2425532f);
        runway.setC(13.44681f);

        runways.add(runway);


        for(int i = 0; i< airplaneAmount; i++) {
            airplanes.add(new Airplane(i,this, runways.get(random.nextInt(100)%4), states[random.nextInt(100)%2]));
        }

    }

    public void paint(Graphics graphics){
        graphics.drawImage(background, 0,0, width, height,null);

        for(Airplane airplane : airplanes){
            airplane.draw(graphics);
        }
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

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
