package mg.frontend.gui.editViews;

import javax.swing.*;
import java.util.Map;

public class ClientEditView extends EditView {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 250;

    private  Map<String, String> data;
    public ClientEditView(String name, Map<String, String> clientMap, IEditViewListener listener) {
        super(name, WIDTH, HEIGHT, listener);
        this.data = clientMap;
        createWindow(clientMap.size(), this.data);
    }

    @Override
    protected void addToPanel() {
        data.forEach((label,value) -> {
            super.contentPanel.add(new JLabel(label));
            super.contentPanel.add(new JTextField(value));
        });
    }
}
