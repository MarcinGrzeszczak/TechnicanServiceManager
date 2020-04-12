package mg.frontend.gui;

import javax.swing.*;

public class GUIFasade extends JFrame implements Runnable {

    private Thread guiThread;
    public GUIFasade(){
        this.createThread();
    }

    public void runInNewThread() {
        this.guiThread.start();
    }

    public void stopThread() {
        this.guiThread.interrupt();

        try {
            this.guiThread.join();
            this.createThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createThread() {
        this.guiThread = new Thread(this);
    }

    @Override
    public void run() {
        new Window();
    }
}
