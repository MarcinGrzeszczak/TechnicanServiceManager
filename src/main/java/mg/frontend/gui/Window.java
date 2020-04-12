package mg.frontend.gui;

import mg.backend.BackendFasade;
import mg.frontend.gui.animation.AnimationWindow;
import mg.frontend.gui.animation.IAnimationWIndowListener;
import mg.frontend.gui.customcomponents.Header;
import mg.frontend.gui.customcomponents.ITableContainerListener;
import mg.frontend.gui.customcomponents.TableContainer;
import mg.frontend.gui.views.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

class Window extends JFrame implements ITableContainerListener, IAnimationListener {
    private static final String WINDOW_NAME = "Technican Service Manager";

    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 720;

    private BackendFasade backend;
    private TableContainer tableContainer;
    private Header header;
    private Thread animThread;

    public Window() {
        super(WINDOW_NAME);

        try {
            this.backend = new BackendFasade();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int positionX = (screenSize.width - WINDOW_WIDTH) /2;
        int positionY = (screenSize.height - WINDOW_HEIGHT) / 2;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(positionX,positionY);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        header = new Header(600, 2000);
        tableContainer = new TableContainer();

        CostView costView = new CostView(this.backend, tableContainer, this,this ,null);
        HistoryView historyView = new HistoryView(this.backend, tableContainer, this, this,costView);
        DeviceView deviceView = new DeviceView(this.backend, tableContainer, this, this,historyView);
        ClientsView clientsView = new ClientsView(this.backend, tableContainer, this,this, deviceView);
        clientsView.load();

        clientsView.addToContainer();
        deviceView.addToContainer();
        historyView.addToContainer();
        costView.addToContainer();

        this.swapButtonsListeners(clientsView);

        mainPanel.add(header.getComponent());
        mainPanel.add(tableContainer.getComponent());
        add(mainPanel);

        setJMenuBar(header.getMenuBar());
        setVisible(true);

    }

    @Override
    public void nextCardListener(ButtonsListeners listeners) {
        tableContainer.nextCard();
        this.swapButtonsListeners(listeners);
    }

    @Override
    public void previousCardListener(ButtonsListeners listeners) {
        tableContainer.prevCard();
        this.swapButtonsListeners(listeners);
    }

    private void swapButtonsListeners(ButtonsListeners listeners) {
        this.header.setBackListener(listeners::backListener);
        this.header.setAddListener(listeners::addListener);
        this.header.setEditListener(listeners::editListener);
        this.header.setDeleteListener(listeners::deleteListener);
    }

    @Override
    public void showSaveAnimation() {
        this.animThread = new Thread(()-> new AnimationWindow(WINDOW_WIDTH,WINDOW_HEIGHT,getX(),getY(), AnimationWindow.ANIMATION.SAVE));
        this.animThread.start();
    }

    @Override
    public void showDeleteAnimation() {
        this.animThread = new Thread(()-> new AnimationWindow(WINDOW_WIDTH,WINDOW_HEIGHT,getX(),getY(), AnimationWindow.ANIMATION.DELETE));
        this.animThread.start();
    }
}
