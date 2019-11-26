package mg.frontend.gui.views;

import mg.backend.BackendFasade;
import mg.frontend.gui.customcomponents.ContentComponent;
import mg.frontend.gui.customcomponents.IContentListener;
import mg.frontend.gui.customcomponents.ITableContainerListener;
import mg.frontend.gui.customcomponents.TableContainer;
import mg.frontend.gui.editViews.CostEditView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Map;

public class CostView implements ITableView, IContentListener, ButtonsListeners {

    private BackendFasade backend;
    private ContentComponent component;
    private TableContainer parentPanel;
    private ITableView parentView;
    private ITableContainerListener listener;
    private  Map<String, String> tableMap;
    private IAnimationListener animListener;

    public CostView(BackendFasade backend, TableContainer parentPanel,ITableContainerListener listener,IAnimationListener animListener ,ITableView childView ) {
        this.listener = listener;
        this.parentPanel = parentPanel;
        this.backend = backend;
        this.animListener = animListener;
        this.tableMap = backend.getCostMap();
        this.tableMap.remove("parent_id");

        this.component = new ContentComponent(this.tableMap, this);
    }

    @Override
    public void setParentView(ITableView parentView) {
        this.parentView = parentView;
    }

    @Override
    public void selection(int id) {
        this.backend.setCostsId((long) id);
    }

    @Override
    public void load() {
        try {
            component.setData( backend.loadCosts());
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
        this.backend.setCostsId(null);
        this.backend.setHistoryId(null);
    }

    @Override
    public void addListener() {
        CostEditView costEditView = new CostEditView("Dodaj koszty", this.tableMap, this::okAddButtonListener);
    }

    @Override
    public void editListener() {
        int id = this.component.getselectedRowId();
        if(id != -1) {
            backend.setCostsId((long) id);
            this.tableMap = backend.getCostMap();
            this.tableMap.remove("parent_id");
            CostEditView costEditView = new CostEditView("Edytuj koszty", this.tableMap, this::okEditButtonListener);
        }
    }

    @Override
    public void deleteListener() {
        int id = this.component.getselectedRowId();
        if(showConfirmationDelete() == JOptionPane.YES_OPTION && id != -1) {
            this.backend.deleteCost(id);
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
            data.put("parent_id", String.valueOf(this.backend.getHistoryId()));
            this.backend.addCost(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.load();
        this.animListener.showSaveAnimation();
    }

    public void okEditButtonListener(Map<String, String> data) {
        this.okAddButtonListener(data);
        backend.setCostsId(null);
    }
}
