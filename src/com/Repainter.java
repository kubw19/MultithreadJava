package com;

public class Repainter extends Thread {
    Airport airport;
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1);
           } catch (InterruptedException e) {
                e.printStackTrace();
           }
            airport.repaint();
        }
    }
}
