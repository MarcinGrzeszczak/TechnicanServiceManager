package mg.backend.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import mg.backend.entities.PersonEntity;

public class DatabaseConnection {

    private String host;
    private String username;
    private String password;
    private String database;

    private Connection connection;

    private void loadProperties(String pathToProperties) throws IOException {

        try (InputStream input = new FileInputStream(pathToProperties)) {
            Properties props = new Properties();

            props.load(input);

            this.host = props.getProperty("db.host");
            this.username = props.getProperty("db.username");
            this.password = props.getProperty("db.password");
            this.database = props.getProperty("db.dbName");

        }
    }

    private void connectToDB() throws SQLException {
        String url = "jdbc:postgresql://" + this.host + "/" + this.database + "?user=" + this.username + "&password="
                + this.password + "&ssl=false";

        this.connection = DriverManager.getConnection(url);
    }

    private void closeDB() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }
   
    public void requestQuery() throws SQLException {
        Map <String, Class<?>> typeMap = new HashMap<>();
        typeMap.put("text", String.class);
        typeMap.put("serial", Integer.class);


        Statement st = this.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM clients");
        PersonEntity person;
        List<PersonEntity> persons = new ArrayList<>();
        List<String> methods;
        methods = Arrays.asList(PersonEntity.class.getDeclaredMethods()).stream().map(method -> method.getName())
                .collect(Collectors.toList());
        System.out.println(methods);
        ResultSetMetaData meta = rs.getMetaData();
        while (rs.next()) {
            person = new PersonEntity();
            for (int i = 1; i <= meta.getColumnCount(); ++i) {
                String columnName = meta.getColumnName(i).substring(0, 1).toUpperCase()
                        + meta.getColumnName(i).substring(1);
                String columnSetter = "set" + columnName;
                String columnType = meta.getColumnTypeName(i);
                System.out.println(columnSetter);
                if (methods.contains(columnSetter)) {
                    try {
                        Method method = person.getClass().getMethod(columnSetter, typeMap.get(columnType));
                        switch(columnType) {
                            case "text": method.invoke(person, rs.getString(i)); break;
                            case "serial": method.invoke(person, rs.getInt(i)); break;
                        }
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                            | IllegalArgumentException | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
           }
           persons.add(person);
        }
        System.out.println(persons.stream()
            .map(p->p.show())
            .collect(Collectors.joining("\n", "", "")));
        st.close();

    }

    public DatabaseConnection(String propertiesFile) throws IOException, SQLException {
        this.loadProperties(propertiesFile);
        this.connectToDB();
    }
}