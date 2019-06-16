package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements ActionListener {

    public static int width = 1066;
    public static int height = 600;
    //public static int scale = 4;

    public JFrame frame;

    public Window(Airport airport){
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Lotnisko Chopina EPWA");

        frame.setLayout(null);

        JLabel depText = new JLabel("Do startu: ");
        depText.setBounds(0,0,60, 30);
        frame.add(depText);

        JTextField textField = new JTextField(20);
        textField.setBounds(60, 0, 100, 30);
        frame.add(textField);

        JLabel arrText = new JLabel("Do lądowania: ");
        arrText.setBounds(160,0,90, 30);
        frame.add(arrText);

        JTextField textField2 = new JTextField(20);
        textField2.setBounds(250, 0, 100, 30);
        frame.add(textField2);

        JButton button = new JButton("Dodaj");
        button.setBounds(370,0,100,30);
        frame.add(button);

        frame.add(airport);
        airport.setBounds(0,30,airport.width, airport.height);
        frame.pack();
        frame.setBounds(0, 0, airport.width, airport.height + 30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                airport.addAirplanes(Integer.valueOf(textField.getText()), Integer.valueOf(textField2.getText()));
                button.setEnabled(false);
                textField.setEnabled(false);
                textField2.setEnabled(false);
            }
        } );


    }

    public static void main(String[] args){
        Airport airport = new Airport();
        Window window = new Window(airport);
    }


    @Override
    public void actionPerformed(ActionEvent e) { }
}