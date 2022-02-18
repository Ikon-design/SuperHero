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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<classes.Heroes> list = DatabaseConnection.getAllHeroes();
        request.setAttribute("heroes", list);
        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        GeoCoder geoCoder = GeoCoder.cityToLatLon(name);
        UserInformation.latitude = geoCoder.lat;
        UserInformation.longitude = geoCoder.lon;
        UserInformation.userCity = geoCoder.name;
        response.sendRedirect(request.getContextPath() + "/home");

    }
}