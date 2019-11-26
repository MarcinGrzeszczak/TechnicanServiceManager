package mg.frontend.gui.customcomponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Header extends GUIComponent<GridLayout> {

    private List<Component> components;
    private JMenuBar menuBar;

    public Header(int width, int height){
        super();
        super.panel.setMaximumSize(new Dimension(width, height));
        super.createContainer();
        this.createMenu();
    }

    @Override
    protected List<Component> addToPanel() {
        components = new ArrayList<>();
        components.add(this.createButton("back","Powrót"));
        components.add(this.createButton("add","Dodaj"));
        components.add(this.createButton("edit","Edytuj"));
        components.add(this.createButton("garbage","Usun"));

        return components;
    }

    public void setBackListener(IHeaderListener backListener) {
       this.swapListener((JButton) this.components.get(0), backListener);
    }

    public void setAddListener(IHeaderListener addListener) {
        this.swapListener((JButton) this.components.get(1), addListener);
        this.swapListener(this.menuBar.getMenu(1).getItem(0), addListener);
    }

    public void setEditListener(IHeaderListener editListener) {
        this.swapListener((JButton) this.components.get(2), editListener);
        this.swapListener(this.menuBar.getMenu(1).getItem(1), editListener);
    }

    public void setDeleteListener(IHeaderListener deleteListener) {
        this.swapListener((JButton) this.components.get(3), deleteListener);
        this.swapListener(this.menuBar.getMenu(1).getItem(2), deleteListener);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    private void swapListener(JButton button, IHeaderListener listener) {
        for(ActionListener currentListener: button.getActionListeners())
            button.removeActionListener(currentListener);

        button.addActionListener(e->listener.run());
    }

    private void swapListener(JMenuItem item, IHeaderListener listener) {
        for(ActionListener currentListener: item.getActionListeners())
            item.removeActionListener(currentListener);

        item.addActionListener(e->listener.run());
    }

    @Override
    protected GridLayout initLayout() {
        GridLayout layout = new GridLayout(1,4,2,2);
        return layout;
    }

    private static ImageIcon loadImage(String filename){
        ImageIcon icon = null;
        String resource = "resources/assets/";
        String ext = ".png";
        String fullpath = resource + filename + ext;
        try {
            Image img = ImageIO.read(new FileInputStream(fullpath));
            icon = new ImageIcon(img);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return icon;
    }

    private JButton createButton(String imageName, String name) {

        JButton button = new JButton();
        button.setText(name);
        ImageIcon icon = loadImage(imageName);

        if(icon != null)
            button.setIcon(icon);

        return button;
    }

    private JMenu addToMenu(String menuName, List<String> items) {
        JMenu menu = new JMenu(menuName);
        for(String itemName : items){
            JMenuItem menuItem = new JMenuItem(itemName);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenuBar createMenu() {
        this.menuBar = new JMenuBar();

        List<String> fileItems = new ArrayList<>();
        fileItems.add("Zakoncz");

        List<String> editItems = new ArrayList<>();
        editItems.add("Dodaj");
        editItems.add("Edytuj");
        editItems.add("Usun");

        this.menuBar.add(addToMenu("Plik", fileItems));
        this.menuBar.add(addToMenu("Edycja", editItems));

        this.menuBar.getMenu(0).getItem(0).addActionListener((l)->System.exit(0));

        return menuBar;
    }
}
