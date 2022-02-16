package com.superhero.superhero;

import classes.DatabaseConnection;
import classes.GeoCoder;
import classes.Heroes;
import classes.JsonBodyHandler;
import classes.UserInformation;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Link;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "Create incident", value = "/incidents")
public class Incidents extends HttpServlet {
    public void init() {
    }

    String deleteChar(String value) {
        String stringToChange;
        stringToChange = value.replace("[", "");
        stringToChange = stringToChange.replace("]", "");
        return stringToChange;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(UserInformation.userProbleme);
        if (UserInformation.userProbleme != "") {
            try {
                var client = HttpClient.newHttpClient();
                var rt = HttpRequest.newBuilder(URI.create("http://api.openweathermap.org/geo/1.0/direct?q=" + UserInformation.userCity + "&limit=1&appid=" + UserInformation.apiKey))
                        .header("accept", "application/json")
                        .build();
                var reponse = client.send(rt, HttpResponse.BodyHandlers.ofString());
                var parsedResponse = deleteChar(reponse.body().toString());
                ObjectMapper objectMapper = new ObjectMapper();
                UserInformation.userPosition = objectMapper.readValue(parsedResponse, GeoCoder.class);
                UserInformation.latitude = UserInformation.userPosition.lat;
                UserInformation.longitude = UserInformation.userPosition.lon;
                ArrayList<classes.Heroes> list = new ArrayList<Heroes>();
                Connection connection = DatabaseConnection.initializeDatabase();
                Statement statement = connection.createStatement();
                String query = "select * from heroes WHERE heroes.fIncident = '" + UserInformation.userProbleme + "' OR heroes.sIncident = '" + UserInformation.userProbleme + "' OR heroes.tIncident = '" + UserInformation.userProbleme + "'";
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
                request.setAttribute("heroes", list);
                request.getRequestDispatcher("/pages/incidents/index.jsp").forward(request, response);
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else {
            request.setAttribute("name", UserInformation.userCity);
            request.getRequestDispatcher("/pages/incidents/create.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO incidents values(?, ?, ?)");
            statement.setInt(1, 0);
            String name = request.getParameter("name");
            statement.setString(2, name);
            String pb = request.getParameter("incident");
            statement.setString(3, pb);
            UserInformation.userProbleme = pb;
            UserInformation.userCity = name;
            System.out.println(pb);
            System.out.println(name);
            statement.executeUpdate();
            statement.close();
            connection.close();
            response.sendRedirect(request.getContextPath() + "/incidents");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
