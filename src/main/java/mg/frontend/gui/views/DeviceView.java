package mg.frontend.gui.views;

import mg.backend.BackendFasade;
import mg.frontend.gui.customcomponents.ContentComponent;
import mg.frontend.gui.customcomponents.IContentListener;
import mg.frontend.gui.customcomponents.ITableContainerListener;
import mg.frontend.gui.customcomponents.TableContainer;
import mg.frontend.gui.editViews.DeviceEditView;
import mg.frontend.gui.editViews.IEditViewListener;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Map;

public class DeviceView  implements IContentListener, ITableView, ButtonsListeners {

    private BackendFasade backend;
    private ContentComponent component;
    private TableContainer parentPanel;
    private ITableView childView;
    private ITableView parentView;
    private ITableContainerListener listener;
    private Map<String, String> tableMap;
    private IAnimationListener animListener;

    public DeviceView(BackendFasade backend, TableContainer parentPanel,ITableContainerListener listener,IAnimationListener animListener , ITableView childView ) {
        this.listener = listener;
        this.childView = childView;
        this.parentPanel = parentPanel;
        this.animListener = animListener;
        this.backend = backend;
        this.tableMap = backend.getDeviceMap();

        this.childView.setParentView(this);
        tableMap.remove("parent_id");

        this.component = new ContentComponent(tableMap, this);
    }

    @Override
    public void setParentView(ITableView parentView) {
        this.parentView = parentView;
    }

    @Override
    public void selection(int id) {
        this.backend.setDeviceId((long) id);
        childView.load();
        this.listener.nextCardListener((ButtonsListeners) childView);
    }

    @Override
    public void load() {
        try {
            component.setData( backend.loadDevices());
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
        this.backend.setDeviceId(null);
        this.backend.setClientId(null);
    }

    @Override
    public void addListener() {
        DeviceEditView deviceEditView = new DeviceEditView("Dodaj Urzadzenie", this.tableMap, this::okAddButtonListener);
    }

    @Override
    public void editListener() {
        int id = this.component.getselectedRowId();
        if(id != -1) {
            backend.setDeviceId((long) id);
            this.tableMap = backend.getDeviceMap();
            this.tableMap.remove("parent_id");
            DeviceEditView deviceEditView = new DeviceEditView("Edytuj Urzadzenie", this.tableMap, this::okEditButtonListener);
        }
    }

    @Override
    public void deleteListener() {
        int id = this.component.getselectedRowId();
        if(showConfirmationDelete() == JOptionPane.YES_OPTION && id != -1) {
            this.backend.deleteDevice(id);
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
            data.put("parent_id", String.valueOf(this.backend.getClientId()));
            this.backend.addDevice(data);
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
