package mg.frontend.gui.views;

import mg.backend.BackendFasade;
import mg.frontend.gui.customcomponents.*;
import mg.frontend.gui.editViews.HistoryEditView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Map;

public class HistoryView implements ITableView, IContentListener, ButtonsListeners {

    private BackendFasade backend;
    private ContentComponent component;
    private TableContainer parentPanel;
    private ITableView childView;
    private ITableView parentView;
    private ITableContainerListener listener;
    private Map<String, String> tableMap;
    private IAnimationListener animListener;

    public HistoryView(BackendFasade backend, TableContainer parentPanel,ITableContainerListener listener,IAnimationListener animListener, ITableView childView ) {
        this.listener = listener;
        this.childView = childView;
        this.parentPanel = parentPanel;
        this.animListener = animListener;
        this.backend = backend;

        this.childView.setParentView(this);

        this.tableMap = backend.getHistoryMap();
        this.tableMap.remove("parent_id");

        this.component = new ContentComponent(this.tableMap, this);
    }

    @Override
    public void setParentView(ITableView parentView) {
        this.parentView = parentView;
    }

    @Override
    public void selection(int id) {
        this.backend.setHistoryId((long) id);
        childView.load();
        this.listener.nextCardListener((ButtonsListeners) childView);
    }

    @Override
    public void rightClick(MouseEvent e) {
        RightContextMenu contextMenu = new RightContextMenu();
        contextMenu.addListeners(this::addListener, this::editListener, this::deleteListener);

        contextMenu.show(e.getComponent(),e.getX(), e.getY());
    }


    @Override
    public void load() {
        try {
            component.setData( backend.loadHistory());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToContainer() {
        parentPanel.addToContainer(component.getComponent());
    }

    @Override
    public ContentComponent getContentComponent() {
        return this.component;
    }

    @Override
    public void backListener() {
        this.listener.previousCardListener((ButtonsListeners) this.parentView);
        this.backend.setHistoryId(null);
        this.backend.setDeviceId(null);
    }

    @Override
    public void addListener() {
        HistoryEditView historyEditView = new HistoryEditView("Dodaj Historie", this.tableMap,this::okAddButtonListener);
    }

    @Override
    public void editListener() {
        int id = this.component.getselectedRowId();
        if(id != -1) {
            backend.setHistoryId((long) id);
            this.tableMap = backend.getHistoryMap();
            this.tableMap.remove("parent_id");

            HistoryEditView historyEditView = new HistoryEditView("Edytuj Historie", this.tableMap, this::okEditButtonListener);
        }
    }

    @Override
    public void deleteListener() {
        int id = this.component.getselectedRowId();
        if(showConfirmationDelete() == JOptionPane.YES_OPTION && id != -1) {
            this.backend.deleteHistory(id);
            this.load();
            this.animListener.showDeleteAnimation();
        }
    }

    private int showConfirmationDelete() {
        return  JOptionPane.showConfirmDialog(null,
                "Czy na pewno chcesz usunac?", "Potwierdzenie Usuniecia",JOptionPane.YES_NO_OPTION);
    }

    public void okAddButtonListener(Map<String, String> data) {
        try {
            data.put("parent_id", String.valueOf(this.backend.getDeviceId()));
            this.backend.addHistory(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.load();
        this.animListener.showSaveAnimation();
    }

    public void okEditButtonListener(Map<String, String> data) {
        this.okAddButtonListener(data);
        backend.setDeviceId(null);
    }
}
