package mg.frontend.gui.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class DeleteAnimation extends JPanel implements AnimationDrawer {
    private int step;
    private int delay;
    private int delayToClose;
    int xStart;
    int yStart;
    int xEnd;
    int yEnd;
    Path2D oneLine;
    Path2D secondLine;

    private IAnimationWIndowListener listener;

    public DeleteAnimation(int xStart, int yStart, int xEnd, int yEnd ,int step, int delay, int delayToClose, IAnimationWIndowListener listener){
        this.step = step;
        this.delay = delay;
        this.delayToClose = delayToClose;
        this.listener = listener;
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;

    }

    @Override
    public void animate() {
        oneLine = new Path2D.Double();
        secondLine = new Path2D.Double();
        oneLine.moveTo(xStart, xStart);
        secondLine.moveTo(xEnd, yStart);
       for(int i = xStart+step; i < yEnd; i+=step){

            oneLine.lineTo(i,i);
            xEnd -= step;

            secondLine.lineTo(xEnd, i);

           try {
               Thread.sleep(delay);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           repaint();
       }

        this.close();
    }

    private void close() {
        try {
            Thread.sleep(delayToClose);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.listener.closeWindow();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(10));
        g2d.draw(oneLine);
        g2d.draw(secondLine);

    }
}
