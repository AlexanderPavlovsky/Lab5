package sample.classes;


import sample.Controller;

/**
 * Class BackUp
 */
public class BackUp extends Thread {

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                Controller.BackUp();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
