package mg.frontend.console.screens;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mg.backend.BackendFasade;
import mg.frontend.console.Menu;

public class CostScreen extends Screen {

    public CostScreen(BackendFasade backend, Scanner scanner) {
        super(backend, scanner);
        
        Menu deviceMenu = Menu.builder().setScanner(scanner).setHeader("Menu kosztow")
                .addOption("Edytuj koszty", this::editCosts)
                .addOption("Usun koszty", null)
                .addOption("Powrot", this::back)
                .build();

        super.setMenu(deviceMenu);
        this.backend = backend;
    }

    @Override
    void displayTable() {
    }

    @Override
    public void back() {
        this.backend.setCostsId(null);
        super.back();
    }
    
    private void editCosts() {
        Map<String, String> data = super.add(this.backend.getCostMap());
        try {
            this.backend.addCost(data);
        } catch (SQLException e) {
            System.out.println("Problem z zapisem kosztow");
            //e.printStackTrace();
        }

        super.displayMenu();
    }
}