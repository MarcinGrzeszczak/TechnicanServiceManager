package mg.frontend.console.screens;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mg.backend.BackendFasade;
import mg.frontend.console.Menu;

public class DeviceScreen extends Screen {

    public DeviceScreen(BackendFasade backend, Scanner scanner) {
        super(backend, scanner);
        
        Menu deviceMenu = Menu.builder().setScanner(scanner).setHeader("Menu Urzadzenia")
                .addOption("Edytuj urzadzenie", this::editDevice)
                .addOption("Usun urzadzenie", null)
                .addOption("Pokaz historie", this::displayTable)
                .addOption("Dodaj historie", this::addHistory)
                .addOption("Zaznacz historie", this::select)
                .addOption("Powrot", this::back)
                .build();

        super.setMenu(deviceMenu);
        this.backend = backend;
    }

    @Override
    public void back() {
        this.backend.setDeviceId(null);
        super.back();
    }

    @Override
    public void select() {
        super.select();
        this.backend.setHistoryId((long) super.selectedId);
        super.callback.selection(super.selectedId);
    }

    @Override
    void displayTable() {
        List<List<String>> history;
        try {
            history = super.backend.loadHistory();
            super.showTable(history);
        } catch (SQLException e) {
            System.out.println("Brak historii");
            //e.printStackTrace();
        }
        super.displayMenu();
    }

    private void editDevice() {
        Map<String, String> data = super.add(this.backend.getDeviceMap());
        try {
            this.backend.addDevice(data);
        } catch (SQLException e) {
            System.out.println("Problem z zapisem urzadzenia");
            //e.printStackTrace();
        }

        super.displayMenu();
    }

    private void addHistory() {
        Map<String, String> data = super.add(this.backend.getHistoryMap());
        try {
            this.backend.addHistory(data);
        } catch (SQLException e) {
            System.out.println("Problem z zapisem historii");
            //e.printStackTrace();
        }

        super.displayMenu();
    }
}