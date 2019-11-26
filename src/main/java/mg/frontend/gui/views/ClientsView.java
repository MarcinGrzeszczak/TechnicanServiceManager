package mg.frontend.gui.views;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import mg.backend.BackendFasade;
import mg.frontend.gui.customcomponents.*;
import mg.frontend.gui.editViews.ClientEditView;
import mg.frontend.gui.editViews.IEditViewListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Map;

public class ClientsView  implements IContentListener, ITableView, ButtonsListeners {

    private BackendFasade backend;
    private ContentComponent component;
    private TableContainer parentPanel;
    private ITableView childView;
    private ITableContainerListener listener;
    private Map<String, String> tableMap;
    private IAnimationListener animListener;

    public ClientsView(BackendFasade backend, TableContainer parentPanel,ITableContainerListener listener, IAnimationListener animListener,ITableView childView ) {
        this.listener = listener;
        this.childView = childView;
        this.parentPanel = parentPanel;
        this.backend = backend;
        this.tableMap = backend.getClientMap();
        this.animListener = animListener;
        this.childView.setParentView(this);
        this.component = new ContentComponent(this.tableMap, this);

    }
    @Override
    public void setParentView(ITableView parentView) {
    }

    @Override
    public void selection(int id) {
        backend.setClientId((long)id);
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
            component.setData( backend.loadAllClients());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContentComponent getContentComponent() {
        return this.component;
    }

    @Override
    public void addToContainer() {
        parentPanel.addToContainer(component.getComponent());
    }


    @Override
    public void backListener() {

    }

    @Override
    public void addListener() {

        ClientEditView editView = new ClientEditView("Dodaj klienta",this.tableMap,this::okAddButtonListener);
    }

    @Override
    public void editListener() {
        int id = this.component.getselectedRowId();
        if(id != -1) {
            backend.setClientId((long) id);
            ClientEditView editView = new ClientEditView("Dodaj klienta", this.backend.getClientMap(), this::okEditButtonListener);
        }
    }

    @Override
    public void deleteListener() {
        int id = this.component.getselectedRowId();
        if(showConfirmationDelete() == JOptionPane.YES_OPTION && id != -1) {
            this.backend.deleteClient(id);
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
            this.backend.addClient(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.load();
        this.animListener.showSaveAnimation();
    }

    public void okEditButtonListener(Map<String, String> data) {
        this.okAddButtonListener(data);
        backend.setClientId(null);
    }




}
