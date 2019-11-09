package mg.frontend.console.screens;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mg.backend.BackendFasade;
import mg.frontend.console.Menu;

public class ClientScreen extends Screen {

    private List<List<String>> devices;

    public ClientScreen(BackendFasade backend, Scanner scanner) {
        super(backend, scanner);
        
        Menu deviceMenu = Menu.builder().setScanner(scanner).setHeader("Menu Klienta")
                .addOption("Edytuj klienta", this::editClient)
                .addOption("Usun klienta", null)
                .addOption("Pokaz liste urzadzen", this::displayTable)
                .addOption("Dodaj urzadzenie", this::addDevice)
                .addOption("Zaznacz urzadzenie", this::select)
                .addOption("Powrot", this::back)
                .build();

        super.setMenu(deviceMenu);
        this.backend = backend;
    }

    @Override
    public void back() {
        this.backend.setClientId(null);
        super.back();
    }

    @Override
    public void select() {
        super.select();
        this.backend.setDeviceId((long) super.selectedId);
        super.callback.selection(super.selectedId);
    }

    @Override
    void displayTable() {
        
        super.showTable(devices);
        super.displayMenu();
    }

    private void editClient() {
        Map<String, String> data = super.add(this.backend.getClientMap());
        try {
            this.backend.addClient(data);
        } catch (SQLException e) {
            System.out.println("Problem z zapisem klienta");
            //e.printStackTrace();
        }

        super.displayMenu();
    }

    private void addDevice() {
        Map<String, String> data = super.add(this.backend.getDeviceMap());
        try {
            this.backend.addDevice(data);
        } catch (SQLException e) {
            System.out.println("Problem z zapisem urzadzenia");
            //e.printStackTrace();
        }

        super.displayMenu();
    }

    @Override
    protected void reloadTable() {
        try {
            this.devices = super.backend.loadDevices();
        } catch (SQLException e) {
            System.out.println("Blad ladowania urzadzen");
            //e.printStackTrace();
        }
    }
}