package mg.frontend.gui.customcomponents;

import mg.frontend.gui.editViews.ClientEditView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class ContentComponent extends GUIComponent<BorderLayout> {

   private JTable jTable;
   private TableModel tableModel;
    public ContentComponent(Map<String, String> tableMap, IContentListener listener) {
        super();
        this.tableModel = new TableModel(tableMap);
        this.jTable = new JTable(tableModel);
        this.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2 && jTable.getSelectedRow() != -1) {
                    int selectedId = tableModel.getSelectionId(jTable.getSelectedRow());
                    listener.selection(selectedId);
                }

            }
        });
        super.createContainer();
    }

    public int getselectedRowId() {
        return tableModel.getSelectionId(jTable.getSelectedRow());
    }

    @Override
    protected List<Component> addToPanel() {
        super.panel.add(new JScrollPane(this.jTable),BorderLayout.CENTER);
        return null;
    }

    @Override
    protected BorderLayout initLayout() {
        return new BorderLayout();
    }

    public void setData(List<List<String>> listData) {
       this.tableModel.refresh(listData);
    }


}
