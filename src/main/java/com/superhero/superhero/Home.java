package com.superhero.superhero;

import classes.DatabaseConnection;
import classes.GeoCoder;
import classes.Heroes;
import classes.UserInformation;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "home", value = "/home")
public class Home extends HttpServlet {

    public void init() {
    }

    String deleteChar(String value) {
        String stringToChange;
        stringToChange = value.replace("[", "");
        stringToChange = stringToChange.replace("]", "");
        return stringToChange;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<classes.Heroes> list = new ArrayList<Heroes>();
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
            request.setAttribute("heroes", list);
            request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String name = request.getParameter("name");
            var client = HttpClient.newHttpClient();
            var rt = HttpRequest.newBuilder(URI.create("http://api.openweathermap.org/geo/1.0/direct?q=" + name + "&limit=1&appid=" + UserInformation.apiKey))
                    .header("accept", "application/json")
                    .build();
            var reponse = client.send(rt, HttpResponse.BodyHandlers.ofString());
            var parsedResponse = deleteChar(reponse.body().toString());
            ObjectMapper objectMapper = new ObjectMapper();
            UserInformation.userPosition = objectMapper.readValue(parsedResponse, GeoCoder.class);
            UserInformation.latitude = UserInformation.userPosition.lat;
            UserInformation.longitude = UserInformation.userPosition.lon;
            UserInformation.userCity = UserInformation.userPosition.name;
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception err) {
            err.printStackTrace();
        }

    }
}