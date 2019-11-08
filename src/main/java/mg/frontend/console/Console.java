package mg.frontend.console;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Scanner;

import mg.backend.BackendFasade;
import mg.frontend.console.screens.ClientScreen;
import mg.frontend.console.screens.CostScreen;
import mg.frontend.console.screens.DeviceScreen;
import mg.frontend.console.screens.HistoryScreen;
import mg.frontend.console.screens.MainScreen;
import mg.frontend.console.screens.ScreenStack;

public class Console {
    private static final Scanner SCANNER = new Scanner(System.in);

    private BackendFasade backend;

    private ScreenStack screenStack;

    public Console() {
        
        try {
            this.backend = new BackendFasade();
            this.screenStack = new ScreenStack.Builder(this.backend, SCANNER)
                    .addToStack(MainScreen.class)
                    .addToStack(ClientScreen.class)
                    .addToStack(DeviceScreen.class)
                    .addToStack(HistoryScreen.class)
                    .addToStack(CostScreen.class)
                    .build();

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            
            System.out.println("Blad podczas tworzenia ekranow menu");
            //e.printStackTrace();
        } catch (IOException | SQLException e) {
            System.out.println("Blad podczas laczenia do bazy danych");
            //e.printStackTrace();
        }
    }

    public void mainLoop() {
        
        boolean running = true;

        while (running) {

        }
    }

    public void switchedScreen() {
        this.screenStack.displayScreen();
    }
}