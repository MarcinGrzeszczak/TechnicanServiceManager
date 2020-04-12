package mg.technicanservicemanager;

import mg.frontend.console.Console;
import mg.frontend.gui.GUIFasade;

public class App {

    public App() {
        //new Console();
        GUIFasade gui = new  GUIFasade();
        gui.runInNewThread();
    }
    
    public static void main(String[] args) { 
        App app = new App();
    }
}
