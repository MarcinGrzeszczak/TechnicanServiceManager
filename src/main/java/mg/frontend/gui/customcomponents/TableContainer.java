package mg.frontend.gui.customcomponents;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TableContainer extends GUIComponent<CardLayout> {

    private CardLayout cardLayout;

    public TableContainer() {
        super();
    }

    @Override
    protected List<Component> addToPanel() {
        return null;
    }

    @Override
    protected CardLayout initLayout() {
        this.cardLayout = new CardLayout();
        return this.cardLayout;
    }

    public void nextCard() {
        this.cardLayout.next(super.panel);
    }

    public void prevCard() {
        this.cardLayout.previous(super.panel);
    }

    public void addToContainer(JPanel panel) {
        super.panel.add(panel);
    }
}
