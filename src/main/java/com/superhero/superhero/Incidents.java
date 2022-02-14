package com.superhero.superhero;

import classes.DatabaseConnection;
import classes.GeoCoder;
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

@WebServlet(name = "Create incident", value = "/incidents/create")
public class Incidents extends HttpServlet {
    public void init() {
    }


    String deleteChar (String value){
        String stringToChange;
        stringToChange = value.replace("[", "");
        stringToChange = stringToChange.replace("]", "");
        return stringToChange;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/pages/incidents/create.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO incidents values(?, ?)");
            statement.setInt(1, 0);
            String name = request.getParameter("name");
            statement.setString(2, name );
            statement.executeUpdate();
            statement.close();
            connection.close();
            request.getRequestDispatcher("/pages/incidents/index.jsp").forward(request, response);
            try {
                var client = HttpClient.newHttpClient();
                var rt = HttpRequest.newBuilder(URI.create("http://api.openweathermap.org/geo/1.0/direct?q=" + name + "&limit=1&appid=" + UserInformation.apiKey))
                        .header("accept", "application/json")
                        .build();
                var reponse = client.send(rt, HttpResponse.BodyHandlers.ofString());
                var parsedResponse = deleteChar(reponse.body().toString());
                ObjectMapper objectMapper = new ObjectMapper();
                GeoCoder geoCoder = objectMapper.readValue(parsedResponse, GeoCoder.class);
                System.out.println(geoCoder.lon);
            } catch (Exception err){
                err.printStackTrace();
            }
        } catch (Exception err) {
            err.printStackTrace();
            //System.out.println(request.getParameter("name"));
        }
    }
}
