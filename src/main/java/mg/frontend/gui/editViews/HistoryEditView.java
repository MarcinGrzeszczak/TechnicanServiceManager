package mg.frontend.gui.editViews;

import javax.swing.*;
import java.util.Map;

public class HistoryEditView extends EditView {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    private  Map<String, String> data;
    public HistoryEditView(String name, Map<String, String> historyMap, IEditViewListener listener) {
        super(name, WIDTH, HEIGHT, listener);
        this.data = historyMap;
        createWindow(historyMap.size(),this.data);
    }

    @Override
    protected void addToPanel() {
        data.forEach((label,value) -> {
            super.contentPanel.add(new JLabel(label));
            super.contentPanel.add(new JTextField(value));
        });
    }
}
