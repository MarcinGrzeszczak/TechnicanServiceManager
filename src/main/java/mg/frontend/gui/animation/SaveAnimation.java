package mg.frontend.gui.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class SaveAnimation extends JPanel implements AnimationDrawer {

    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private double curvePoint;
    private int step;
    private int delay;
    private int delayToClose;
    private IAnimationWIndowListener listener;
    private Path2D path;

    public SaveAnimation(int xStart, int yStart, int xEnd, int yEnd, double curvePoint, int step, int delay, int delayToClose, IAnimationWIndowListener listener) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.curvePoint = curvePoint;
        this.step = step;
        this.delay = delay;
        this.delayToClose = delayToClose;
        this.listener = listener;
    }

    @Override
    public void animate(){

        path = new Path2D.Double();
        double y = 0;
        path.moveTo(xStart,yStart);
        double partAfter = (curvePoint - yEnd) / ((xEnd - curvePoint) / step);
        for(int i = xStart+step; i < xEnd; i+=step) {
            if(i < curvePoint)
                y = i;
            else
                y -= partAfter ;

            path.lineTo(i, y);

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
            Thread.sleep(this.delayToClose);
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
        g2d.draw(path);
    }
}
