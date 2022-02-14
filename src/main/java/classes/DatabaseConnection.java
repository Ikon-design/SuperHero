package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException{
        String dbDriver = "org.mariadb.jdbc.Driver";
        String dbUrl = "jdbc:mariadb://localhost:8889/";
        String dbName = "superHeroes";
        String dbUsername = "root";
        String dbPassword = "root";


        Class.forName(dbDriver);

        return DriverManager.getConnection(dbUrl + dbName +"?user="+ dbUsername+"&password=" + dbPassword);
    }
}
