package classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        String dbDriver = "org.mariadb.jdbc.Driver";
        String dbUrl = "jdbc:mariadb://localhost:8889/";
        String dbName = "superHeroes";
        String dbUsername = "root";
        String dbPassword = "root";
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbUrl + dbName + "?user=" + dbUsername + "&password=" + dbPassword);
    }

    static private List<Heroes> getAllHeroes() {
        ArrayList<Heroes> list = new ArrayList<Heroes>();
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            Statement statement = connection.createStatement();
            String query = "select * from heroes";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                classes.Heroes heroes = new classes.Heroes();
                heroes.name = resultSet.getString("name");
                heroes.phone = resultSet.getString("phone");
                heroes.latitude = resultSet.getDouble("latitude");
                heroes.longitude = resultSet.getDouble("longitude");
                heroes.fIncident = resultSet.getString("fIncident");
                heroes.sIncident = resultSet.getString("sIncident");
                heroes.tIncident = resultSet.getString("tIncident");
                list.add(heroes);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return list;
    }
}
