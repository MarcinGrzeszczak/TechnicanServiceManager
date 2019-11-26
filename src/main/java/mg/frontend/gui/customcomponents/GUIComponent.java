package mg.frontend.gui.customcomponents;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class GUIComponent<T extends LayoutManager> {
    protected JPanel panel;

    GUIComponent() {
        this.panel = new JPanel(this.initLayout());
    }

    protected void createContainer() {
        List<Component> components = this.addToPanel();
        if(components != null)
            for(Component comp : components)
                this.panel.add(comp);
    }

    protected abstract List<Component> addToPanel ();

    protected abstract T initLayout();

    public JPanel getComponent() {
         return this.panel;
    }
}
