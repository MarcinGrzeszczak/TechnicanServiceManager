package mg.frontend.console.screens;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mg.backend.BackendFasade;
import mg.frontend.console.Menu;

public class MainScreen extends Screen {
    private List<List<String>> clients;

    public MainScreen(BackendFasade backend, Scanner scanner) {
        super(backend, scanner);

        Menu clientMenu = Menu.builder().setScanner(scanner).setHeader("Menu Glowne")
            .addOption("Pokaz liste klientow", this::displayTable)
            .addOption("Dodaj klienta", this::addClient)
            .addOption("Zaznacz klienta", this::select)
            .build();

        super.setMenu(clientMenu);
        this.backend = backend;
    }

    @Override
    public void select() {
        super.select();
        this.backend.setClientId((long) super.selectedId);
        super.callback.selection(super.selectedId);
    }

    @Override
    void displayTable() {
        super.showTable(clients);
        super.displayMenu();
    }

    private void addClient() {
        Map<String, String> data = super.add(this.backend.getClientMap());
        try {
            this.backend.addClient(data);
        } catch (SQLException e) {
            //System.out.println("Problem z zapisem klienta");
            e.printStackTrace();
        }

        super.displayMenu();
    }

    @Override
    protected void reloadTable() {
        try {
            this.clients = super.backend.loadAllClients();
        } catch (SQLException e) {
            System.out.println("Blad ladowania klientow");
            //e.printStackTrace();
        }
    }

}