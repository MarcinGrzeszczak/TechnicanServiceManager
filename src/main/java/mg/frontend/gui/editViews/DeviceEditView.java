package mg.frontend.gui.editViews;

import javax.swing.*;
import java.util.Map;

public class DeviceEditView extends EditView {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 150;

    private  Map<String, String> data;
    public DeviceEditView(String name, Map<String, String> deviceMap, IEditViewListener listener) {
        super(name, WIDTH, HEIGHT, listener);
        this.data = deviceMap;
        createWindow(deviceMap.size(),this.data);
    }

    @Override
    protected void addToPanel() {
        data.forEach((label,value) -> {
            super.contentPanel.add(new JLabel(label));
            super.contentPanel.add(new JTextField(value));
        });
    }
}
