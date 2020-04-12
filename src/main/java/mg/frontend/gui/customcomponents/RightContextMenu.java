package mg.frontend.gui.customcomponents;

import javax.swing.*;

public class RightContextMenu extends JPopupMenu {
    JMenuItem addItem;
    JMenuItem editItem;
    JMenuItem deleteItem;
    public RightContextMenu() {
        addItem = new JMenuItem("Dodaj");
        editItem = new JMenuItem("Edytuj");
        deleteItem = new JMenuItem("Usun");
        add(addItem);
        add(editItem);
        add(deleteItem);
    }

    public void addListeners(IHeaderListener addListener,IHeaderListener editListener, IHeaderListener deleteListener ) {
        addItem.addActionListener((l)->addListener.run());
        editItem.addActionListener((l)->editListener.run());
        deleteItem.addActionListener((l)->deleteListener.run());
    }

}