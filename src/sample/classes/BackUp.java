package sample.classes;


import sample.сontrollers.Controller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class BackUp
 */
public class BackUp extends TimerTask {

    private Timer timer = new Timer();

    @Override
    public void run() {
        timer.schedule(new BackUp(), 300000);
        Controller.backUp();

    }
}

