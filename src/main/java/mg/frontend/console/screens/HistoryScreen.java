package mg.frontend.console.screens;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mg.backend.BackendFasade;
import mg.frontend.console.Menu;

public class HistoryScreen extends Screen {
    private List<List<String>> costs;

    public HistoryScreen(BackendFasade backend, Scanner scanner) {
        super(backend, scanner);
        
        Menu deviceMenu = Menu.builder().setScanner(scanner).setHeader("Menu Historii")
                .addOption("Edytuj historie", this::editHistory)
                .addOption("Usun historie", null)
                .addOption("Pokaz koszty naprawy", this::displayTable)
                .addOption("Dodaj koszty naprawy", this::addCosts)
                .addOption("Zaznacz koszty naprawy", this::select)
                .addOption("Powrot", this::back)
                .build();

        super.setMenu(deviceMenu);
        this.backend = backend;
    }

    @Override
    public void back() {
        this.backend.setHistoryId(null);
        super.back();
    }

    @Override
    public void select() {
        super.select();
        this.backend.setCostsId((long) super.selectedId);
        super.callback.selection(super.selectedId);
    }

    @Override
    void displayTable() {       
        super.showTable(costs);
        super.displayMenu();
    }

    private void editHistory() {
        Map<String, String> data = super.add(this.backend.getHistoryMap());
        try {
            this.backend.addHistory(data);
        } catch (SQLException e) {
            System.out.println("Problem z zapisem historii");
            //e.printStackTrace();
        }

        super.displayMenu();
    }

    private void addCosts() {
        Map<String, String> data = super.add(this.backend.getCostMap());
        try {
            this.backend.addCost(data);
        } catch (SQLException e) {
            System.out.println("Problem z zapisem kosztow");
            //e.printStackTrace();
        }

        super.displayMenu();
    }

    @Override
    protected void reloadTable() {
        try {
            this.costs = super.backend.loadCosts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}