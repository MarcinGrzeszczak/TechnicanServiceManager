package mg.frontend.console.screens;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mg.backend.BackendFasade;
import mg.frontend.console.Menu;

public class MainScreen extends Screen {

    public MainScreen(BackendFasade backend, Scanner scanner) {
        super(backend, scanner);

        Menu clientMenu = Menu.builder().setScanner(scanner).setHeader("Menu Glowne")
            .addOption("Pokaz liste klientow", this::displayTable)
            .addOption("Dodaj klienta", this::addClient)
            .addOption("Zaznacz klienta", super::select)
            .build();

        super.setMenu(clientMenu);
        this.backend = backend;
    }

    @Override
    void displayTable() {
        List<List<String>> clients;
        try {
            clients = super.backend.loadAllClients();
            super.showTable(clients);
        } catch (SQLException e) {
            System.out.println("Brak klientow");
            //e.printStackTrace();
        }

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

}