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
import java.util.List;
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
        if (!UserInformation.userProbleme.equals("") ) {
        GeoCoder geoCoder = GeoCoder.cityToLatLon(UserInformation.userCity);
            try {
                UserInformation.latitude = geoCoder.lat;
                UserInformation.longitude = geoCoder.lon;
                ArrayList<Heroes> list = DatabaseConnection.getAllHeroes();
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
            String name = request.getParameter("name");
            String pb = request.getParameter("incident");
            DatabaseConnection.insertIncident(name, pb);
            UserInformation.userCity = name;
            UserInformation.userProbleme = pb;
            response.sendRedirect(request.getContextPath() + "/incidents");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
