package com;

import javax.swing.*;
import java.awt.*;

public class Window {

    public static int width = 1066;
    public static int height = 600;
    //public static int scale = 4;

    public JFrame frame;

    public Window(Airport airport){
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Lotnisko Chopina EPWA");
        frame.add(airport);
        frame.pack();
        frame.setBounds(0, 0, airport.width, airport.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){

        Airport airport = new Airport();
        Window window = new Window(airport);


    }


}