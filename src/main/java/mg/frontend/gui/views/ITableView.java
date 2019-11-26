package mg.frontend.gui.views;

import mg.frontend.gui.customcomponents.ContentComponent;

public interface ITableView {
    void load();
    void addToContainer();
    void setParentView(ITableView parentView);
    ContentComponent getContentComponent();
}
