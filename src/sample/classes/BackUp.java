package sample.classes;


import sample.сontrollers.Controller;

/**
 * Class BackUp
 */
public class BackUp extends Thread {

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                Controller.backUp();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
