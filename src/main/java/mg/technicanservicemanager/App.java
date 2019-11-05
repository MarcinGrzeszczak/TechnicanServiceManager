package mg.technicanservicemanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import mg.backend.BackendFasade;
import mg.backend.entities.ClientEntity;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        //System.out.println(System.getProperty("user.dir"));
        
        try {
            BackendFasade backend = new BackendFasade();


            System.out.println(backend.loadAllClients().stream()
                .map(ClientEntity::show)
                .collect(Collectors.joining("\n")));

        } catch (IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
