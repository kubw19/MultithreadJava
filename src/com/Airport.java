package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Airport extends JPanel implements KeyListener, MouseListener {

    public int height = 600;
    public int width = 1066;

    public Random random;

    public static int airplaneAmount = 0;

    public volatile boolean wybieranie[];
    public volatile int numerek[];
    public boolean runwayLocked = false;

    private BufferedImage background;

    public ArrayList<Airplane> airplanes = new ArrayList<>();

    private ArrayList<Runway> runways = new ArrayList<>();

    public ArrayList<PointExtended> points = new ArrayList<>();


    public int ileLaduje = 0;
    public int ileStartuje = 0;
    public int ileObsluzonych = 0;
    public int ileDoObsluzenia = 0;

    public Semaphore addLanding = new Semaphore(1);
    public Semaphore addTakeoff = new Semaphore(1);
    public Semaphore changeToManage = new Semaphore(1);



    public Airport(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        addMouseListener(this);

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

        runway.maxLandings = 9;
        runway.land = new Semaphore(9);


        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-142, 305, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-183, 195, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-198, 163, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-209, 171, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-211, 170, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-233, 179, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-306, 210, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-353, 225, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-369, 202, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-376, 189, this));

        runway.addApproachTaxiwayPoint(PointExtended.getPoint(453, -196, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(444, -220, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(437, -235, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(402, -222, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(366, -207, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(302, -180, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(281, -171, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(290, -146, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(299, -131, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(322, -92, this), true);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(315, -84, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(300, -68, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(278, -37, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(261, -30, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(212, -9, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(147, 18, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(110, 34, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(66, 52, this), true);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(58, 55, this));//here
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(5, 78, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-63, 106, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-123, 131, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-182, 157, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-198, 163, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-209, 171, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-189, 216, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-154, 300, this));

        runway.landingQueueDecreasePoint = PointExtended.getPoint(315, -84, this);

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

        runway.maxLandings = 1;
        runway.land = new Semaphore(1);



        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-142, 305, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-183, 195, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-136, 180, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-86, 159, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-19, 128, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-5, 128, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(29, 112, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(40, 80, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(78, 89, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(138, 100, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(201, 117, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(227, 122, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(252, 114, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(245, 103, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(233, 93, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(225, 85, this));

        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-359, -124, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-210, -84, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-213, -58, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-253, 146, this), true);
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-261, 186, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-306, 210, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-287, 256, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-255, 245, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-216, 228, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-189, 216, this));//ważny
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-154, 300, this));
        runway.landingQueueDecreasePoint = PointExtended.getPoint(-287, 256, this);

        runways.add(runway);

        //Runway 33
        runway = new Runway("33");

        runway.setFinalApproach(new Point(900,-383));
        runway.setRunwayExit(new Point(-166, 84));

        runway.setRunwayStart(new Point(465,154));
        runway.setAirbornePoint(new Point(-587, 269));

        runway.setM(-0.4389359f);
        runway.setC(11.47158f);

        runway.maxLandings = 2;
        runway.land = new Semaphore(2);

        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-142, 305, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-183, 195, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-136, 180, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-86, 159, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-19, 128, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-5, 128, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(29, 112, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(40, 80, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(78, 89, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(138, 100, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(201, 117, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(227, 122, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(252, 114, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(278, 106, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(284, 105, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(291, 103, this));
        //runway.addDepartureTaxiwayPoint(PointExtended.getPoint(294, 101, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(301, 71, this));//
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(332, -58, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(337, -67, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(411, -96, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(482, -128, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(462, -166, this));

        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-180, 118, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-195, 151, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-198, 163, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-209, 171, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-189, 216, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-154, 300, this));
        runway.landingQueueDecreasePoint = PointExtended.getPoint(-209, 171, this);


        runways.add(runway);

        //Runway 11
        runway = new Runway("11");
        runway.setFinalApproach(new Point(-576,-126));
        runway.setRunwayExit(new Point(52, 26));

        runway.setRunwayStart(new Point(-412,120));
        runway.setAirbornePoint(new Point(576, 153));

        runway.setM(0.2425532f);
        runway.setC(13.44681f);

        runway.maxLandings = 1;
        runway.land = new Semaphore(1);

        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-142, 305, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-183, 195, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-198, 163, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-209, 171, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-211, 170, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-233, 179, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-242, 170, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-250, 162, this));//runway entry
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-252, 161, this), true);
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-210, -84, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-359, -124, this));
        runway.addDepartureTaxiwayPoint(PointExtended.getPoint(-366, -94, this));

        runway.addApproachTaxiwayPoint(PointExtended.getPoint(49, 42, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(58, 55, this));//here
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(5, 78, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-63, 106, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-123, 131, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-182, 157, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-198, 163, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-209, 171, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-189, 216, this));
        runway.addApproachTaxiwayPoint(PointExtended.getPoint(-154, 300, this));
        runway.landingQueueDecreasePoint = PointExtended.getPoint(58, 55, this);

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
        for(int i = airplaneAmountArr; i< airplaneAmount; i++) {
            airplanes.add(new Airplane(i,this, runways.get(random.nextInt(1000)%4), "departure"));
        }



        Repainter rep = new Repainter();
        rep.airport = this;
        rep.start();
    }

    public void paint(Graphics graphics){
        graphics.drawImage(background, 0,0, width, height,null);

        for(int i = 0;i<airplanes.size();i++){
            airplanes.get(i).draw(graphics);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = new Point(e.getPoint().x - width/2, -1 * (e.getY() - height/2));
        System.out.println(p);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
