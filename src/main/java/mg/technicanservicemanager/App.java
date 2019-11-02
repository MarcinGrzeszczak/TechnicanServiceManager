package mg.technicanservicemanager;

import java.io.IOException;
import java.sql.SQLException;

import mg.backend.BackendFasade;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        BackendFasade backend = new BackendFasade();
        //System.out.println(System.getProperty("user.dir"));
        
        try {
            backend.initConnection();
        } catch (IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
