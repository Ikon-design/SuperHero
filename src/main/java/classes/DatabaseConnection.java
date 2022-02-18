package classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        String dbDriver = "org.mariadb.jdbc.Driver";
        String dbUrl = "jdbc:mariadb://localhost:3306/";
        String dbName = "superHeroes";
        String dbUsername = "root";
        String dbPassword = "";
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbUrl + dbName + "?user=" + dbUsername + "&password=" + dbPassword);
    }

    static public ArrayList<Heroes> getAllHeroes() {
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

    static public void insertHero(String name, double latitude, double longitude, String phone, String fIncident, String sIncident, String tIncident) {
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = connection.prepareStatement("insert into heroes values(?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, 0);
            statement.setString(2, name);
            statement.setDouble(3, latitude);
            statement.setDouble(4, longitude);
            statement.setString(5, phone);
            statement.setString(6, fIncident);
            statement.setString(7, sIncident);
            statement.setString(8, tIncident);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    static public void insertIncident(String name, String pb) {
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO incidents values(?, ?, ?)");
            statement.setInt(1, 0);
            statement.setString(2, name);
            statement.setString(3, pb);
            statement.executeUpdate();
            statement.close();
            connection.close();

        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
