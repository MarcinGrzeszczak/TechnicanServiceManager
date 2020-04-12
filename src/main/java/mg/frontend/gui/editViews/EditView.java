package mg.frontend.gui.editViews;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public abstract class EditView extends JFrame {
    protected JPanel contentPanel;
    private IEditViewListener listener;

    public EditView(String name, int width, int height, IEditViewListener listener){
        this.listener = listener;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle(name);
        setSize(width,height);
        setResizable(false);

    }
    protected void createWindow(int rows, Map<String,String> data) {
        JPanel parentPanel = new JPanel();
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        this.contentPanel = new JPanel(new GridLayout(rows, 2));

        BoxLayout boxLayout = new BoxLayout(parentPanel,BoxLayout.Y_AXIS);
        parentPanel.setLayout(boxLayout);

        this.addToPanel();

        parentPanel.add(this.contentPanel);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e->
        {
            for(int i = 0; i < contentPanel.getComponents().length-1; i++) {
                String key = ((JLabel) contentPanel.getComponent(i++)).getText();
                String value = ((JTextField) contentPanel.getComponent(i)).getText();
                data.put(key,value);
            }

            this.listener.okButtonListener(data);
            dispose();
        });

        buttonsPanel.add(okButton);

        JButton cancelButton = new JButton("Anuluj");
        cancelButton.addActionListener(e-> dispose());

        buttonsPanel.add(cancelButton);

        parentPanel.add(buttonsPanel);

        add(parentPanel);
        setVisible(true);
    }
    protected abstract void addToPanel();
}
