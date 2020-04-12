package mg.frontend.gui.animation;

import javax.swing.*;
import java.awt.*;

public class AnimationWindow extends JFrame implements IAnimationWIndowListener {

    private static final int WIDTH = 90;
    private static final int HEIGHT = 100;
    private JPanel content;

    public enum ANIMATION {SAVE, DELETE};

    @Override
    public void closeWindow() {
        dispose();
    }

    public AnimationWindow(int parentWidth, int parentHeight, int parentX, int parentY, ANIMATION animType) {
        AnimationDrawer anim = null;
        setSize(WIDTH,HEIGHT);
        setLocation((parentWidth / 2 + parentX) - WIDTH/2, (parentHeight / 2 + parentY) - HEIGHT/2);

        setUndecorated(true);
        content = new JPanel();
        BoxLayout boxLayout = new BoxLayout(content, BoxLayout.Y_AXIS);
        content.setLayout(boxLayout);


        if(ANIMATION.SAVE == animType)
            anim = drawSave();
        else if(ANIMATION.DELETE == animType)
            anim = drawDelete();
        //pack();

        JPanel parent = new JPanel(new BorderLayout());
        parent.add(content,BorderLayout.CENTER);
        add(parent);
        setVisible(true);

        if(anim != null)
            anim.animate();
    }

    private AnimationDrawer drawSave() {

        SaveAnimation saveAnimation = new SaveAnimation(25,25,60, 20, 40, 3 ,  55, 600, this);
        saveAnimation.setPreferredSize(new Dimension(45, 55));
        content.add(saveAnimation);
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel("ZAPISANO");
        label.setFont(new Font("Verdana", Font.PLAIN, 14));
        content.add(label);

        return saveAnimation;
    }

    private AnimationDrawer drawDelete() {
            DeleteAnimation deleteAnimation = new DeleteAnimation(30,30,60,60,3,60,800, this);
            deleteAnimation.setPreferredSize(new Dimension(45, 55));
            content.add(deleteAnimation);
            content.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel label = new JLabel("USUNIETO");
            label.setFont(new Font("Verdana", Font.PLAIN, 14));
            content.add(label);

            return deleteAnimation;
    }
}
